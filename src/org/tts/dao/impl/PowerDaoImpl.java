package org.tts.dao.impl;

import org.tts.dao.PowerDao;
import org.tts.entity.Power;
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
 * @create 2019-08-18 14:06
 */
public class PowerDaoImpl implements PowerDao {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    @Override
    public List<Power> getPower(int aid) {
        List<Power> powerList=new ArrayList<>();
        connection=JDBC.getConnection();
        String sql="select distinct pid,pclass,purl,by2 " +
                   "from tts_power where pid in (select pid " +
                   "from tts_r_p where rid in (select rid " +
                   "from tts_a_r where aid=?))";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,aid);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Power power=new Power();
                power.setPid(resultSet.getInt("pid"));
                power.setPclass(resultSet.getString("pclass"));
                power.setPurl(resultSet.getString("purl"));
                power.setBy2(resultSet.getString("by2"));
                powerList.add(power);
            }
            return powerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return powerList;
    }

    @Override
    public String getPowerString(int rid) {
        String powerString=null;
        connection=JDBC.getConnection();
        String sql="select rp.rid,GROUP_CONCAT(rp.by1) as rStr " +
                "FROM (select r_p.rid,p.by1 " +
                "from tts_r_p r_p,tts_power p where r_p.rid=? and r_p.pid=p.pid) rp GROUP BY rp.rid";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,rid);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                powerString=resultSet.getString(2);
            }
            return powerString;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public List<Power> getAllPower() {
        List<Power> powerList=new ArrayList<>();
        connection=JDBC.getConnection();
        String sql="select pid,by1 from tts_power";
        try {
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Power power=new Power();
                power.setPid(resultSet.getInt("pid"));
                power.setBy1(resultSet.getString("by1"));
                powerList.add(power);
            }
            return powerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.release(connection,preparedStatement,resultSet);
        }
        return powerList;
    }
}
