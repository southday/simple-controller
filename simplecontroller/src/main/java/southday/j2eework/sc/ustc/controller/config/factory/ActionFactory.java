package southday.j2eework.sc.ustc.controller.config.factory;

import java.util.ArrayList;
import java.util.List;

import southday.j2eework.sc.ustc.controller.action.ActionIface;
import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Result;
import southday.j2eework.sc.ustc.controller.factory.Factory;

public class ActionFactory implements Factory<Action> {
    public static Action DEFAULT_ACTION = defaultAction();

    @Override
    public Action create() throws Exception {
        return null;
    }

    private static Action defaultAction() {
        Action action = new Action();
        action.setActionName("default");
        action.setClassName("southday.j2eework.sc.ustc.controller.action.DefaultAction");
        action.setMethodName("execute");
        Result result = new Result();
        result.setName(ActionIface.FAILURE);
        result.setType(ActionIface.REDIRECT);
        // 与UseSC紧耦合
        result.setValue("/pages/unkonwn-action.jsp");
        List<Result> resList = new ArrayList<>();
        resList.add(result);
        action.setResults(resList);
        return action;
    }
    
}
