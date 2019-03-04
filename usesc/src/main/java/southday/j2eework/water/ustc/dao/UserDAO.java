package southday.j2eework.water.ustc.dao;

import southday.j2eework.sc.ustc.controller.dao.BaseDAO;
import southday.j2eework.sc.ustc.controller.orm.ORMConversation;

public class UserDAO extends BaseDAO {
    private ORMConversation conv = ORMConversation.getConversation();
    
    private UserDAO() {}
    
    private static class UserDAOHolder {
        private static UserDAO usrDAO = new UserDAO();
    }
    
    public static UserDAO getUserDAO() {
        return UserDAOHolder.usrDAO;
    }

    @Override
    public Object query(Object obj, String... queryAttrs) throws Exception {
        return conv.getObject(obj, queryAttrs);
    }

    @Override
    public boolean insert(Object obj) throws Exception {
        return conv.addObject(obj);
    }

    @Override
    public boolean update(Object obj) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object obj) throws Exception {
        return false;
    }
}
