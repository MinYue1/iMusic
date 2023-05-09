package com.music.controller;

import com.music.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes("userSession")
public class JumpController {

    @GetMapping("/cs") //跳转测试
    public String cs(){
        return "demo.html";
    }

    @GetMapping("/login") //登录跳转
    public String login(){
        return "login.html";
    }

    @GetMapping("/loginout") //退出
    public String loginout(Map map, HttpServletRequest request){
        //注销登录
        map.put("userSession",null);
        return "redirect:/login";
    }


    @GetMapping("/index") //前端普通用户页跳转
    public String index(){
        return "index.html";
    }

    @GetMapping("/ip") //前端普通用户页跳转
    public String ip(){
        return "ip.html";
    }





    //---------- 前端普通用户页面 跳转 ---------

    //个性推荐
    @GetMapping("/gxtj")
    public String gxtj(){
        return "shouye/gxtj.html";
    }

    //专属定制
    @GetMapping("/zsdz")
    public String zsdz(){
        return "shouye/zsdz.html";
    }


    //音乐上传
    @GetMapping("/upRequest")
    @ResponseBody
    public Boolean upRequest(Map map, HttpServletRequest request){
        //获取session
        HttpSession session = request.getSession();
        User username = (User)session.getAttribute("userSession");
        if(username == null){
            System.out.println("没登录");
            return false;
        }else{
            System.out.println("当前登录用户"+username);
            return true;
        }

    }
    //-------------------------------------






    //---------- 前端管理页面 跳转 ---------

    @GetMapping("/help")//帮助页面
    public String help(){
        return "/admin/help.html";
    }

    //-------------------------------------

}
