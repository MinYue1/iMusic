package com.music.dao;

import com.music.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMusicDao {

    /**
     * 查询所有歌手信息
     * @return List<Music> - 歌手信息
     */
    List<Music> findAll();

    /**
     * 查询所有歌手名
     * @return List<String>
     */
    List<String> Allsinger();

    /**
     * 添加一首歌
     * @param music
     * @return
     */
    int insOneMusic(@Param("music") Music music);

    /**
     * 添加一名歌手信息 （不存在则添加
     * @param singername
     * @return
     */
    int addsingerName(@Param("singername")String singername);

    /**
     * 添加一种歌曲类型 （不存在则添加
     * @param musicclass
     * @return
     */
    int addmusicClass(@Param("musicclass")String musicclass);

    /**
     * 添加一种歌曲地区（不存在则添加
     * @param songarea
     * @return
     */
    int addsingArea(@Param("songarea")String songarea);

    /**
     * 删除一条歌曲
     * @param id
     * @return
     */
    int delOneMusic(@Param("id")String id);

    /**
     * 查找一首歌曲信息
     * @param id
     * @return - Music
     */
    Music findOne(@Param("id")String id);


    int updateOneMusic(@Param("music")Music music);
}
