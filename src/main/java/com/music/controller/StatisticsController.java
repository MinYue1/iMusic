package com.music.controller;

import com.music.services.StatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

//今日统计
@Controller
@SessionAttributes("userSession")
public class StatisticsController {

    @Autowired
    private StatisticsServices statisticsServices;

    @GetMapping("/admin")//今日统计
    public String admin(Map map){
//        System.out.println("i come in");
        //用户总数
        map.put("UserSums",statisticsServices.UserSums());
        //歌曲总数
        map.put("MusicSums",statisticsServices.MusicSums());
        //今日新增用户
        map.put("NewUsers",statisticsServices.NewUsers());
        //今日新增歌曲
        map.put("NewMusics",statisticsServices.NewMusics());
        return "/admin/admin.html";
    }

}
