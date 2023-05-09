package com.music.controller;

import com.music.pojo.Music;
import com.music.pojo.User;
import com.music.services.MusicServices;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes(value = {"userSession","message"})
public class MusicController {
    @Autowired
    private MusicServices musicServices;

    @GetMapping("/musiclist")
    public String fandAll(Map map){
        System.out.println("!i come in!");

        //查询所有歌手
        map.put("singerAll",musicServices.singerAll());
        //查询所有歌曲信息
        map.put("musiclist",musicServices.fandAll());

        return "admin/musiclist.html";
    }

    @GetMapping("/addmusic")
    public String addmusic(){
        return "admin/musicadd.html";
    }

    @GetMapping("/delOneMusic")
    public String delOneMusic(String id,Map map){
        if(musicServices.delOneMusic(id) > 0){
            map.put("message","删除成功");
        }

        //查询所有歌手
        map.put("singerAll",musicServices.singerAll());
        //查询所有歌曲信息
        map.put("musiclist",musicServices.fandAll());

        return "admin/musiclist.html";
    }

    int TemporaryMusicId = 0;//临时用户id(用于修改用户信息时验证),防止前台篡改
    @GetMapping("/musiclookOne")
    public String musiclookOne(String id,int method,Map map){

        String path = "";
        //查询单个歌手数据
        Music m = musicServices.findOne(id);
        TemporaryMusicId = m.getId();
        map.put("m",m);

        if(method == 1){
            path = "admin/userview.html";
        }else if(method == 2){
            //歌手下拉列表
            map.put("MusicRoleNames",musicServices.singerAll());
            path = "admin/musicmodify.html";
        }
        return path;
    }

    @PostMapping("/updateMusic")
    public String updateMusic(Music music,Map map){

        //比较上次查找单个user的id,防止前台篡改
        if(music.getId() != TemporaryMusicId)music.setId(TemporaryMusicId);
        if(musicServices.updateOneMusic(music) > 0){
            System.out.println("修改成功");
            //前端消息
            map.put("message","修改歌曲成功");
            //查询所有歌手
            map.put("singerAll",musicServices.singerAll());
            //查询所有歌曲信息
            map.put("musiclist",musicServices.fandAll());

        }else{
            System.out.println("修改失败");
            map.put("message","修改歌曲失败");
        }
        //防止重复提交表单
        return "redirect:musiclist";

    }

}
