package com.music.services;

import com.music.dao.IUserDao;
import com.music.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserSevices {
    @Autowired
    private IUserDao dao;

    /**
     * 查询所有用户
     * @return List<User>
     */
    public List<User> findAll(int page,int line){
        return dao.findAll(page,line);
    }

    /**
     * 查询所有用户总条数
     * @return int
     */
    public int findAllline(){
        return dao.findAllline();
    }

    /**
     * 查询所有用户角色分类
     * @return List<String>
     */
    public List<String> findAllRoleName(){
        return dao.findAllRoleName();
    }

    /**
     * 查询一个用户
     * @param id
     * @return User
     */
    public User findOne(String id){
        return dao.findOne(id);
    }

    /**
     * 修改一个用户信息
     * @param user
     * @return
     */
    public int updateOneUser(User user){
        //根据角色类型设置id
        user.setUserRole(dao.findUserRole(user.getUserRoleName()));
        return dao.updateOneUser(user);
    }

    /**
     * 删除一个用户
     * @param id
     * @return int - 删除成功的行数
     */
    public int userOneDel(String id){
        return dao.userOneDel(id);
    }

    /**
     * 添加一个用户
     * @param user
     * @return int - 添加成功的行数
     */
    public int insertOneUser(User user){
        //根据角色类型设置id
        user.setUserRole(dao.findUserRole(user.getUserRoleName()));
        //获取当前注册时间( Date<--String<--long'毫秒为单位')
        user.setRegDate(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())));

        return dao.insertOneUser(user);
    }

    /**
     * 模糊查询
     * @param queryUserRoleName
     * @param queryText
     * @return List<User>
     */
    public List<User> queryUsers1(String queryUserRoleName,String queryText,int page,int line ){
        if("".equals(queryUserRoleName) && "".equals(queryText)){
            return dao.findAll(page,line);
        }
        if(!"".equals(queryUserRoleName)){
            return dao.queryUsers2(queryUserRoleName,queryText,page,line);
        }

        return dao.queryUsers1(queryUserRoleName,queryText,page,line);
    }

}
