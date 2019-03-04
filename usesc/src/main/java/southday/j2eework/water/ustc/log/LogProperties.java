package southday.j2eework.water.ustc.log;

import java.util.Map;

import southday.j2eework.sc.ustc.controller.config.BaseConfig;
import southday.j2eework.sc.ustc.controller.util.FileUtil;

public class LogProperties {
    private static final String LOG_PROPS_PATH = getPathOfLogProps();
    private Map<String, String> props = null;
    
    private LogProperties() {
        try {
            props = FileUtil.loadProperties(LOG_PROPS_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static class LogPropertiesHolder {
        private static LogProperties logProps = new LogProperties();
    }
    
    public static LogProperties getLogProperties() {
        return LogPropertiesHolder.logProps;
    }
    
    public String getValue(String key) {
        return props.get(key);
    }
    
    private static String getPathOfLogProps() {
        return BaseConfig.getBaseConfig().getFilePathInClassesDIR("log.properties");
    }
}
