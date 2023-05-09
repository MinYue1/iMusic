package com.music.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStatisticsDao {

    /**
     * 总用户数
     * @return int
     */
    int UserSums();

    /**
     * 总歌曲数
     * @return int
     */
    int MusicSums();

    /**
     * 今日新增用户数
     * @return int
     */
    int NewUsers();

    /**
     * 今日新增歌曲数
     * @return int
     */
    int NewMusics();

}
