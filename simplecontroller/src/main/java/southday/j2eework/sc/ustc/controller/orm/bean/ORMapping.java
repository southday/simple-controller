package southday.j2eework.sc.ustc.controller.orm.bean;

import java.util.Map;

public class ORMapping {
    private Map<String, ORMClass> ormClasses;

    public Map<String, ORMClass> getOrmClasses() {
        return ormClasses;
    }
    public void setOrmClasses(Map<String, ORMClass> ormClasses) {
        this.ormClasses = ormClasses;
    }
}
