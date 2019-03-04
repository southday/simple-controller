package southday.j2eework.sc.ustc.controller.config;

import java.util.Map;

import southday.j2eework.sc.ustc.controller.util.FileUtil;

/**
 * 保存基本配置信息（使用静态内部类实现单例，目前信息源：files-locations.properties）
 * @author southday
 * @date 2018年11月30日
 */
public class BaseConfig {
    private static final String FILES_LOCATIONS_PROPS_PATH = getPathOfFilesLocationsProps();
    private Map<String, String> props = null;
    
    private BaseConfig() {
        try {
            props = FileUtil.loadProperties(FILES_LOCATIONS_PROPS_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static class BaseConfigHolder {
        private static BaseConfig config = new BaseConfig();
    }
    
    public static BaseConfig getBaseConfig() {
        return BaseConfigHolder.config;
    }
    
    public String getValue(String key) {
        return props.get(key);
    }
    
    private static String getPathOfFilesLocationsProps() {
        return FileUtil.CLASSES_PATH + "config/files-locations.properties";
    }
    
    /**
     * 返回fileName在项目中的真实路径，该文件在xxx/UseSC/WEB-INF/classes/目录或子目录下</br>
     * 目前只用作配置文件真实路径的获取，比如：controller.xml，jdbc-mysql.properties等
     * @param fileName 文件名，如：controller.xml
     * @return
     */
    public String getFilePathInClassesDIR(String fileName) {
        return FileUtil.CLASSES_PATH + getValue(fileName);
    }
}
