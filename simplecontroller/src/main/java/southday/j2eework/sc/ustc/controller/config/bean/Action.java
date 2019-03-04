package southday.j2eework.sc.ustc.controller.config.bean;

import java.util.List;

public class Action {
    private String actionName;
    private String className;
    private String methodName;
    private List<Result> results;
    private List<InterceptorRef> interceptorRefs;
    
    /**
     * 查找result
     * @param name
     * @return 不存在则返回null
     */
    public Result getResult(String name) {
        for (Result r : results)
            if (name.equals(r.getName()))
                return r;
        return null;
    }

    public String getActionName() {
        return actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }
    public List<InterceptorRef> getInterceptorRefs() {
        return interceptorRefs;
    }
    public void setInterceptorRefs(List<InterceptorRef> interceptorRefs) {
        this.interceptorRefs = interceptorRefs;
    }
}
