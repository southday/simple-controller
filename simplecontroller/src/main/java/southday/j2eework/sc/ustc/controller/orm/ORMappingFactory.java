package southday.j2eework.sc.ustc.controller.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import southday.j2eework.sc.ustc.controller.factory.Factory;
import southday.j2eework.sc.ustc.controller.orm.bean.ID;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMapping;
import southday.j2eework.sc.ustc.controller.orm.bean.Property;

/*
E6的or_mapping.xml如下：
<OR-Mapping>
    <class>
        <name>southday.j2eework.water.ustc.bean.User</name>
        <table>user_t</table>
        <id>
            <name>userId</name>
            <column>user_id</column>
            <type>java.lang.Integer</type>
            <lazy>false</lazy>
        </id>
        <property>
            <name>userName</name>
            <column>user_name</column>
            <type>java.lang.String</type>
            <lazy>false</lazy>
        </property>
        <propertye>
            <name>password</name>
            <column>password</column>
            <type>java.lang.String</type>
            <lazy>true</lazy>
        </propertye>
    </class>
</OR-Mapping>
 */

public class ORMappingFactory implements Factory<ORMapping> {
    String ormappingXMLPath = null;
    private static final SAXReader saxReader = new SAXReader();
    
    public ORMappingFactory(String ormappingXMLPath) {
        this.ormappingXMLPath = ormappingXMLPath;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ORMapping create() throws Exception {
        Document doc = saxReader.read(ormappingXMLPath);
        Element root = doc.getRootElement();
        ORMapping ormapping = new ORMapping();
        ormapping.setOrmClasses(toORMClasses(root.elements("class")));
        return ormapping;
    }
    
    private Map<String, ORMClass> toORMClasses(List<Element> elements) {
        Map<String, ORMClass> ormClasses = new HashMap<>();
        for (Element e : elements) {
            ORMClass clzz = toORMClass(e);
            ormClasses.put(clzz.getName(), clzz);
        }
        return ormClasses;
    }
    
    @SuppressWarnings("unchecked")
    private ORMClass toORMClass(Element element) {
        ORMClass clzz = new ORMClass();
        clzz.setId(toID(element.element("id")));
        clzz.setName(element.elementTextTrim("name"));
        clzz.setProperties(toProperties(element.elements("property")));
        clzz.setTable(element.elementTextTrim("table"));
        return clzz;
    }
    
    private ID toID(Element element) {
        ID id = new ID();
        id.setName(element.elementTextTrim("name"));
        id.setColumn(element.elementTextTrim("column"));
        id.setType(element.elementTextTrim("type"));
        return id;
    }
    
    private Map<String, Property> toProperties(List<Element> elements) {
        Map<String, Property> properties = new HashMap<>();
        for (Element e : elements) {
            Property prop = toProperty(e);
            properties.put(prop.getName(), prop);
        }
        return properties;
    }
    
    private Property toProperty(Element element) {
        Property prop = new Property();
        prop.setName(element.elementTextTrim("name"));
        prop.setColumn(element.elementTextTrim("column"));
        prop.setLazy("true".equals(element.elementTextTrim("lazy")));
        prop.setType(element.elementTextTrim("type"));
        return prop;
    }

}
