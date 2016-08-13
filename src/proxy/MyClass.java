package proxy;

public class MyClass implements MyInterface {

	@Override
	public int myFun(int i) {
		System.out.println(i);
		return i;
	}

}
