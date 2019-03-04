package southday.j2eework.sc.ustc.controller.factory;

class A {
    B b = new B();
}

class B {
    A a = new A();
}

public class CycleRefTest {

    public static void main(String[] args) {
        A a = new A();
    }
}
