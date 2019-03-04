package southday.j2eework.water.ustc.service;

import southday.j2eework.water.ustc.bean.User;

public interface UserService {
    boolean login(User paramUser) throws Exception;
    boolean register(User paramUser) throws Exception;
}
