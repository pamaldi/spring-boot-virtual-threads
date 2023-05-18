package cloud.isaura.springbootvirtualthreads;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.SECONDS;

public class LaboratoryPrintClient
{

    private final String endpointType;

    public LaboratoryPrintClient(String endpointType)
    {
        this.endpointType = endpointType;
    }

    public void performRequest()
    {
        System.out.println("Thread " + Thread.currentThread() + " started");
        UncheckedObjectMapper objectMapper = new UncheckedObjectMapper();
        String baseEnd = "laboratory-print";
        String end = baseEnd+endpointType;
        String uri = "http://localhost:8080/virtual-threads/"+end+"/1";
        HttpRequest request = null;
        CompletableFuture<HttpResponse<String>> response = null;
        try
        {
            request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .timeout(Duration.of(60, SECONDS))
                    .GET()
                    .build();
        } catch (Exception e)
        {
           e.printStackTrace();
        }
        try
        {
            /*
             response = HttpClient
                    .newBuilder()
                    .build()
                     .send(request, HttpResponse.BodyHandlers.ofString());
                     */
            response = HttpClient.newBuilder()
                    .build()
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            String responseString = null;
            try
            {
                responseString = response == null ? "null" : response.get().body().toString();
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            } catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println("Thread " + Thread.currentThread() + " finished with response "+responseString);

        }
    }

    class UncheckedObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
        /**
         * Parses the given JSON string into a Map.
         */
        Map<String, String> readValue(String content)
        {
            try
            {
                return this.readValue(content, new TypeReference<>() {
                });
            } catch (IOException ioe)
            {
                throw new CompletionException(ioe);
            }
        }
    }
}
