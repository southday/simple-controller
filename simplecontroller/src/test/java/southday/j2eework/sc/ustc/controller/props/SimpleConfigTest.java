package southday.j2eework.sc.ustc.controller.props;

import java.io.File;

public class SimpleConfigTest {
    
    public static void main(String[] args) {
//        private static final String FILES_LOCATIONS_PROPS_PATH = "src/test/java/southday/j2eework/sc/ustc/controller/props/files-locations.properties";
//        BaseConfig config = BaseConfig.getBaseConfig();
        
//        System.out.println(config.getValue(BaseConfig.CONTROLLER_XML));
        File f = new File("ss.txt");
        System.out.println(f.getAbsolutePath());
        System.out.println(SimpleConfigTest.class.getClassLoader().getResource("").getPath());
        
    }
}
