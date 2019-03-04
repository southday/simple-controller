package southday.j2eework.sc.ustc.controller.proxy;

import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.proxy.cglib.LazyLoadProxy;
import southday.j2eework.sc.ustc.controller.proxy.cglib.LogActionProxy;

public class ProxyFactory {

    public static Object newLogActionProxyInstance(Object target, Action action) {
        LogActionProxy proxy = new LogActionProxy(target, action);
        return proxy.getInstance();
    }
    
    public static Object newLazyLoadPoxyInstance(Object target, ORMClass ormClzz) {
        LazyLoadProxy proxy = new LazyLoadProxy(target, ormClzz);
        return proxy.getInstance();
    }
}
