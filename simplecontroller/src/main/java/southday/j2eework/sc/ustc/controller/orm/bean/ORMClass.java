package southday.j2eework.sc.ustc.controller.orm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ORMClass {
    private String name;
    private String table;
    private ID id;
    private Map<String, Property> properties;
    
    public List<Property> nolazyProps() {
        List<Property> nolazy = new ArrayList<>();
        // id一定是非lazy的，因为后期要用其去查询lazyload属性
        nolazy.add(getId());
        Map<String, Property> properties = getProperties();
        for (Map.Entry<String, Property> map : properties.entrySet())
            if (!map.getValue().isLazy())
                nolazy.add(map.getValue());
        return nolazy;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
    public Map<String, Property> getProperties() {
        return properties;
    }
    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }
}
