# iMusic 在线音乐网 

@(原创)[文凯 欧|Spring Boot|MySQL]

**iMusic**是一款专为音乐爱好者打造的在线听歌网站，通过精心设计的UI与技术实现，配合服务器强大的存储和同步功能，带来前所未有的听歌体验。特点概述：
- **支持后台管理** ：登录页输入管理员账号密码通过时、*页面* 跳转到后台管理页面，其余账号跳转到播放器页面；
- **音乐云盘** ：普通用户可以上传自定义的Mp3音频，管理员审核过后即可设置是否公开，公开的mp3可以被其他用户看到；
- **歌单创建** ：喜欢的歌可以创建一个歌单收藏到一起，服务器并不会重复存储；
- **单曲聊天室**：公开类的歌曲开放单独聊天室，当播放开始时即进入聊天室；

-------------------

### 登录模块实现
``` java
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
```
## 反馈与建议
- 抖音：[@自由AI爱好者](http://weibo.com/u/2788354117](https://www.douyin.com/user/MS4wLjABAAAAd_CZq4G0iZh3TSAg3s8mr4f8IdmB6v-WdcjsCfyDjVM)
- 邮箱：<3343790891@qq.com>
