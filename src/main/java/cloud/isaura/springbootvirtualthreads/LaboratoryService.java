package cloud.isaura.springbootvirtualthreads;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LaboratoryService
{
    public LaboratoryResponse buildPrint(Long idEpisode)
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread()+" getLaboratory ");
        return new LaboratoryResponse(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8), UUID.randomUUID().toString());
    }
}
