package cloud.isaura.springbootvirtualthreads;

import org.apache.tomcat.util.codec.binary.Base64;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class LaboratoryPrintService
{

    public LaboratoryReport getResponse(Long idEpisode)
    {

        Patient patientById = getPatient(idEpisode);
        LaboratoryResponse laboratoryResponse = getLaboratoryResponse(idEpisode);
        return buildLaboratoryReport(laboratoryResponse,patientById);
    }

    private Patient getPatient(Long idEpisode){
        PatientService patientService = new PatientService();
        return patientService.getPatientByEpisode(idEpisode);
    }

    private CompletableFuture<Patient> getPatientAsync(Long id)
    {
        return CompletableFuture.supplyAsync(() -> getPatient(id));
    }

    private LaboratoryResponse getLaboratoryResponse(Long episodeId)
    {
        LaboratoryService laboratoryService   = new LaboratoryService();
        return laboratoryService.buildPrint(episodeId);
    }


    private CompletableFuture<LaboratoryResponse> getLaboratoryResponseAsync(Long episodeId)
    {
       return CompletableFuture.supplyAsync(() -> getLaboratoryResponse(episodeId));
    }

    private LaboratoryReport buildLaboratoryReport(LaboratoryResponse laboratoryResponse, Patient patient)
    {
        LaboratoryReport laboratoryReport = new LaboratoryReport();
        laboratoryReport.setExecutionTime(LocalDateTime.now());
        laboratoryReport.setBase64Image(Base64.encodeBase64String(laboratoryResponse.imageData()));
        laboratoryReport.setFiscalCode(patient.fiscalCode());
        laboratoryReport.setExternalId(laboratoryResponse.externalId());
        return laboratoryReport;
    }


    public LaboratoryReport getAsyncResponse(Long idEpisode){
        CompletableFuture<Patient> patientCompletableFuture = getPatientAsync(idEpisode);
        CompletableFuture<LaboratoryResponse>laboratoryResponseAsync = getLaboratoryResponseAsync(idEpisode);
        final CompletableFuture<Void> completableFutureAllOf =
                CompletableFuture.allOf(patientCompletableFuture, laboratoryResponseAsync);
          try
          {
              completableFutureAllOf.get();
              final Patient patient =
                      patientCompletableFuture.get();
              final LaboratoryResponse laboratoryResponse =
                      laboratoryResponseAsync.get();
              return buildLaboratoryReport(laboratoryResponse, patient);
          }catch(Exception e)
          {
              throw new RuntimeException(e);
          }

    }


    public LaboratoryReport getVtResponse(Long idEpisode){
         try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
             var patientFuture = executor.submit(()->getPatient(idEpisode));
             var laboratoryResponseFuture=  executor.submit(()->getLaboratoryResponse(idEpisode));
             return buildLaboratoryReport(laboratoryResponseFuture.get(), patientFuture.get());
        } catch (ExecutionException e)
         {
             throw new RuntimeException(e);
         } catch (InterruptedException e)
         {
             throw new RuntimeException(e);
         }


    }
}
