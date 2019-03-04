package southday.j2eework.sc.ustc.controller.util;

public class CommonUtil {
    
    public static boolean checkParam(String str) {
        return str != null && !"".equals(str.trim());
    }
    
    public static boolean checkParam(String... strs) {
        for (String s : strs)
            if (s == null || "".equals(s.trim()))
                return false;
        return true;
    }
    
    public static String setterName(String paramName) {
        return "set" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1, paramName.length());
    }
    
    public static String getterName(String paramName) {
        return "get" + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1, paramName.length());
    }
    
    public static String getPropNameFromSetterOrGetter(String methodName) {
        return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4, methodName.length());
    }
}
