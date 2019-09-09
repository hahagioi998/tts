package org.tts.service;

import org.tts.entity.Admin;

import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.service
 * @create 2019-08-18 13:58
 */
public interface AdminService {
    /**
     * 登录判断
     * @param admin 管理员
     * @return 管理员
     */
    public Admin checkLogin(Admin admin);

    /**
     * 更新管理员的密码
     * @param aid 管理员的id
     * @param apassword 管理员的新密码
     * @return 是否更新成功
     */
    public boolean updatePassword(int aid,String apassword,String aname);
    /**
     * 更新管理员的基本信息
     * @param admin 管理员对象
     * @return 是否更新成功
     */
    public boolean updateAdmin(Admin admin,String updateuser);
    /**
     * 分页查询管理员的信息
     * @param page 页数
     * @return 管理员的集合
     */
    public List<Admin> getAdminByPage(int page);
    /**
     * 查询总共有多少个管理员
     * @return 管理员
     */
    public int getAdminCount();
}
