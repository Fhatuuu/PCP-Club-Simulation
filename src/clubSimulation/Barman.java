package clubSimulation;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Andre the Barman. Andre serves drinks to
    patrons at the bar, patrons must not leave the bar before being served. Implement
    Andre as a separate thread.
    @author Fhatuwani Mokwenda
 */

public class Barman extends Thread{
    
    private final Lock serverLock = new ReentrantLock();
    private boolean isServing = false;

    public void serveDrink() throws InterruptedException {
        synchronized (serverLock) {
            isServing = true;
            serverLock.notifyAll();
        }

        Thread.sleep(1000);

        synchronized (serverLock) {
            isServing = false;
            serverLock.notifyAll(); // notify thay serving is done
        }
    }

    public void waitForServe() throws InterruptedException {
        synchronized (serverLock) {
            while (isServing) {
                serverLock.wait();
            }
        }
    }
}
