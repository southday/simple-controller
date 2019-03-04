package southday.j2eework.sc.ustc.controller.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import southday.j2eework.sc.ustc.controller.dao.DBConfiguration;
import southday.j2eework.sc.ustc.controller.orm.bean.Property;

public class DBUtil {
    private static BasicDataSource ds = DBConfiguration.getDataSource();
    
    public static boolean insert(String sql) throws Exception {
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        conn.commit();
        conn.close();
        return true;
    }
    
    /**
     * 目前只支持填充1个po对象，并且属性类型为Integer或String
     * @param po
     * @param rs
     * @param props
     * @throws Exception
     */
    public static void fillParams(Object po, ResultSet rs, Property... props) throws Exception {
        fillParams(po, rs, Arrays.asList(props));
    }
    
    public static void fillParams(Object po, ResultSet rs, List<Property> props) throws Exception {
        if (rs.next()) {
            for (Property prop : props) {
                String setter = CommonUtil.setterName(prop.getName());
                if (Integer.class.getName().equals(prop.getType())) {
                    int value = rs.getInt(prop.getColumn());
                    ReflectUtil.execute(po, setter, value);
                } else { // else 默认为String
                    String value = rs.getString(prop.getColumn());
                    ReflectUtil.execute(po, setter, value);
                }
            }
        }
    }
}
