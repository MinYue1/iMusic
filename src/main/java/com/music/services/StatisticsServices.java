package com.music.services;

import com.music.dao.IStatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServices {
    @Autowired
    private IStatisticsDao dao;
    /**
     * 查询用户总数
     * @return int
     */
    public int UserSums(){
        return dao.UserSums();
    }

    /**
     * 查询歌曲总数
     * @return int
     */
    public int MusicSums(){
        return dao.MusicSums();
    }

    /**
     * 查询今日新增用户数
     * @return int
     */
    public int NewUsers(){
        return dao.NewUsers();
    }

    /**
     * 查询今日新增歌曲总数
     * @return int
     */
    public int NewMusics(){
        return dao.NewMusics();
    }

}
