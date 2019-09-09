package org.tts.service;

import org.tts.entity.Power;

import java.util.List;

/**
 * @author xujin
 * @package-name org.tts.service
 * @createtime 2019-08-22 15:02
 */

public interface PowerService {
    /**
     * 得到全部的权限信息
     * @return  权限集合
     */
    public List<Power> getAllPower();
}
