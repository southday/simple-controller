package southday.j2eework.sc.ustc.controller.util;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 文件工具类
 * @author southday
 * @date 2018年12月1日
 */
public class FileUtil {
    public static final String CLASSES_PATH = getClassesPath();
    /**
     * 关闭文件流资源
     * @param c
     */
    public static void close(Closeable c) {
        try {
            if (c != null)
                c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 扫描propsFilePath文件，生成对应的Map
     * @param propsFilePath
     * @return
     */
    public static Map<String, String> loadProperties(String propsFilePath) throws IOException {
        Properties properties = getProperties(propsFilePath);
        if (properties == null)
            return null;
        Map<String, String> props = new HashMap<>();
        for (Map.Entry<Object, Object> e : properties.entrySet())
            props.put((String)e.getKey(), (String)e.getValue());
        return props;
    }
    
    public static Properties getProperties(String propsFilePath) throws IOException {
        Properties props = null;
        InputStream ins = null;
        try {
            ins = new BufferedInputStream(new FileInputStream(propsFilePath));
            props = new Properties();
            props.load(ins);
        } finally {
            close(ins);
        }
        return props;
    }
    
    /**
     * 获取类加载的路径（web应用运行时），用于合成要扫描的配置文件的文件路径</br>
     * 如有项目路径为：.../UseSC/WEB-INF/classes/...</br>
     * 执行此方法将获得路径：.../UseSC/WEB-INF/classes/
     * @return
     */
    private static String getClassesPath() {
        return FileUtil.class.getClassLoader().getResource("").getPath();
    }
}
