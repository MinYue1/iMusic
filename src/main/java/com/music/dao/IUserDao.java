package com.music.dao;

import com.music.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理
 */
@Mapper
public interface IUserDao {

    /**
     * 查询所有用户 - 分页
     * @return List<User>
     */
    List<User> findAll(@Param("page")int page,@Param("line")int line);

    /**
     * 查询所有用户 总条数
     * @return int
     */
    int findAllline();
    /**
     * 查询所有角色分类
     * @return List<String>
     */
    List<String> findAllRoleName();

    /**
     * 查询一个用户
     * @return User
     */
    User findOne(@Param("id")String id);

    /**
     * 修改一个用户信息
     * @param uesr
     * @return int(修改成功的条数)
     */
    int updateOneUser(@Param("user")User uesr);

    /**
     * 根据用户分类名查找对于的id
     * @param str
     * @return int
     */
    int findUserRole(@Param("str")String str);

    /**
     * 删除一个用户
     * @param id
     * @return int - 成功删除的行数
     */
    int userOneDel(@Param("id")String id);

    /**
     * 添加一个用户
     * @param user
     * @return int - 添加成功的行数
     */
    int insertOneUser(@Param("user")User user);


    /**
     * 模糊查询
     * @param queryUserRoleName
     * @param queryText
     * @return List<User> - 用户集合
     */
    List<User> queryUsers1(@Param("queryUserRoleName")String queryUserRoleName,@Param("queryText")String queryText,@Param("page")int page,@Param("line")int line);

    /**
     * 模糊查询 在用户分类范围内
     * @param queryUserRoleName
     * @param queryText
     * @return List<User> - 用户集合
     */
    List<User> queryUsers2(@Param("queryUserRoleName")String queryUserRoleName,@Param("queryText")String queryText,@Param("page")int page,@Param("line")int line);

}
