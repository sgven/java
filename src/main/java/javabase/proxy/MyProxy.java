package javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler {

    // 目标类，也就是被代理对象
    private MyInterface target;

    public MyProxy(MyInterface target) {
        this.target = target;
    }

    public MyInterface getProxy(){
        // 参数说明：1.classLoader   2.interface     3.invoiceHandler
//        this.target = target;
        return (MyInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target, args);
        System.out.println("after");
        return result;
    }

    public static void main(String[] args) {
        MyInterfaceImpl service = new MyInterfaceImpl();
        MyProxy proxy = new MyProxy(service);
        proxy.getProxy().dosomething();
    }
}
