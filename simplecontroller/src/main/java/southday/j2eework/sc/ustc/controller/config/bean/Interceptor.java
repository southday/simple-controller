package southday.j2eework.sc.ustc.controller.config.bean;

public class Interceptor {
    private String name;
    private String className;
    private String predo;
    private String afterdo;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String clssName) {
        this.className = clssName;
    }
    public String getPredo() {
        return predo;
    }
    public void setPredo(String predo) {
        this.predo = predo;
    }
    public String getAfterdo() {
        return afterdo;
    }
    public void setAfterdo(String afterdo) {
        this.afterdo = afterdo;
    } 
    
}
