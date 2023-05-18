package cloud.isaura.springbootvirtualthreads;

import java.util.UUID;
import java.util.random.RandomGenerator;

public class PatientService
{

    public Patient getPatientByEpisode(Long idEpisode
    )
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread()+" getPatient ");
        return new Patient(RandomGenerator.getDefault().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
