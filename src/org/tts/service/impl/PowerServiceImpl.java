package org.tts.service.impl;

import org.tts.dao.PowerDao;
import org.tts.dao.impl.PowerDaoImpl;
import org.tts.entity.Power;
import org.tts.service.PowerService;

import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.service.impl
 * @createtime 2019-08-22 15:03
 */

public class PowerServiceImpl implements PowerService {
    PowerDao powerDao=new PowerDaoImpl();
    @Override
    public List<Power> getAllPower() {
        List<Power> powerList=powerDao.getAllPower();
        return powerList;
    }
}
