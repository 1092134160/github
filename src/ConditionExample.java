import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public static void main(String[] args) {
		ConditionExample example = new ConditionExample();
		LetterPrinter letterPrinter = example.new LetterPrinter();
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(example.new PrintRunnable(letterPrinter, 'A'));
		service.execute(example.new PrintRunnable(letterPrinter, 'B'));
		service.execute(example.new PrintRunnable(letterPrinter, 'C'));
		service.shutdown();
	}

	private class LetterPrinter {

		private char letter = 'A';

		private void print() {
			System.out.println(letter);
			if (letter == 'C') {
				System.out.println();
			}
		}

		private void nextLetter() {
			switch (letter) {
			case 'A':
				letter = 'B';
				break;
			case 'B':
				letter = 'C';
				break;
			case 'C':
				letter = 'A';
				break;
			}
		}

	}

	private class PrintRunnable implements Runnable {

		private LetterPrinter letterPrinter;
		private char letter;

		private PrintRunnable(LetterPrinter letterPrinter, char letter) {
			this.letterPrinter = letterPrinter;
			this.letter = letter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					lock.lock();
					while (letterPrinter.letter != letter) {
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					letterPrinter.print();
					letterPrinter.nextLetter();
					condition.signalAll();
					lock.unlock();
				}
			}
		}

	}

}
