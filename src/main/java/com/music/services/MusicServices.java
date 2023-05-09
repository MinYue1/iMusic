package com.music.services;

import com.music.dao.IMusicDao;
import com.music.pojo.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MusicServices {
    @Autowired
    private IMusicDao dao;

    /**
     * 查询所有歌曲
     * @return List<Music> - 所有歌曲集合
     */
    public List<Music> fandAll(){
        return dao.findAll();
    }

    /**
     * 查询所有歌手名
     * @return List<String> - 所有歌手名字集合
     */
    public List<String> singerAll(){
        return dao.Allsinger();
    }

    /**
     * 添加一首歌
     * @param music
     * @return - int
     */
    public int insOneMusic(Music music){

        //如果上传的歌手、类型、地区 不存在，则添加
        dao.addsingerName(music.getSingerNameToStr());
        dao.addmusicClass(music.getMusicClassToStr());
        dao.addsingArea(music.getSongAreaToStr());

        System.out.println("2："+music);
        //设置上传时间
        music.setUpLoadDate(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())));
        return dao.insOneMusic(music);
    }

    /**
     * 删除一条歌曲
     * @param id
     * @return - int
     */
    public int delOneMusic(String id){
        return dao.delOneMusic(id);
    }

    /**
     * 查询单个歌曲信息
     * @param id
     * @return - Music
     */
    public Music findOne(String id){
        return dao.findOne(id);
    }

    /**
     * 修改一条歌曲信息
     * @param music
     * @return
     */
    public int updateOneMusic(Music music){

        //查询修改的歌手、类型、地区 不存在，则添加
        dao.addsingerName(music.getSingerNameToStr());
        dao.addmusicClass(music.getMusicClassToStr());
        dao.addsingArea(music.getSongAreaToStr());

        return dao.updateOneMusic(music);
    }
 }
