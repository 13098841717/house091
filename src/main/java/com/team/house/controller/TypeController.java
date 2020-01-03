package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.Type;
import com.team.house.service.TypeService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("getType")
    public Map<String,Object> getType(PageUtil pageUtil){
        PageInfo<Type> pageInfo = typeService.getType(pageUtil);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addType")
    public Map<String,Object> addType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.addType(type);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("listType")
    public Type listType(Integer id){
        Type type = typeService.listType(id);
        return type;
    }
    @RequestMapping("updateType")
    public Map<String,Object> updateType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.updateType(type);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("deleteType")
    public Map<String,Object> deleteType(Integer id){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.deleteType(id);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //接受页面数据，ids=1,2,3的方式
    @RequestMapping("deleteMoreType")
    public String deleteMoreType(String ids){
        try {
            //将字符串转换为string数组
            String[] split = ids.split(",");
            //创建数组
            Integer [] id=new Integer[split.length];
            //将string数组转换为Integer数组
            for (int i = 0; i < split.length; i++) {
                id[i]=new Integer(split[i]);
            }
            int i = typeService.deleteMoreType(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

}
