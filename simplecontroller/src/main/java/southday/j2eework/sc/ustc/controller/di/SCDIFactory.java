package southday.j2eework.sc.ustc.controller.di;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import southday.j2eework.sc.ustc.controller.di.bean.DIBean;
import southday.j2eework.sc.ustc.controller.di.bean.DIField;
import southday.j2eework.sc.ustc.controller.di.bean.SCDI;
import southday.j2eework.sc.ustc.controller.factory.Factory;

/* di.xml
<sc-di>
    <bean id="user" class="southday.j2eework.water.ustc.bean.User"></bean>
    <bean id="loginAction" class="southday.j2eework.water.ustc.action.LoginAction">
        <field name="user" bean-ref="user"></field>
    </bean>
    <bean id="registerAction" class="southday.j2eework.water.ustc.action.RegisterAction">
        <field name="user" bean-ref="user"></field>
    </bean>
</sc-di>
 */

public class SCDIFactory implements Factory<SCDI> {
    String diXMLPath = null;
    private static final SAXReader saxReader = new SAXReader();
    
    public SCDIFactory(String diXMLPath) {
        this.diXMLPath = diXMLPath;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SCDI create() throws Exception {
        Document doc = saxReader.read(diXMLPath);
        Element root = doc.getRootElement();
        SCDI scdi = new SCDI();
        scdi.setDibeans(toDIBeans(root.elements("bean")));
        return scdi;
    }

    @SuppressWarnings("unchecked")
    private Map<String, DIBean> toDIBeans(List<Element> elements) {
        Map<String, DIBean> dibeans = new HashMap<>();
        for (Element e : elements) {
            DIBean dibean = new DIBean();
            dibean.setId(e.attributeValue("id"));
            dibean.setClassName(e.attributeValue("class"));
            dibean.setDifields(toDIFields(e.elements("field")));
            dibeans.put(dibean.getId(), dibean);
        }
        return dibeans;
    }
    
    private List<DIField> toDIFields(List<Element> elements) {
        List<DIField> difields = new ArrayList<>();
        for (Element e : elements) {
            DIField difield = new DIField();
            difield.setName(e.attributeValue("name"));
            difield.setBeanRef(e.attributeValue("bean-ref"));
            difields.add(difield);
        }
        return difields;
    }
}
