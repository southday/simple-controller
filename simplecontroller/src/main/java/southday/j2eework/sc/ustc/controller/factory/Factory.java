package southday.j2eework.sc.ustc.controller.factory;

/**
 * 用于创建对象
 * @author southday
 * @date 2018年11月29日
 */
public interface Factory<T> {
    T create() throws Exception;
}
