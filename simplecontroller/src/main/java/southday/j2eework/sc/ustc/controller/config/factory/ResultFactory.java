package southday.j2eework.sc.ustc.controller.config.factory;

import southday.j2eework.sc.ustc.controller.action.ActionIface;
import southday.j2eework.sc.ustc.controller.config.bean.Result;
import southday.j2eework.sc.ustc.controller.factory.Factory;

public class ResultFactory implements Factory<Result> {
    public static final Result NO_REQ_RESOURCES = noReqResourceResult();

    @Override
    public Result create() throws Exception {
        return null;
    }

    private static Result noReqResourceResult() {
        Result result = new Result();
        result.setName(ActionIface.FAILURE);
        result.setType(ActionIface.REDIRECT);
        result.setValue("/pages/no-req.resources.jsp");
        return result;
    }
}
