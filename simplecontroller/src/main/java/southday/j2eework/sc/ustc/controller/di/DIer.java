package southday.j2eework.sc.ustc.controller.di;

import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.di.bean.DIBean;
import southday.j2eework.sc.ustc.controller.di.bean.DIField;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class DIer {
    private static final DIConfiguration diconfig = DIConfiguration.getDIConfiguration();
    
    public static void inject(Object target, Action action) {
        DIBean dibean = diconfig.getDIBeanByClass(action.getClassName());
        if (dibean == null || dibean.getDifields().size() <= 0)
            return;
        try {
            for (DIField difield : dibean.getDifields()) {
                DIBean ref = diconfig.getDIBeanByID(difield.getBeanRef());
                if (ref == null)
                    continue;
                Object obj = ReflectUtil.newInstance(ref.getClassName());
                ReflectUtil.setValue(target, difield.getName(), obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
