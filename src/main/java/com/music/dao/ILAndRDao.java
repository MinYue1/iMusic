package com.music.dao;

import com.music.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

@Mapper
public interface ILAndRDao {

    /**
     * 登入操作
     * @param user
     * @param pass
     * @return
     */
    User login(@Param("user")String user, @Param("pass")String pass);

    /**
     * 注册账号 查重
     * @param userCode
     * @return User
     */
    User registerName(@Param("userCode")String userCode);

    int register(@Param("userCode")String userCode,@Param("userPass")String userPass,@Param("regDate") Date regDate);
}
