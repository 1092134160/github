package proxy;

public class Test {

	public static void main(String[] args) {
		MyInterface target = new MyClass();
		MyInterface proxy = (MyInterface) new MyProxy().bind(target);
		proxy.myFun(1);
	}

}
