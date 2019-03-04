package southday.j2eework.sc.ustc.controller.factory;

import java.util.ArrayList;
import java.util.List;

import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Controller;
import southday.j2eework.sc.ustc.controller.config.bean.Result;

public class BeanGenerator {

    public static Controller getController() {
        Controller controller = new Controller();
        List<Action> actions = new ArrayList<>();
        Action a1 = new Action();
        a1.setActionName("login");
        a1.setClassName("southday.j2eework.water.ustc.action.LoginActio");
        a1.setMethodName("handleLogin");
        
        List<Result> results = new ArrayList<>();
        Result r1 = new Result();
        r1.setName("success");
        r1.setType("forward");
        r1.setValue("pages/welcome.jsp");
        Result r2 = new Result();
        r2.setName("failure");
        r2.setType("redirect");
        r2.setValue("pages/failure.jsp");
        
        results.add(r1);
        results.add(r2);
        a1.setResults(results);
        actions.add(a1);
        controller.setActions(actions);
        
        return controller;
    }
    
}
