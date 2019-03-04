package southday.j2eework.water.ustc;

import java.util.Arrays;
import java.util.List;

import southday.j2eework.water.ustc.log.LogWriter;
import southday.j2eework.water.ustc.log.bean.ActionLog;

public class LogWriterTest {
    
    public static void main(String[] args) {
        for (ActionLog al : getActionLogs())
            LogWriter.write(al);
    }
    
    public static List<ActionLog> getActionLogs() {
        ActionLog al = new ActionLog();
        al.setActionName("login");
        al.setStartTime("2013-12-04 13:02:40");
        al.setEndTime("2013-12-04 13:02:50");
        al.setResult("success");
        
        ActionLog al2 = new ActionLog();
        al2.setActionName("register");
        al2.setStartTime("2013-12-05 18:02:40");
        al2.setEndTime("2013-12-05 18:02:50");
        al2.setResult("failure");
        
        return Arrays.asList(al, al2);
    }
}
