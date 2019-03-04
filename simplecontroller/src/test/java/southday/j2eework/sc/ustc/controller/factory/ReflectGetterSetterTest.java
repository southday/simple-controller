package southday.j2eework.sc.ustc.controller.factory;

import java.lang.reflect.Method;
import java.util.List;

import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

class Dog {
    public void foo(String name, String age, String sex) {
        System.out.println(name + ", " + age + ", " + sex);
    }
}

/**
 * 反射获取一个Bean对象的所有getter和setter方法，并通过一定规则来设置对象属性值
 * @author southday
 * @date 2018年12月19日
 */
public class ReflectGetterSetterTest {
    
    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        ReflectUtil.execute(dog, "foo", "haha", null, "22");
//        joo();
    }
    
    public static void foo(List<String> ss) {
        for (String s : ss)
            System.out.println(s);
    }
    
    public static void goo(String... ss) {
        for (String s : ss)
            System.out.println(s);
    }
    
    public static void joo() throws Exception {
        Dog d = new Dog();
        Class<?> clzz = d.getClass();
        Method[] ms = clzz.getDeclaredMethods();
        for (Method m : ms) {
            String mname = m.getName();
            System.out.println(mname);
            Class<?>[] paramTypes = m.getParameterTypes();
            for (Class<?> z : paramTypes) {
                System.out.println(z.getName());
            }
        }
    }
}
