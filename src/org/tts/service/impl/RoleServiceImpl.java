package org.tts.service.impl;

import org.tts.dao.PowerDao;
import org.tts.dao.RoleDao;
import org.tts.dao.impl.PowerDaoImpl;
import org.tts.dao.impl.RoleDaoImpl;
import org.tts.entity.Role;
import org.tts.service.RoleService;
import org.tts.util.JDBC;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.service.impl
 * @createtime 2019-08-21 17:57
 */

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao=new RoleDaoImpl();
    PowerDao powerDao=new PowerDaoImpl();
    @Override
    public List<Role> getRoleByPage(int page) {
        List<Role> roleList=roleDao.getRoleByPage(page);
        for (Role role : roleList) {
            String powerString=powerDao.getPowerString(role.getRid());
            role.setPowerString(powerString);
        }
        return roleList;
    }

    @Override
    public int getRoleCount() {
        return roleDao.getRoleCount();
    }

    @Override
    public boolean insertRole(Role role,String[] powerList) {
        Connection connection = null;
        try {
            connection= JDBC.getConnection();
            JDBC.startTransaction();
            int count=roleDao.insertRole(role,powerList);
            int rid=roleDao.getRoleId(role.getRname());
            role.setRid(rid);
            int rowCount=roleDao.insertRP(role,powerList);
            JDBC.commitTransaction();
            if(count>0&&rowCount==powerList.length){
                return true;
            }
        } catch (Exception e) {
            try {
                JDBC.rollBackTranaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBC.release(connection,null,null);
        }
        return false;
    }

    @Override
    public boolean deleteRole(int rid) {
        int count= 0;
        try {
            count = roleDao.deleteRole(rid);
            return count>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;
    }

    @Override
    public List<Integer> getPidByRid(int rid) {
        List<Integer> pidList=roleDao.getPidByRid(rid);
        return pidList;
    }

    @Override
    public boolean updateRole(Role role, String[] powerList) {
        Connection connection = null;
        try {
            connection= JDBC.getConnection();
            JDBC.startTransaction();
            int deleteCount=roleDao.deleteRole(role.getRid());
            int count=roleDao.insertRole(role,powerList);
            int rid=roleDao.getRoleId(role.getRname());
            role.setRid(rid);
            int rowCount=roleDao.insertRP(role,powerList);
            JDBC.commitTransaction();
            if(deleteCount>0&&count>0&&rowCount==powerList.length){
                return true;
            }
        } catch (Exception e) {
            try {
                JDBC.rollBackTranaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBC.release(connection,null,null);
        }
        return false;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }
}
