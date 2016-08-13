package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

	static CyclicBarrier c = new CyclicBarrier(3,new A());
	
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
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
				}
				System.out.println(2);
			}
			
		}).start();
		c.await();		
		System.out.println(3);
	}
	
	static class A implements Runnable {

		@Override
		public void run() {
			System.out.println(4);
		}
		
	}
}
