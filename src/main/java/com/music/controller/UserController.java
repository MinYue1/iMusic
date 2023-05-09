package com.music.controller;

import com.music.pojo.Page;
import com.music.pojo.User;
import com.music.services.UserSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value = {"userSession","message"})
public class UserController {

    @Autowired
    private UserSevices userSevices;

    @GetMapping("userlist")//用户管理
    public String userlist(Map map){
        System.out.println("查找所有用户中");

        //用户角色下拉列表
        map.put("userRoleNames",userSevices.findAllRoleName());
        //用户列表
        map.put("userlist",userSevices.findAll(0,5));

        //当前页码
        map.put("nowpage",1);
        //分页条数
        map.put("line",5);
        //总页数
        int sumpage = userSevices.findAllline();
        if(sumpage%5 != 0){
            sumpage = sumpage/5 +1;
        }else{
            sumpage = sumpage / 5;
        }
        map.put("sumpage",sumpage);


        return "/admin/userlist.html";
    }

    @GetMapping("/useradd")//添加用户
    public String useradd(Map map){

        //查找已存在的用户角色列表
        //
        return null;
    }

    int TemporaryUserId = 0;//临时用户id(用于修改用户信息时验证),防止前台篡改
    @GetMapping("/userlookOne")
    public String lookOne(String id,int method,Map map){

        String path = "";
        //查询单个用户数据
        User u = userSevices.findOne(id);
        TemporaryUserId = u.getId();
        map.put("user",u);

        if(method == 1){
            path = "admin/userview.html";
        }else if(method == 2){
            //用户角色下拉列表
            map.put("userRoleNames",userSevices.findAllRoleName());
            path = "admin/usermodify.html";
        }

        return path;
    }

    @PostMapping("/updateUser")
    public String updateUser(User user, Map map){
        System.out.println(user+":");

        //比较上次查找单个user的id,防止前台篡改
        if(user.getId() != TemporaryUserId)user.setId(TemporaryUserId);
        if(userSevices.updateOneUser(user) > 0){
            System.out.println("修改成功");
            //前端消息
            map.put("message","修改用户成功");
            //用户角色下拉列表
            map.put("userRoleNames",userSevices.findAllRoleName());
            //用户列表
            map.put("userlist",userSevices.findAll(0,5));

        }else{
            System.out.println("修改失败");
            map.put("message","修改用户成功");
        }
        //防止重复提交表单
        return "redirect:userlist";
    }

    @GetMapping("/userOneDel")
    public String userOneDel(String id,Map map) {

        if (!"1".equals(id) && userSevices.userOneDel(id) > 0) {
            System.out.println("删除成功");
            //前端消息
            map.put("message", "删除成功");

        } else {
            System.out.println("删除失败");
            //前端消息
            map.put("message", "删除失败");
        }
        //用户角色下拉列表
        map.put("userRoleNames", userSevices.findAllRoleName());
        //用户列表
        map.put("userlist", userSevices.findAll(0,5));

        return "admin/userlist.html";
    }

    /**
     * 添加用户跳转方法
     * @return
     */
    @GetMapping("/addUser")
    public String addUser(Map map){
        //用户角色下拉列表
        map.put("userRoleNames", userSevices.findAllRoleName());
        return "admin/useradd.html";
    }

    @PostMapping("/insertOneUser")
    public String insertOneUser(User user,Map map){
//      System.out.println(user);

        if(userSevices.insertOneUser(user) > 0){
            System.out.println("添加成功");
            //前端消息
            map.put("message", "添加成功");
            //用户角色下拉列表
            map.put("userRoleNames", userSevices.findAllRoleName());
            //用户列表
            map.put("userlist", userSevices.findAll(0,5));
        }else{
            System.out.println("添加失败");
        }
        //防止重复提交表单
        return "redirect:userlist";
    }

    @PostMapping("/queryUsers")
    public String queryUsers(String queryUserRoleName,String queryText,int page,int line,Map map){

        System.out.println("i come in!!");

        //用户角色下拉列表
        map.put("userRoleNames", userSevices.findAllRoleName());

        //模糊查询
        List<User> list = userSevices.queryUsers1(queryUserRoleName,queryText,(page-1)*line,line);
        map.put("userlist",list);

        //当前页码
        map.put("nowpage",page);
        //分页条数
        map.put("line",line);
        //总页数
        int sumpage = list.size();
        if(sumpage%line != 0){
            sumpage = sumpage/line +1;
        }else{
            sumpage = sumpage / line;
        }
        map.put("sumpage",sumpage);



       return "admin/userlist.html";
    }


    @GetMapping("pagelist")//分页
    public String pagelist(int page,int line,Map map){

        //用户角色下拉列表
        map.put("userRoleNames",userSevices.findAllRoleName());
        //用户列表
        List<User> list = userSevices.findAll((page-1)*line,line);
        map.put("userlist",list);


        //当前页码
        map.put("nowpage",page);
        //分页条数
        map.put("line",line);
        //总页数
        int sumpage = userSevices.findAllline();
        if(sumpage%line != 0){
            sumpage = sumpage/line +1;
        }else{
            sumpage = sumpage / line;
        }
        map.put("sumpage",sumpage);
        return "/admin/userlist.html";
    }



}
