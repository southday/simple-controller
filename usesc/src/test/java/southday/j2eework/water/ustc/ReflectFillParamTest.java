package southday.j2eework.water.ustc;

import southday.j2eework.sc.ustc.controller.util.ReflectUtil;
import southday.j2eework.water.ustc.bean.User;

public class ReflectFillParamTest {

    public static void main(String[] args) throws Exception {
        User user = new User();
        fill(user);
        System.out.println("user.name: " + user.getUserName());
        System.out.println("user.password: " + user.getPassword());
    }
    
    public static void fill(Object obj) throws Exception {
        ReflectUtil.setValue(obj, "userName", "lcx");
        ReflectUtil.setValue(obj, "password", "xcl");
    }
}
