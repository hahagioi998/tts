package org.tts.service.impl;

import org.tts.dao.AdminDao;
import org.tts.dao.PowerDao;
import org.tts.dao.RoleDao;
import org.tts.dao.impl.AdminDaoImpl;
import org.tts.dao.impl.PowerDaoImpl;
import org.tts.dao.impl.RoleDaoImpl;
import org.tts.entity.Admin;
import org.tts.entity.Power;
import org.tts.service.AdminService;

import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.service.impl
 * @create 2019-08-18 13:59
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao=new AdminDaoImpl();
    private PowerDao powerDao=new PowerDaoImpl();
    private RoleDao roleDao=new RoleDaoImpl();
    @Override
    public Admin checkLogin(Admin admin) {
        Admin admin1=adminDao.checkLogin(admin);
        if(admin1!=null){
            List<Power> powerList=powerDao.getPower(admin1.getAid());
            if(powerList!=null&&powerList.size()>0) {
                String roleString=roleDao.getRoleString(admin1.getAid());
                admin1.setRoleString(roleString);
                admin1.setPowerList(powerList);
                return admin1;
            }else{
                return admin1;
            }
        }else{
            return admin1;
        }
    }

    @Override
    public boolean updatePassword(int aid, String apassword,String aname) {
        int count=adminDao.updatePassword(aid,apassword,aname);
        return count>0?true:false;
    }

    @Override
    public boolean updateAdmin(Admin admin,String updateuser) {
        int count=adminDao.updateAdmin(admin,updateuser);
        return count>0?true:false;
    }

    @Override
    public List<Admin> getAdminByPage(int page) {
        List<Admin> adminList=adminDao.getAdminByPage(page);
        if(adminList!=null&&adminList.size()>0){
            for (Admin admin : adminList) {
                String roleString=roleDao.getRoleString(admin.getAid());
                admin.setRoleString(roleString);
            }
        }
        return  adminList;
    }

    @Override
    public int getAdminCount() {
        return adminDao.getAdminCount();
    }
}
