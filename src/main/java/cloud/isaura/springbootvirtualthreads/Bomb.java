package cloud.isaura.springbootvirtualthreads;

import java.io.IOException;
import java.net.URISyntaxException;

public class Bomb
{
    public static final int NUMBER_OF_PLANNED_REQUESTS = 10000;




    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException
    {

        BombLog bombLog = new BombLog(NUMBER_OF_PLANNED_REQUESTS);
        BombLogReader bombLogReader = new BombLogReader(bombLog);
        Thread bombLogReaderThread = new Thread(bombLogReader);
        bombLogReaderThread.start();
        for (int i = NUMBER_OF_PLANNED_REQUESTS; i > 0; i--)
        {
            RequestTimeOutClient requestTimeOutClient = new RequestTimeOutClient(bombLog);
            Thread.startVirtualThread(requestTimeOutClient::performRequest);

        }


    }
}
