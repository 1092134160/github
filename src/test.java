import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class test {

	public static void main(String args[]) throws IOException {
	    int i = 1;
	    fun(i);
	    System.out.println(i);
	    String s = "a";
	    fun(s);
	    System.out.println(s);
	}
	
	private static void fun(int i) {
		i = 2;
	}
	
	private static void fun(String s) {
		s = "b";
	}

}
