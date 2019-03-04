package southday.j2eework.water.ustc.action;

import southday.j2eework.sc.ustc.controller.action.ActionSupport;
import southday.j2eework.water.ustc.bean.User;
import southday.j2eework.water.ustc.service.UserService;
import southday.j2eework.water.ustc.service.impl.UserServiceImpl;

public class RegisterAction extends ActionSupport {
    private User user;
    private UserService userService = UserServiceImpl.getUserService();
    
    public String handleRegister() throws Exception {
        return userService.register(user) ? SUCCESS : FAILURE;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
