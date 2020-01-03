package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Street;
import com.team.house.service.StreetService;
import com.team.house.utils.StreetCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreet")
    //需要带条件查询，使用了实体，里面装了分页和查询条件
    public Map<String,Object> getStreet(StreetCondition condition){
        PageInfo<Street> pageInfo = streetService.getStreet(condition);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addStreet")
    public Map<String,Object> addStreet(Street street){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = streetService.addStreet(street);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("listStreet")
    public Street listStreet(Integer id){
        Street street = streetService.listStreet(id);
        return street;
    }
    @RequestMapping("updateStreet")
    public Map<String,Object> updateStreet(Street street){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = streetService.updateStreet(street);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("deleteStreet")
    public Map<String,Object> deleteStreet(Integer id){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = streetService.deleteStreet(id);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //接受页面数据，ids=1,2,3的方式
    @RequestMapping("deleteMoreStreet")
    public String deleteMoreStreet(String ids){
        try {
            //将字符串转换为string数组
            String[] split = ids.split(",");
            //创建数组
            Integer [] id=new Integer[split.length];
            //将string数组转换为Integer数组
            for (int i = 0; i < split.length; i++) {
                id[i]=new Integer(split[i]);
            }
            int i = streetService.deleteMoreStreet(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

}
