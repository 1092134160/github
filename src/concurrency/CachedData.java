package concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {

	Object data;
	volatile boolean cacheValid;
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void processData() {
		rwl.readLock().lock();
		if(!cacheValid) {
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			if(!cacheValid) {
				cacheValid = true;
			}
			rwl.readLock().lock();
			rwl.writeLock().unlock();
		}
		//use(data);
		rwl.readLock().unlock();
	}

}