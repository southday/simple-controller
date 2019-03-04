package southday.j2eework.sc.ustc.controller.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import southday.j2eework.sc.ustc.controller.config.BaseConfig;
import southday.j2eework.sc.ustc.controller.util.FileUtil;

public class DBConfiguration {
    private static final String PROPERTIES = "jdbc-postgresql.properties";
//    private static final String PROPERTIES = "jdbc-mysql.properties";
    private static BasicDataSource ds;
    
    public static BasicDataSource getDataSource() {
        if (ds == null) {
            synchronized (DBConfiguration.class) {
                if (ds == null) {
                    String propsFilePath = getPathOfJDBCProps();
                    try {
                        ds = BasicDataSourceFactory.createDataSource(FileUtil.getProperties(propsFilePath));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return ds;
    }
    
    private static String getPathOfJDBCProps() {
        return BaseConfig.getBaseConfig().getFilePathInClassesDIR(PROPERTIES);
    }
}
