package cloud.isaura.springbootvirtualthreads;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LaboratoryPrintClientMain
{
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException
    {


        final String SEQ="";
        final String ASYNC="async";
        final String VT="vt";
        Long startTime = System.currentTimeMillis();
        ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 1; i <= 1000; i++) {
            String taskname = "task " + i;
            System.out.println("Submitting "+taskname);
            LaboratoryPrintClient laboratoryPrintClient = new LaboratoryPrintClient(VT);
            exec.submit(laboratoryPrintClient::performRequest);
        }
        exec.close();
        Long endTime = System.currentTimeMillis();
        System.out.println("Elapsed "+(endTime-startTime));



    }
}
