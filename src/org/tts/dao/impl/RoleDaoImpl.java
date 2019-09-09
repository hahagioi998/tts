package org.tts.dao.impl;

import org.tts.dao.RoleDao;
import org.tts.entity.Role;
import org.tts.util.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.dao.impl
 * @createtime 2019-08-21 14:56
 */

public class RoleDaoImpl implements RoleDao {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    @Override
    public String getRoleString(int aid) {
        String roleString=null;
        connection=JDBC.getConnection();
        String sql="select ar.aid,GROUP_CONCAT(ar.rname) as rStr " +
                "from (select a_r.aid,r.rname " +
                "from tts_a_r a_r,tts_role r where a_r.aid=? and a_r.rid=r.rid ) ar " +
                "GROUP BY ar.aid";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,aid);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                roleString=resultSet.getString(2);
            }
            return roleString;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public List<Role> getRoleByPage(int page) {
        List<Role> roleList=new ArrayList<>();
        connection=JDBC.getConnection();
        String sql="select rid,rname from tts_role limit ?,?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,(page-1)*Role.PAGE_SIZE);
            preparedStatement.setInt(2,Role.PAGE_SIZE);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Role role=new Role();
                role.setRid(resultSet.getInt(1));
                role.setRname(resultSet.getString(2));
                roleList.add(role);
            }
            return roleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public int getRoleCount() {
        int count=0;
        connection=JDBC.getConnection();
        String sql="select count(rid) from tts_role";
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                count= resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int insertRole(Role role,String[] powerList) throws SQLException {
        int count=0;
        connection=JDBC.getConnection();
        String sql="insert into tts_role (rname,createuser,createtime) values (?,?,now())";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,role.getRname());
            preparedStatement.setString(2,role.getCreateuser());
            count=preparedStatement.executeUpdate();
            return count;
        }finally {
            JDBC.release(null, preparedStatement, null);
        }
    }

    @Override
    public int insertRP(Role role, String[] powerList) throws SQLException {
        connection=JDBC.getConnection();
        String sql="insert into tts_r_p (rid,pid,createuser,createtime) values (?,?,?,now())";
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < powerList.length; i++) {
                preparedStatement.setInt(1,role.getRid());
                preparedStatement.setInt(2,Integer.parseInt(powerList[i]));
                preparedStatement.setString(3,role.getCreateuser());
                preparedStatement.addBatch();
            }
            int[] rows=preparedStatement.executeBatch();
            return rows.length;
        }finally {
            JDBC.release(null,preparedStatement,null);
        }
    }

    @Override
    public int getRoleId(String roleName) throws SQLException {
        int rid=0;
        connection=JDBC.getConnection();
        String sql="select rid from tts_role where rname=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,roleName);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                rid=resultSet.getInt(1);
            }
            return rid;
        }finally {
            JDBC.release(null, preparedStatement, resultSet);
        }

    }

    @Override
    public int deleteRole(int rid) throws SQLException {
        int count=0;
        connection=JDBC.getConnection();
        String sql="delete from tts_role where rid=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,rid);
            count=preparedStatement.executeUpdate();
            return count;
        } finally {
            JDBC.release(null,preparedStatement,null);
        }

    }

    @Override
    public List<Integer> getPidByRid(int rid) {
        List<Integer> pidList=new ArrayList<>();
        connection=JDBC.getConnection();
        String sql="select pid from tts_r_p where rid=?";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,rid);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                pidList.add(resultSet.getInt(1));
            }
            return pidList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pidList;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roleList=new ArrayList<>();
        String sql="select rid,rname from tts_role";
        connection=JDBC.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Role role=new Role();
                role.setRid(resultSet.getInt("rid"));
                role.setRname(resultSet.getString("rname"));
                roleList.add(role);
            }
            return  roleList;
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return  roleList;
    }


}
