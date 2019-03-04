package southday.j2eework.sc.ustc.controller.proxy.cglib;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import southday.j2eework.sc.ustc.controller.config.ControllerConfig;
import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Interceptor;
import southday.j2eework.sc.ustc.controller.config.bean.InterceptorRef;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class LogActionProxy implements MethodInterceptor {
    // SimpleController工程和UseSC工程紧耦合
    private static final String LOG_INTERCEPTOR_NAME = "log";
    private ControllerConfig cconfig = ControllerConfig.getControllerConfig();
    private Object target;
    private Action action;
    
    public LogActionProxy(Object target, Action action) {
        this.target = target;
        this.action = action;
    }
    
    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (!action.getMethodName().equals(method.getName()))
            return proxy.invokeSuper(obj, args);
        List<InterceptorRef> refs = action.getInterceptorRefs();
        Interceptor logInterceptor = cconfig.findInterceptor(refs, LOG_INTERCEPTOR_NAME);
        if (logInterceptor == null)
            return proxy.invokeSuper(obj, args);
        // predo() -> startTime
        Object objRes = cconfig.getObjResource(logInterceptor.getClassName());
        String startTime = (String)ReflectUtil.execute(objRes, logInterceptor.getPredo());
        String result = (String)proxy.invokeSuper(obj, args);
        // afterdo(actionName, startTime, result)
        Object afterdoArgs[] = {action.getActionName(), startTime, result};
        ReflectUtil.execute(objRes, logInterceptor.getAfterdo(), afterdoArgs);
        return result;
    }
}
