package southday.j2eework.water.ustc.service.impl;

import southday.j2eework.sc.ustc.controller.util.CommonUtil;
import southday.j2eework.water.ustc.bean.User;
import southday.j2eework.water.ustc.dao.UserDAO;
import southday.j2eework.water.ustc.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = UserDAO.getUserDAO();
    
    private UserServiceImpl() {}
    
    private static class UserServiceImplHolder {
        private static UserService userService = new UserServiceImpl();
    }
    
    public static UserService getUserService() {
        return UserServiceImplHolder.userService;
    }

    @Override
    public boolean login(User paramUser) throws Exception {
        if (!checkUserParam(paramUser))
            return false;
        User retUser = (User)userDAO.query(paramUser, "userName");
        return paramUser.getPassword().equals(retUser.getPassword());
    }

    @Override
    public boolean register(User paramUser) throws Exception {
        if (!checkUserParam(paramUser))
            return false;
        return userDAO.insert(paramUser);
    }
    
    private boolean checkUserParam(User user) {
        return user != null && CommonUtil.checkParam(user.getUserName(), user.getPassword());
    }
}
