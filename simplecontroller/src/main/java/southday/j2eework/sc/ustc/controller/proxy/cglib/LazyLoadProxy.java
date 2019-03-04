package southday.j2eework.sc.ustc.controller.proxy.cglib;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import southday.j2eework.sc.ustc.controller.dao.DBConfiguration;
import southday.j2eework.sc.ustc.controller.orm.SQLFactory;
import southday.j2eework.sc.ustc.controller.orm.bean.ORMClass;
import southday.j2eework.sc.ustc.controller.orm.bean.Property;
import southday.j2eework.sc.ustc.controller.util.CommonUtil;
import southday.j2eework.sc.ustc.controller.util.DBUtil;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class LazyLoadProxy implements MethodInterceptor {
    private Object target;
    private ORMClass ormClzz;
    private BasicDataSource ds = DBConfiguration.getDataSource();
    
    public LazyLoadProxy(Object target, ORMClass ormClzz) {
        this.target = target;
        this.ormClzz = ormClzz;
    }
    
    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String methodName = method.getName();
        Object originalValue = proxy.invokeSuper(obj, args);
        if (!isNeedProxy(methodName) || originalValue != null)
            return originalValue;
        Object idvalue = ReflectUtil.getValue(obj, ormClzz.getId().getName());
        if (idvalue == null)
            return originalValue;
        String propName = CommonUtil.getPropNameFromSetterOrGetter(methodName);
        String sql = SQLFactory.selectSQLByID(obj, propName);
        System.out.println("[LazyLoadProxy]-[intercept] SQL: " + sql);
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Property prop = ormClzz.getProperties().get(propName);
        // 目前只支持Integer, String属性getXXX()方法的代理，代理其他类型的getter方法时会出错
        DBUtil.fillParams(obj, rs, prop);
        conn.close(); // 在rs使用完毕后关闭conn
        return proxy.invokeSuper(obj, args);
    }

    private boolean isNeedProxy(String methodName) {
        // 只对getXXX()做代理
        if (methodName.indexOf("get") != 0)
            return false;
        // 只对XXX设置为lazyload做代理
        Map<String, Property> properties = ormClzz.getProperties();
        String propName = CommonUtil.getPropNameFromSetterOrGetter(methodName);
        if (propName.equals(ormClzz.getId().getName()))
            return ormClzz.getId().isLazy();
        else
            return properties.get(propName).isLazy();
    }
}
