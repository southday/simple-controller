package southday.j2eework.sc.ustc.controller.di;

import java.util.HashMap;
import java.util.Map;

import southday.j2eework.sc.ustc.controller.config.BaseConfig;
import southday.j2eework.sc.ustc.controller.di.bean.DIBean;
import southday.j2eework.sc.ustc.controller.di.bean.SCDI;
import southday.j2eework.sc.ustc.controller.factory.Factory;

public class DIConfiguration {
    private static final String DI_XML_PATH = getPathOfDIXML();
    private Factory<SCDI> fac = new SCDIFactory(DI_XML_PATH);
    private SCDI scdi;
    private Map<String, DIBean> idDIBeans;
    private Map<String, DIBean> clzzDIBeans;
    
    private DIConfiguration() {
        try {
            scdi = fac.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        idDIBeans = scdi.getDibeans();
        clzzDIBeans = new HashMap<>();
        for (Map.Entry<String, DIBean> e : idDIBeans.entrySet())
            clzzDIBeans.put(e.getValue().getClassName(), e.getValue());
    }
    
    private static class DIConfigurationHolder {
        private static DIConfiguration diconfig = new DIConfiguration();
    }
    
    public static DIConfiguration getDIConfiguration() {
        return DIConfigurationHolder.diconfig;
    }
    
    public DIBean getDIBeanByID(String id) {
        return idDIBeans.get(id);
    }
    
    public DIBean getDIBeanByClass(String clzz) {
        /* 当对象为动态代理生成的子类时，
         * clzz就可能为这类形式：southday.j2eework.water.ustc.action.LoginAction$$EnhancerByCGLIB$$2b939c0d
         * 为了能正确获取到DIBean，这里做个转换，把从$到后面的内容全部切掉，只保留父类类路径
         */
        int index = clzz.indexOf('$');
        return index < 0 ? clzzDIBeans.get(clzz) : clzzDIBeans.get(clzz.substring(0, index));
    }
    
    private static String getPathOfDIXML() {
        return BaseConfig.getBaseConfig().getFilePathInClassesDIR("di.xml");
    }
}
