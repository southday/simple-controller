package southday.j2eework.sc.ustc.controller.factory;

import southday.j2eework.sc.ustc.controller.util.CommonUtil;

public class VariableArgTest {
    public static void main(String[] args) {
//        foo();
        String x = "getName";
//        System.out.println(CommonUtil.getPropNameFromSetterOrGetter(x));
        if (x.equals(null))
            System.out.println("xxx");
    }
    
    public static void foo(Object... objs) {
        if (objs == null)
            System.out.println("xxx");
        for (Object o : objs) {
            System.out.println(o);
        }
        System.out.println(objs.getClass());
        System.out.println(objs.length);
    }
}
