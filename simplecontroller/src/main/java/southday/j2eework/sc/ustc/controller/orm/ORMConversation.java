package southday.j2eework.sc.ustc.controller.orm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

import southday.j2eework.sc.ustc.controller.dao.DBConfiguration;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.proxy.ProxyFactory;
import southday.j2eework.sc.ustc.controller.util.DBUtil;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class ORMConversation {
    private ORMConfiguration config = ORMConfiguration.getConfiguration();
    private BasicDataSource ds = DBConfiguration.getDataSource();
    
    private ORMConversation() {}
    
    private static class ORMConversationHolder {
        private static ORMConversation conv = new ORMConversation();
    }
    
    public static ORMConversation getConversation() {
        return ORMConversationHolder.conv;
    }
    
    public Object getObject(Object paramObj, String... queryAttrs) throws Exception {
        Class<?> clzz = paramObj.getClass();
        ORMClass ormClzz = config.getORMClass(clzz.getName());
        Object target = ReflectUtil.newInstance(clzz.getName());
        Object proxy = ProxyFactory.newLazyLoadPoxyInstance(target, ormClzz);
        String sql = SQLFactory.selectSQL(paramObj, queryAttrs);
        System.out.println("[Conversation]-[getObject] SQL: " + sql);
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DBUtil.fillParams(proxy, rs, ormClzz.nolazyProps());
        conn.close(); // 在rs使用完毕后关闭conn
        return proxy;
    }
    
    public boolean addObject(Object paramObj) throws Exception {
        String sql = SQLFactory.insertSQL(paramObj);
        System.out.println("[Conversation]-[getObject] SQL: " + sql);
        return DBUtil.insert(sql);
    }
}
