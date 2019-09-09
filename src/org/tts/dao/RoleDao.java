package org.tts.dao;

import org.tts.entity.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.dao
 * @createtime 2019-08-21 14:54
 */

public interface RoleDao {
    /**
     * 由管理员id获取管理员所拥有的所有角色字符串
     */
    public String getRoleString(int aid);

    /**
     * 分页查询角色信息
     * @param page 页数
     * @return 角色对象集合
     */
    public List<Role> getRoleByPage(int page);
    /**
     * 查询总共有多少个角色
     * @return 角色数
     */
    public int getRoleCount();
    /**
     * 添加角色
     * @param role 角色对象
     * @return  影响行数
     */
    public int insertRole(Role role,String[] powerList) throws SQLException;

    /**
     * 添加角色，权限关联表
     * @param role  角色对象
     * @param powerList  权限id的数组
     * @return 影响的行数
     * @throws SQLException
     */
    public int insertRP(Role role,String[] powerList)throws  SQLException;

    /**
     * 获得角色id
     * @param roleName 角色姓名
     * @return 角色id
     * @throws SQLException
     */
    public int getRoleId(String roleName) throws SQLException;

    /**
     * 删除角色
     * @param rid 角色id
     * @return 影响的行数
     */
    public int deleteRole(int rid) throws  SQLException ;

    /**
     * 根据角色id得到权限id的集合
     * @param rid 角色id
     * @return 权限id的集合
     */
    public List<Integer> getPidByRid(int rid);

    /**
     * 得到所有的角色
     * @return 角色集合
     */
    public List<Role> getAllRole();
}
