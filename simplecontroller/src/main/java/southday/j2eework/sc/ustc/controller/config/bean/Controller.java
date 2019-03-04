package southday.j2eework.sc.ustc.controller.config.bean;

import java.util.List;

public class Controller {
    private List<Action> actions;

    /**
     * 查找Action
     * @param name
     * @return 不存在则返回null
     */
    public Action getAction(String name) {
        for (Action a : actions)
            if (name.equals(a.getActionName()))
                return a;
        return null;
    }
    
    public List<Action> getActions() {
        return actions;
    }
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
