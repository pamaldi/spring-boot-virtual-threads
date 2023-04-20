package cloud.isaura.springbootvirtualthreads;

public class BombLogReader implements Runnable
{

    private BombLog bombLog;

    public BombLogReader(BombLog bombLog)
    {
        this.bombLog = bombLog;
    }

    @Override
    public void run()
    {
        BombLogInfo bombLogInfo = bombLog.getBombLogInfo();
        Long startTime =  System.currentTimeMillis();
        while(bombLogInfo.numberOfPlannedRequests()>bombLogInfo.numberOfRequests())
        {
            try
            {
                Thread.sleep(200);
                bombLogInfo = bombLog.getBombLogInfo();
                System.out.println("Number of requests: " + bombLogInfo.numberOfRequests());
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Elapsed time: " + (System.currentTimeMillis()-startTime) + " ms");
        bombLogInfo = bombLog.getBombLogInfo();
        System.out.println("Number of requests per seconds: " + bombLogInfo.numberOfRequests()/((System.currentTimeMillis()-startTime)/1000));


    }
}
