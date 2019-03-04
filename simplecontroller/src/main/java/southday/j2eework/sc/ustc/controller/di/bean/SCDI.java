package southday.j2eework.sc.ustc.controller.di.bean;

import java.util.Map;

public class SCDI {
    private Map<String, DIBean> dibeans;

    public Map<String, DIBean> getDibeans() {
        return dibeans;
    }
    public void setDibeans(Map<String, DIBean> dibeans) {
        this.dibeans = dibeans;
    }
}
