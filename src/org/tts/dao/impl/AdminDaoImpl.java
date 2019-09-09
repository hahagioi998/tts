package org.tts.dao.impl;

import org.tts.dao.AdminDao;
import org.tts.entity.Admin;
import org.tts.entity.Role;
import org.tts.util.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.dao.impl
 * @create 2019-08-18 13:40
 */
public class AdminDaoImpl implements AdminDao{
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    @Override
    public Admin checkLogin(Admin admin) {
        connection=JDBC.getConnection();
        String sql="select aid,aname,auname,apassword,aimage,atel,aemail,createtime from" +
                " tts_admin where auname=? and apassword=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,admin.getAuname());
            preparedStatement.setString(2,admin.getApassword());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                Admin admin1 = new Admin();
                admin1.setAid(resultSet.getInt("aid"));
                admin1.setAname(resultSet.getString("aname"));
                admin1.setAuname(resultSet.getString("auname"));
                admin1.setApassword(resultSet.getString("apassword"));
                admin1.setAimage(resultSet.getString("aimage"));
                admin1.setAtel(resultSet.getString("atel"));
                admin1.setAemail(resultSet.getString("aemail"));
                admin1.setCreatetime(resultSet.getString("createtime"));

                return admin1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public int updatePassword(int aid,String apassword,String aname) {
        int count=0;
        connection= JDBC.getConnection();
        String sql="update tts_admin set apassword=?,updatetime=now(),updateuser=? where aid=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(3,aid);
            preparedStatement.setString(2,aname);
            preparedStatement.setString(1,apassword);
            count=preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public int updateAdmin(Admin admin,String updateuser) {
        int count=0;
        connection=JDBC.getConnection();
        String sql="update tts_admin set aname=?,atel=?,aemail=?,updateuser=?,updatetime=now() where auname=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,admin.getAname());
            preparedStatement.setString(2,admin.getAtel());
            preparedStatement.setString(3,admin.getAemail());
            preparedStatement.setString(4,updateuser);
            preparedStatement.setString(5,admin.getAuname());
            count=preparedStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,null);
        }
        return count;
    }

    @Override
    public List<Admin> getAdminByPage(int page) {
        List<Admin> adminList=new ArrayList<>();
        connection=JDBC.getConnection();
        String sql="select aid,aname,auname,atel,aemail,by4 from tts_admin LIMIT ?,?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,(page-1)*Admin.PAGE_SIZE);
            preparedStatement.setInt(2,Admin.PAGE_SIZE);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Admin admin=new Admin();
                admin.setAid(resultSet.getInt("aid"));
                admin.setAname(resultSet.getString("aname"));
                admin.setAuname(resultSet.getString("auname"));
                admin.setAtel(resultSet.getString("atel"));
                admin.setAemail(resultSet.getString("aemail"));
                admin.setBy4(resultSet.getString("by4"));
                adminList.add(admin);
            }
            return adminList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return adminList;
    }

    @Override
    public int getAdminCount() {
        int count=0;
        connection=JDBC.getConnection();
        String sql="select count(aid) from tts_admin";
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                count= resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return count;
    }


}
