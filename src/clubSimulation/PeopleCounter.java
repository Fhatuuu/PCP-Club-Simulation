package clubSimulation;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PeopleCounter {
	private AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private AtomicInteger peopleInside; //counter for patrons inside club
	private AtomicInteger peopleLeft; //counter for patrons who have left the club
	private AtomicInteger maxPeople; //maximum patrons allowed in the club at one time

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	PeopleCounter(int max) {
		peopleOutSide= new AtomicInteger(0);
		peopleInside=new AtomicInteger(0);
		peopleLeft=new AtomicInteger(0);
		maxPeople=new AtomicInteger(max);
	}
		
	public int getWaiting() {
		return peopleOutSide.get();
	}

	public int getInside() {
		return peopleInside.get();
	}
	
	public int getTotal() {
		return (peopleOutSide.get()+  peopleInside.get() +peopleLeft.get());
	}

	public int getLeft() {
		return peopleLeft.get();
	}
	
	public int getMax() {
		return maxPeople.get();
	}
	
	//someone arrived outside
	public void personArrived() {
		peopleOutSide.getAndIncrement();
	}

	// someone got inside
	public void personEntered() throws InterruptedException {
        lock.lock();
        try {
            while (peopleInside.get() >= maxPeople.get()) {
                condition.await(); // Wait if club is full
            }
            peopleOutSide.getAndDecrement();
            peopleInside.getAndIncrement();
        } finally {
            lock.unlock();
        }
    }

	//someone left
	public void personLeft() {
        lock.lock();
        try {
            peopleInside.getAndDecrement();
            peopleLeft.getAndIncrement();
            condition.signal(); // Signal that a spot is available
        } finally {
            lock.unlock();
        }
    }

	//too many people inside
	synchronized public boolean overCapacity() {
		
		return peopleInside.get() >= maxPeople.get();
	}
	
	//not used
	synchronized public void resetScore() {
		peopleOutSide.set(0);
		peopleInside.set(0);
		peopleLeft.set(0);
	}
}
