package southday.j2eework.sc.ustc.controller.dao;

public abstract class BaseDAO {
    public abstract Object query(Object obj, String... queryAttrs) throws Exception;
    public abstract boolean insert(Object obj) throws Exception;
    public abstract boolean update(Object obj) throws Exception;
    public abstract boolean delete(Object obj) throws Exception;
}
