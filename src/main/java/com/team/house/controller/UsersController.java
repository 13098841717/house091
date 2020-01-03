package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Users;
import com.team.house.service.UsersService;
import com.team.house.utils.PageUtil;
import com.team.house.utils.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("getUsers")
    //需要带条件查询，使用了实体，里面装了分页和查询条件
    public Map<String,Object> getUsers(UserCondition condition){
        PageInfo<Users> pageInfo = usersService.getUsers(condition);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addUsers")
    public Map<String,Object> addUsers(Users users){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = usersService.addUsers(users);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("listUsers")
    public Users listUsers(Integer id){
        Users users = usersService.listUsers(id);
        return users;
    }
    @RequestMapping("updateUsers")
    public Map<String,Object> updateUsers(Users users){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = usersService.updateUsers(users);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("deleteUsers")
    public Map<String,Object> deleteUsers(Integer id){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = usersService.deleteUsers(id);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //接受页面数据，ids=1,2,3的方式
    @RequestMapping("deleteMoreUsers")
    public String deleteMoreUsers(String ids){
        try {
            //将字符串转换为string数组
            String[] split = ids.split(",");
            //创建数组
            Integer [] id=new Integer[split.length];
            //将string数组转换为Integer数组
            for (int i = 0; i < split.length; i++) {
                id[i]=new Integer(split[i]);
            }
            int i = usersService.deleteMoreUsers(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }


}
