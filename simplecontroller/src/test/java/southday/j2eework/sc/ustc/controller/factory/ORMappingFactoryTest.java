package southday.j2eework.sc.ustc.controller.factory;

import com.alibaba.fastjson.JSONObject;

import southday.j2eework.sc.ustc.controller.orm.ORMappingFactory;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMapping;

public class ORMappingFactoryTest {

    public static void main(String[] args) throws Exception {
        String path = "F:/J2EE-SC/orm/or_mapping.xml";
        Factory<ORMapping> fac = new ORMappingFactory(path);
        ORMapping ormapping = fac.create();
//        ORMClass ormClzz = ormapping.getOrmClasses().get("User");
        String json = JSONObject.toJSONString(ormapping);
        System.out.println(json);
    }
}
