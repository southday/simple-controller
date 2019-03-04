package southday.j2eework.sc.ustc.controller.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Controller;
import southday.j2eework.sc.ustc.controller.config.bean.Interceptor;
import southday.j2eework.sc.ustc.controller.config.bean.InterceptorRef;
import southday.j2eework.sc.ustc.controller.config.bean.SCConfiguration;
import southday.j2eework.sc.ustc.controller.config.factory.ActionFactory;
import southday.j2eework.sc.ustc.controller.config.factory.DOM4JSCConfigurationFactory;
import southday.j2eework.sc.ustc.controller.factory.Factory;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

/**
 * 单例，用于保存从controller.xml解析的结果以及相关资源
 * @author southday
 * @date 2018年12月1日
 */
public class ControllerConfig {
    private static final String CONTROLLER_XML_PATH = getPathOfControllerXML();
    private Factory<SCConfiguration> fac = new DOM4JSCConfigurationFactory(CONTROLLER_XML_PATH);
    private SCConfiguration scc = null;
    private Map<String, Interceptor> interceptorMap = null;
    private Map<String, Action> actionMap = null;
    private Map<String, Object> objResources = null;

    private ControllerConfig() {
        objResources = new HashMap<>();
        try {
            scc = fac.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        interceptorMap = new HashMap<>();
        for (Interceptor i : scc.getInterceptors()) {
            interceptorMap.put(i.getName(), i);
        }
        actionMap = new HashMap<>();
        for (Action a : getController().getActions()) {
            actionMap.put(a.getActionName(), a);
        }
    }
    
    private static class ControllerConfigHolder {
        private static ControllerConfig config = new ControllerConfig();
    }
    
    public static ControllerConfig getControllerConfig() {
        return ControllerConfigHolder.config;
    }
    
    // 目前controller.xml中，只有一个<controller>标签
    public Controller getController() {
        return scc.getControllers().get(0);
    }
    
    public Interceptor getInterceptor(String name) {
        return interceptorMap.get(name);
    }
    
    public Action getAction(String name) {
        Action action = actionMap.get(name);
        return action == null ? ActionFactory.DEFAULT_ACTION : action;
    }
    
    public Object getObjResource(String className) throws Exception {
        Object obj = objResources.get(className);
        if (obj == null) {
            synchronized (this) {
                obj = objResources.get(className);
                if (obj != null)
                    return obj;
                obj = ReflectUtil.newInstance(className);
                objResources.putIfAbsent(className, obj);
            }
        }
        return obj;
    }
    
    /**
     * 在refs中找指定名称(interceptorName)的interceptor，找不到则返回null
     * @param refs
     * @param interceptorName
     * @return
     */
    public Interceptor findInterceptor(List<InterceptorRef> refs, String interceptorName) {
        if (refs != null && interceptorName != null && !"".equals(interceptorName.trim()))
            for (InterceptorRef ref : refs)
                if (interceptorName.equals(ref.getName()))
                    return getInterceptor(interceptorName);
        return null;
    }
    
    private static String getPathOfControllerXML() {
        return BaseConfig.getBaseConfig().getFilePathInClassesDIR("controller.xml");
    }
}
