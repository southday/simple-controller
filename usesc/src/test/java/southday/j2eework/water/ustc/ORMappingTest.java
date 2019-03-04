package southday.j2eework.water.ustc;

import javax.security.auth.login.Configuration;

import southday.j2eework.sc.ustc.controller.orm.SQLFactory;
import southday.j2eework.water.ustc.bean.User;

public class ORMappingTest {
    public static void main(String[] args) throws Exception {
        Configuration config = Configuration.getConfiguration();
        User user = new User();
        user.setUserName("lcx");
        user.setUserId(17);
        user.setPassword("xcl");
//        String sql = SQLFactory.selectSQL(user, "userName");
//        String sql = SQLFactory.selectSQLByID(user, "password");
//        String sql = SQLFactory.insertSQL(user);
//        System.out.println(sql);
        System.out.println(user.getClass().getName());
    }
}
