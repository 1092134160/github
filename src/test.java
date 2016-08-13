import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class test {

	public static void main(String args[]) throws IOException {
		/*FileInputStream in = new FileInputStream("C:/Users/admin/Desktop/新建文本文档 (2).txt");
	    FileOutputStream out = new FileOutputStream("C:/Users/admin/Desktop/sc.txt");
	    //byte[] b = new byte[1024];
	    int temp = 0;
	    while((temp = in.read()) != -1) {
	    	out.write(temp);
	    }
	    in.close();
	    out.close();*/
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
