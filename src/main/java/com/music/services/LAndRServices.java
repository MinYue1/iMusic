package com.music.services;

import com.music.dao.ILAndRDao;
import com.music.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class LAndRServices {
    @Autowired
    private ILAndRDao dao;

    /**
     * 登入操作
     * @param user
     * @param pass
     * @return User
     */
    public User login(String user, String pass){
        return dao.login(user,pass);
    }

    /**
     * 注册账号 查重
     * @param userCode 注册的账号
     * @return boolean - 是否能注册
     */
    public boolean registerName(String userCode){
        return dao.registerName(userCode) == null;
    }

    /**
     * 注册操作
     * @param userCode 注册账号
     * @param userPass 注册密码
     * @return int - 添加成功的行数
     */
    public int register(String userCode, String userPass){
        //获取当前时间( Date<--String<--long'毫秒为单位')
        java.sql.Date d1 = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        Date regDate = d1;

        //此处必须记录注册日期，以后完善用户信息没有修改的权限和选项
        System.out.println("注册日期："+d1);
        return dao.register(userCode,userPass,regDate);
    }
}
