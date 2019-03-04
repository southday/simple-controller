package southday.j2eework.sc.ustc.controller.factory;

public class LoginAction_01 {
    
    public String handleLogin(String userName, String password) {
        System.out.println("userName = " + userName + ", password = " + password);
        if ("xcl".equals(userName) && "southday".equals(password))
            return "success";
        else
            return "failure";
    }
}
