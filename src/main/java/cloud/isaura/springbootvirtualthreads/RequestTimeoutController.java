package cloud.isaura.springbootvirtualthreads;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.random.RandomGenerator;

import static java.time.temporal.ChronoUnit.SECONDS;

@RestController
public class RequestTimeoutController
{

    @GetMapping("/long-way-run")
    String all()
    {
        //System.out.println("Start to sleep "+Thread.currentThread());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("End sleep "+Thread.currentThread());
        return "a";
    }

    private static String getRandomCall()
    {
        RandomGenerator generator = RandomGenerator.getDefault();
        int random = generator.nextInt(10, 20);
        String uri= random%2==0 ? "http://10.255.255.1/" : "https://it.wikipedia.org/wiki/Gian_Piero_Gasperini";
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
        HttpResponse<String> response = null;
        try
        {
            response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e)
        {

        } catch (InterruptedException e)
        {

        }
        return response != null ? response.body() : "";
    }
}
