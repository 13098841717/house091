package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.mapper.StreetMapper;
import com.team.house.mapper.UsersMapper;
import com.team.house.pojo.Users;
import com.team.house.pojo.UsersExample;
import com.team.house.service.UsersService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Users> getUsers(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if(condition.getUsername()!=null){
            criteria.andNameLike("%"+condition.getUsername()+"%");
        }
        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        return new PageInfo<>(list);
    }

    @Override
    public int addUsers(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users listUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
    //挂起事物
    @Override
    public int updateUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    //使用事物支持

    @Override
    //不能使用try catch，因为会让事物扫描不到异常
    //返回值无关了，同步后出现异常就会回滚，成功就执行
//    @Transactional
    public int deleteUsers(Integer id) {
        //删除区域
        int i = usersMapper.deleteByPrimaryKey(id);
        //删除街道
        return i;
    }

    @Override
    public int deleteMoreUsers(Integer[] ids) {
        return usersMapper.deleteMoreUsers(ids);
    }

    @Override
    public boolean isExistence(String Uname) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andNameEqualTo(Uname);
        List<Users> users = usersMapper.selectByExample(usersExample);
        //判断查询后的集合是否为空，空表示可以注册
        if(users.size()!=0&&users!=null){
            return false;
        }else{
            return true;
        }
    }



    @Override
    public boolean addsUsers(Users users) {
        //考虑加入时候必须判断是否是管理员
        users.setIsadmin(0);
        //对密码进行加密
        String s = MD5Utils.md5Encrypt(users.getPassword());
        //设置新密码
        users.setPassword(s);
        int i = usersMapper.insertSelective(users);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Map<String, Object> loginUsers(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(usersExample);
        Map<String,Object> map=new HashMap<>();
        //查询后list第一个就是我们想要的数据
        //拿到数据后对密码进行比较
        //同时做出账号密码谁出错进行判断
        if(users.size()!=0){
            Users user = users.get(0);
            if(user.getPassword().equals(MD5Utils.md5Encrypt(password))){
                    map.put("user",user);
            }else{
                map.put("info","密码错误");
            }
        }else{
            map.put("info","用户名错误");
        }
        return map;
    }

    public static void main(String[] args) {
        ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
        UsersService usersService=(UsersService) ctx.getBean("usersServiceImpl");
    }
}
