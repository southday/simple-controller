package southday.j2eework.sc.ustc.controller.orm.bean;

public class Property {
    protected String name;
    protected String column;
    protected String type;
    protected boolean lazy;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isLazy() {
        return lazy;
    }
    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }
    
}
