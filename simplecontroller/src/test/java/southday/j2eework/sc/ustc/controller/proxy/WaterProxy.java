package southday.j2eework.sc.ustc.controller.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

class Cat {
    public void foo() {
        System.out.println("喵喵喵");
    }
}

public class WaterProxy implements MethodInterceptor {
    
    public Object getInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
            
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("大水来啦！！！");
        proxy.invokeSuper(obj, args);
        System.out.println("大水走啦！！！");
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        Cat c = new Cat();
        
        Object proxy = new WaterProxy().getInstance(c);
        ReflectUtil.execute(proxy, "foo");
    }
}
