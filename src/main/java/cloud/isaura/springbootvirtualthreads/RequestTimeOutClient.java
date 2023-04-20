package cloud.isaura.springbootvirtualthreads;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.random.RandomGenerator;

import static java.time.temporal.ChronoUnit.SECONDS;

public class RequestTimeOutClient
{

    private final BombLog bombLog;

    public RequestTimeOutClient(BombLog bombLog)
    {
        this.bombLog = bombLog;
    }

    public void performRequest()
    {
        //System.out.println("Thread " + Thread.currentThread() + " started");
        RandomGenerator generator = RandomGenerator.getDefault();
        int random = generator.nextInt(10, 100);
        try
        {
            Thread.sleep(random*100);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        String uri = "http://localhost:8080/virtual-threads/long-way-run";
        HttpRequest request = null;
        try
        {
            request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .timeout(Duration.of(5, SECONDS))
                    .GET()
                    .build();
        } catch (URISyntaxException e)
        {

        }
        try
        {
            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {

        }finally
        {
            //System.out.println("Thread " + Thread.currentThread() + " finished");
            this.bombLog.incNumberOfRequest();
        }
    }
}
