package southday.j2eework.sc.ustc.controller.config.bean;

import java.util.List;

public class SCConfiguration {
    private List<Interceptor> interceptors;
    private List<Controller> controllers;
    
    public Interceptor getInterceptor(String name) {
        for (Interceptor i : interceptors)
            if (name.equals(i.getName()))
                return i;
        return null;
    }
    
    public List<Interceptor> getInterceptors() {
        return interceptors;
    }
    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }
    public List<Controller> getControllers() {
        return controllers;
    }
    public void setControllers(List<Controller> controllers) {
        this.controllers = controllers;
    }
    
}
