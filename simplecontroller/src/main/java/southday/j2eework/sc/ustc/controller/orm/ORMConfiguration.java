package southday.j2eework.sc.ustc.controller.orm;

import java.util.Map;

import southday.j2eework.sc.ustc.controller.config.BaseConfig;
import southday.j2eework.sc.ustc.controller.factory.Factory;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMapping;

public class ORMConfiguration {
    private static final String ORMAPPING_XML_PATH = getPathOfORMappingXML();
    private Factory<ORMapping> fac = new ORMappingFactory(ORMAPPING_XML_PATH);
    private ORMapping ormapping;
    private Map<String, ORMClass> classes;
    
    private ORMConfiguration() {
        try {
            ormapping = fac.create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        classes = ormapping.getOrmClasses();
    }
    
    private static class ORMConfigurationHolder {
        private static ORMConfiguration config = new ORMConfiguration();
    }
    
    public static ORMConfiguration getConfiguration() {
        return ORMConfigurationHolder.config;
    }
    
    public ORMClass getORMClass(String key) {
        /* 当对象为动态代理生成的子类时，
         * key就可能为这类形式：southday.j2eework.water.ustc.bean.User$$EnhancerByCGLIB$$4110e76b
         * 为了能正确获取到ORMClass，这里做个转换，把从$到后面的内容全部切掉，只保留父类类路径
         */
        int index = key.indexOf('$');
        return index < 0 ? classes.get(key) : classes.get(key.substring(0, index));
    }
    
    private static String getPathOfORMappingXML() {
        return BaseConfig.getBaseConfig().getFilePathInClassesDIR("or_mapping.xml");
    }
}
