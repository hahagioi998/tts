package org.tts.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author xujin
 * @package-name org.tts.util
 * @create 2019-08-18 10:00
 */
public class JDBC {
    //连接与线程绑定ThreadLocal的Map里放的是<Thread,Connection>
    private static final ThreadLocal<Connection> tl=new ThreadLocal<Connection>();

    public static Connection getConnection(){
        Connection conn = null;
        if(tl.get()==null){
            conn = DBCP.getConnection();
            tl.set(conn); //获取线程时连接放入线程
        }
        return tl.get();//取出线程绑定的连接
    }
    public static  void release(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                resultSet=null;
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                statement=null;
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection=null;
                tl.remove();
            }
        }
    }
    public static void startTransaction() throws SQLException {
        Connection conn = null;
        conn = tl.get();
        if(conn==null){
            conn=DBCP.getConnection();
            tl.set(conn);
        }
        conn.setAutoCommit(false);//线程绑定的连接设置不自动提交

    }

    public static void commitTransaction() throws SQLException{
        Connection connection = tl.get();
        connection.commit();   ///线程绑定的连接提交事务
    }

    public static  void  rollBackTranaction() throws SQLException{
        Connection connection = tl.get();
        connection.rollback();//线程绑定的连接回滚
    }


}
