package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Users;
import com.team.house.utils.PageUtil;
import com.team.house.utils.UserCondition;

import java.util.Map;

public interface UsersService {

    /**
     *
     * @param condition 分页工具
     * @return 返回的是pageInfo
     */
    PageInfo<Users> getUsers(UserCondition condition);

    /**
     *
     * @param users 区域pojo
     * @return 影响行数
     */
    int addUsers(Users users);

    /**
     *
     * @param id 区域ID
     * @return 查询区域的单挑数据
     */
     Users listUsers(Integer id);

    /**
     *
     * @param users 页面获取的更新数据
     * @return 影响行数
     */
     int updateUsers(Users users);

    /**
     *
     * @param id 区域编号
     * @return 影响行数
     * 使用事物支持，删除区域同时删除街道
     */
     int deleteUsers(Integer id);

    /**
     *
     * @param ids 区域id数组
     * @return 影响行数
     */
    int deleteMoreUsers(Integer[] ids);

    /**
     * 验证用户名是否存在
     * @param Uname 用户输入的数据
     * @return 返回true用户可以使用 ，false该 用户已存在
     */
    boolean isExistence(String Uname);

    /**
     * 添加用户
     * @param users 用户注册时候添加的实体
     * @return 返回影响行数
     */
    boolean addsUsers(Users users);

    /**
     * 登陆功能
     * @param username 用户名
     * @param password 密码
     * @return 返回实体，考虑这个实体要存放在session中，供后面使用
     * 考虑用户接受到的提示信息的情况，一种是提示他账号和密码错误，第二种准确告诉他密码错还是账号错
     * 使用第二种,我可以选择返回一个Map，里面存放密码错，账号错和实体
     */
    Map<String,Object> loginUsers(String username, String password);


}
