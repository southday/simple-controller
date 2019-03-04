package southday.j2eework.water.ustc.db;

import java.util.concurrent.ConcurrentHashMap;

import southday.j2eework.water.ustc.bean.User;

/**
 * 简易UserDB，使用静态内部类在实现单例
 * @author southday
 * @date 2018年11月30日
 */
public class SimpleUserDB {
    private final ConcurrentHashMap<String, User> db = new ConcurrentHashMap<>();
    
    private SimpleUserDB() {}
    
    private static class SimpleUserDBHolder {
        private static SimpleUserDB userDB = new SimpleUserDB();
    }
    
    /**
     * 返回SimpleUserDB实例（单例）
     * @return
     */
    public static SimpleUserDB getSimpleUserDB() {
        return SimpleUserDBHolder.userDB;
    }
    
    /**
     * 检查用户登陆
     * @param userName
     * @param password
     * @return
     */
    public boolean checkLogin(String userName, String password) throws Exception {
        User user = db.get(userName);
        return user != null && password.equals(user.getPassword());
    }
    
    /**
     * 用户注册
     * @param userName
     * @param password
     * @return
     */
    public boolean register(String userName, String password) throws Exception {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        db.putIfAbsent(userName, user);
        return true;
    }
    
}
