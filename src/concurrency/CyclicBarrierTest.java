package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	static CyclicBarrier c = new CyclicBarrier(2);
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
				}
				System.out.println(1);
			}
			
		}).start();
        c.await();
        System.out.println(2);
	}

}
