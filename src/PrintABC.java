import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintABC {

	public static void main(String args[]) {
		PrintABC printABC = new PrintABC();
		LetterPrinter letterPrinter = printABC.new LetterPrinter();
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(printABC.new PrintRunnable(letterPrinter,'A'));
		service.execute(printABC.new PrintRunnable(letterPrinter,'B'));
		service.execute(printABC.new PrintRunnable(letterPrinter,'C'));
		service.shutdown();
	}
	
	private class LetterPrinter {
		private char letter = 'A';
		public void print() {
			System.out.print(letter);
			if('C' == letter) 
				System.out.println();
		}
		
		public void nextletter() {
			switch(letter) {
			case 'A' : 
				letter = 'B';
				break;
			case 'B' : 
				letter = 'C';
				break;
			case 'C' :
				letter = 'A';
				break;
			}
		}
		
		public char getLetter() {
			return letter;
		}
	}
	
	private class PrintRunnable implements Runnable {

		private LetterPrinter letterPrinter;
		private char letter;
		
		public PrintRunnable(LetterPrinter letterPrinter,char letter) {
			this.letterPrinter = letterPrinter;
			this.letter = letter;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 10; i++) {
				synchronized (letterPrinter) {
					while(letter != letterPrinter.getLetter()) {
						try {
							letterPrinter.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					letterPrinter.print();
					letterPrinter.nextletter();
					letterPrinter.notifyAll();
				}
			}
		}
		
	}
	
}
