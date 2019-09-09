package org.tts.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xujin
 * @package-name util
 * @createtime 2019-08-22 15:51
 */

public class DBCP {
    /**
     * 数据源
     */
    private static DataSource DS;

    private static final String configFile="../../pro/dbcp.properties";

    private static   Properties properties=new Properties();
    static{
        try {
            properties.load(DBCP.class.getClassLoader().getResourceAsStream(configFile));
            DS=BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**从数据源获得一个连接*/
    public static   Connection getConnection(){
        Connection connection=null;
        if(DS!=null){
            try {
                connection=DS.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
        return null;
    }
}
