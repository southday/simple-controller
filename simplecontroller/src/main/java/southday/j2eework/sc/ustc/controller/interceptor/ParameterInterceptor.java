package southday.j2eework.sc.ustc.controller.interceptor;

import java.util.Map;

import southday.j2eework.sc.ustc.controller.di.DIConfiguration;
import southday.j2eework.sc.ustc.controller.di.bean.DIBean;
import southday.j2eework.sc.ustc.controller.di.bean.DIField;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class ParameterInterceptor {
    private static final DIConfiguration diconfig = DIConfiguration.getDIConfiguration();
    
    public static void fillParams(Object obj, Map<String, String[]> map) {
        try {
            for (Map.Entry<String, String[]> e : map.entrySet()) {
                String paramName = e.getKey();
                String value = e.getValue()[0];
                ReflectUtil.setValue(obj, paramName, value);
            }
        } catch (NoSuchMethodException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 特例，针对user填充参数
     * @param obj
     * @param map
     */
    public static void fillUserParams(Object obj, Map<String, String[]> map) {
        if (!isNeedFillUserParams(obj))
            return;
        Object user = null;
        try {
            user = ReflectUtil.getValue(obj, "user");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fillParams(user, map);
    }
    
    private static boolean isNeedFillUserParams(Object obj) {
        DIBean dibean = diconfig.getDIBeanByClass(obj.getClass().getName());
        if (dibean == null || dibean.getDifields().size() <= 0)
            return false;
        for (DIField f : dibean.getDifields())
            if ("user".equals(f.getName()))
                return true;
        return false;
    }
}
