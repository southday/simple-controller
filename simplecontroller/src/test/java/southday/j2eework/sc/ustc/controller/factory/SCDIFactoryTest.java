package southday.j2eework.sc.ustc.controller.factory;

import java.util.Map;

import southday.j2eework.sc.ustc.controller.di.SCDIFactory;
import southday.j2eework.sc.ustc.controller.di.bean.DIBean;
import southday.j2eework.sc.ustc.controller.di.bean.SCDI;

public class SCDIFactoryTest {
    public static void main(String[] args) throws Exception {
        String dipath = "F:/J2EE-SC/di/di.xml";
        Factory<SCDI> fac = new SCDIFactory(dipath);
        SCDI scdi = fac.create();
//        String json = JSONObject.toJSONString(scdi);
//        System.out.println(json);
        Map<String, DIBean> dibeans = scdi.getDibeans();
        for (Map.Entry<String, DIBean> e : dibeans.entrySet()) {
            System.out.println("e.key: " + e.getKey());
            System.out.println("e.value: " + e.getValue());
        }
        
    }
}
