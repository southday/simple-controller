package southday.j2eework.sc.ustc.controller.di.bean;

import java.util.List;

public class DIBean {
    private String id;
    private String className;
    private List<DIField> difields;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<DIField> getDifields() {
        return difields;
    }
    public void setDifields(List<DIField> difields) {
        this.difields = difields;
    }
}
