package southday.j2eework.sc.ustc.controller.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {
    
    public static Object newInstance(String className) throws Exception {
        Class<?> clzz = Class.forName(className);
        return clzz.getDeclaredConstructor().newInstance();
    }
    
    public static Object execute(String className, String methodName, Object... args) throws Exception {
        Class<?> clzz = Class.forName(className);
        Method method = clzz.getDeclaredMethod(methodName, getArgTypes(args));
        Object res = method.invoke(clzz.getDeclaredConstructor().newInstance(), args);
        return res;
    }
    
    public static Object execute(Object obj, String methodName, Object... args) throws Exception {
        Class<?> clzz = obj.getClass();
        Method method = clzz.getDeclaredMethod(methodName, getArgTypes(args));
        Object res = method.invoke(obj, args);
        return res;
    }
    
    public static Class<?>[] getArgTypes(Object... args) {
        List<Class<?>> argTypes = new ArrayList<>();
        for (Object arg : args)
            if (arg != null)
                argTypes.add(arg.getClass());
        Class<?>[] res = new Class<?>[argTypes.size()];
        return argTypes.toArray(res);
    }
    
    public static Object setValue(Object obj, String propName, Object arg) throws Exception {
        return execute(obj, CommonUtil.setterName(propName), arg);
    }
    
    public static Object getValue(Object obj, String propName) throws Exception {
        return execute(obj, CommonUtil.getterName(propName));
    }
}
