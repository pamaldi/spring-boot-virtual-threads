package cloud.isaura.springbootvirtualthreads;

import java.util.concurrent.locks.ReentrantLock;

public class BombLog
{

    private final Integer numberOfPlannedRequests;

    private  Integer numberOfRequests = 0;
    private  Integer numberOfOkRequests = 0;

    private  Integer numberOfKoRequest=0;

    private ReentrantLock lock;

    public BombLog(Integer numberOfPlannedRequests)
    {
        this.numberOfPlannedRequests = numberOfPlannedRequests;
        this.lock = new ReentrantLock();
    }

    public void incNumberOfRequest()
    {
        lock.lock();
        this.numberOfRequests++;
        lock.unlock();
    }

    public void incNumberOfOkRequest()
    {
        lock.lock();
        this.numberOfOkRequests++;
        lock.unlock();
    }

    public void incNumberOfKoRequest()
    {
        lock.lock();
        this.numberOfKoRequest++;
        lock.unlock();
    }

   public BombLogInfo getBombLogInfo()
   {
       lock.lock();
       BombLogInfo bombLogInfo = new BombLogInfo(numberOfPlannedRequests, numberOfRequests, numberOfOkRequests, numberOfKoRequest);
       lock.unlock();
       return bombLogInfo;
   }


}
