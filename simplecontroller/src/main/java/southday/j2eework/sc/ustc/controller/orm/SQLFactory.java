package southday.j2eework.sc.ustc.controller.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.orm.bean.Property;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class SQLFactory {
    private static final ORMConfiguration config = ORMConfiguration.getConfiguration();
    
    public static String selectSQL(Object po, String... queryAttrs) throws Exception {
        ORMClass ormClzz = config.getORMClass(po.getClass().getName());
        List<String> nolazyCol = nolazyColumns(ormClzz);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        for (String col : nolazyCol)
            sql.append(col + ", ");
        sql.deleteCharAt(sql.length()-2); // 去掉最后多余的 ','
        // SELECT attr1, attr2 
        sql.append("FROM " + ormClzz.getTable() + " WHERE ");
        // SELECT attr1, attr2 FROM table WHERE 
        Map<String, Property> properties = ormClzz.getProperties();
        for (String attr : queryAttrs) {
            String col = properties.get(attr).getColumn();
            Object value = ReflectUtil.getValue(po, attr);
            if (value.getClass() == String.class)
                sql.append(col + " = '" + value.toString() + "' AND ");
            else
                sql.append(col + " = " + value.toString() + " AND ");
        }
        // 如果查询属性为空，则默认使用id查询
        if (queryAttrs.length <= 0) {
            String col = ormClzz.getId().getColumn(); // id基本都为Integer，下面不用判断是否为String类型
            Object value = ReflectUtil.getValue(po, ormClzz.getId().getName());
            sql.append(col + " = " + value.toString() + " AND ");
        }
        // SELECT attr1, attr2 FROM table WHERE a = 1 AND b = 'x' AND 
        sql.delete(sql.length()-5, sql.length()); // 去掉最后多余的' AND '
        // SELECT attr1, attr2 FROM table WHERE a = 1 AND b = 'x'
        return sql.toString();
    }
    
    private static List<String> nolazyColumns(ORMClass ormClzz) {
        List<String> nolazy = new ArrayList<>();
        for (Property prop : ormClzz.nolazyProps())
            nolazy.add(prop.getColumn());
        return nolazy;
    }
    
    /**
     * 默认使用po中的id进行查询
     * @param po
     * @param propName
     * @return
     * @throws Exception
     */
    public static String selectSQLByID(Object po, String propName) throws Exception {
        ORMClass ormClzz = config.getORMClass(po.getClass().getName());
        String col = ormClzz.getProperties().get(propName).getColumn();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(col).append(" FROM ").append(ormClzz.getTable());
        sql.append(" WHERE ").append(ormClzz.getId().getColumn()).append(" = ");
        Object value = ReflectUtil.getValue(po, ormClzz.getId().getName());
        sql.append(value.toString());
        return sql.toString();
    }
    
    public static String insertSQL(Object po) throws Exception {
        ORMClass ormClzz = config.getORMClass(po.getClass().getName());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(ormClzz.getTable()).append("(");
        // ISNERT INTO table(
        List<String> valueProps = new ArrayList<>();
        for (Map.Entry<String, Property> map : ormClzz.getProperties().entrySet()) {
            Property prop = map.getValue();
            sql.append(prop.getColumn() + ", ");
            valueProps.add(prop.getName());
        }
        // INSERT INTO table(attr1, attr2, 
        // 将最后的 ', ' 换为 ') '
        sql.replace(sql.length()-2, sql.length()-1, ")");
        sql.append("VALUES(");
        // INSERT INTO table(attr1, attr2) VALUES(
        for (String vprop : valueProps) {
            Object value = ReflectUtil.getValue(po, vprop);
            if (value.getClass() == String.class)
                sql.append("'" + value.toString() + "', ");
            else
                sql.append(value.toString() + ", ");
        }
        // INSERT INTO table(attr1, attr2) VALUES('lcx', 2, 
        // 将最后的 ', ' 换为 ') '
        sql.replace(sql.length()-2, sql.length(), ")");
        // INSERT INTO table(attr1, attr2) VALUES('lcx', 2)
        return sql.toString();
    }
}
