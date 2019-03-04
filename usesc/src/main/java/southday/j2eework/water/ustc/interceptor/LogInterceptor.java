package southday.j2eework.water.ustc.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import southday.j2eework.water.ustc.log.LogWriter;
import southday.j2eework.water.ustc.log.bean.ActionLog;

public class LogInterceptor {
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public String preAction() throws Exception {
        return fmt.format(new Date());
    }
    
    public String afterAction(String actionName, String startTime, String result) throws Exception {
        ActionLog actionLog = new ActionLog();
        actionLog.setActionName(actionName);
        actionLog.setStartTime(startTime);
        actionLog.setEndTime(fmt.format(new Date()));
        actionLog.setResult(result);
        LogWriter.write(actionLog);
        return null;
    }
}
