package southday.j2eework.water.ustc;

import java.io.File;

public class ReadConfigFileTest {
    public static void main(String[] args) {
        String path = "WEB-INF/config/controller.xml";
        File file = new File(path);
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
    }
}
