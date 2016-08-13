package cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyCglibProxy implements MethodInterceptor {

	public Enhancer enhancer = new Enhancer();
	private String name;

	/*
	 * public MyCglibProxy(String name) { this.name = name; }
	 */

	public Object getProxy(Class<?> cls) {
		enhancer.setSuperclass(cls);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("before");
		proxy.invokeSuper(obj, args);
		System.out.println("after");
		return null;
	}

	public static void main(String[] args) {
		MyCglibProxy proxy = new MyCglibProxy();
		// 通过生成子类的方式创建代理类
		Hello proxyImp = (Hello) proxy.getProxy(Hello.class);
		proxyImp.say();
		proxyImp.talk();
	}

}
