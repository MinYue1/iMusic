package com.music.controller;

import com.music.pojo.User;
import com.music.services.LAndRServices;
import com.music.services.StatisticsServices;
import com.music.validatecode.IVerifyCodeGen;
import com.music.validatecode.SimpleCharVerifyCodeGenImpl;
import com.music.validatecode.VerifyCode;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("userSession")
public class LoginAndRegister {
    @Autowired
    private LAndRServices lAndRServices;
    @Autowired
    private StatisticsServices statisticsServices;


    //登入
    int loginrequestNumber = 0;//登入失败次数
    @PostMapping("/login")
    public String login(String user, String password, String code, Map map){
//        System.out.println("name:"+user+"pass:"+password);
        if(!"".equals(code)){
            if(!this.Code.equalsIgnoreCase(code)){ //不区分大小写比较字符串
                map.put("user",user);
                map.put("password",password);
                map.put("message","验证码错误");
                return "login.html";
            }
        }
        User u = lAndRServices.login(user,password);
        if(u != null){
            System.out.println("登入成功");
            map.put("message",true);
            map.put("userSession",u);
            loginrequestNumber = 0;
            if(u.getUserRoleName().equals("管理员")){
                //重定向，防止重复提交表单
                return "redirect:admin";
            }else{
                return "redirect:index";
            }
        }else{
            if((++loginrequestNumber) >= 3){//登录失败3次，填写验证码
                map.put("codetrue",true);
            }
            map.put("user",user);
            map.put("message","账号或密码错误");
            System.out.println(loginrequestNumber);
            return "login.html";
        }
    }

    //注册
    @PostMapping("/register")
    public ModelAndView register(String user, String password, ModelAndView view){

        if(lAndRServices.register(user,password) > 0){
            System.out.println("注册成功");
            view.addObject("m",true);
        }else{
            System.out.println("注册失败");
            view.addObject("m",false);
        }
        view.setViewName("login.html");
        return view;
    }

    //注册用户名 查重
    @PostMapping("/registerName")
    @ResponseBody
    public boolean registerName(String username){
        if(lAndRServices.registerName(username)){
            return true;
        }else{
            return false;
        }
    }



    String Code; //验证码
    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            this.Code = code;
            System.out.println("验证码："+Code);
//            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        }
        catch (IOException e) {
//            LOGGER.info("", e);
        }
    }
}
