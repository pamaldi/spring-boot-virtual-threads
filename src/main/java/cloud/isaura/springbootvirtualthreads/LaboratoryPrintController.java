package cloud.isaura.springbootvirtualthreads;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaboratoryPrintController
{



    @GetMapping("/laboratory-print/{idEpisode}")
    public LaboratoryReport getLaboratoryPrint(@PathVariable Long idEpisode) throws Exception
    {
      LaboratoryPrintService laboratoryPrintService = new LaboratoryPrintService();
      return laboratoryPrintService.getResponse(idEpisode);
    }




    @GetMapping("/laboratory-printasync/{idEpisode}")
    public LaboratoryReport getLaboratoryPrintAsync(@PathVariable Long idEpisode) throws Exception
    {

        LaboratoryPrintService laboratoryPrintService = new LaboratoryPrintService();
         return laboratoryPrintService.getAsyncResponse(idEpisode);

    }


    @GetMapping("/laboratory-printvt/{idEpisode}")
    public LaboratoryReport getLaboratoryPrintVT(@PathVariable Long idEpisode) throws Exception
    {

        LaboratoryPrintService laboratoryPrintService = new LaboratoryPrintService();
        return laboratoryPrintService.getVtResponse(idEpisode);

    }
}
