package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.pojo.House;
import com.team.house.service.HouseService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("houseControllers")
@Service
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouse")
    @ResponseBody
    public Map<String,Object> getHouse(PageUtil pageUtil){
        Map<String,Object> map=new HashMap<>();
        PageInfo<House> pageInfo = houseService.showAllHouse(pageUtil);
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("isPassHouse")
    @ResponseBody
    public String isPassHouse(String id,Integer ispass){
        int passHouse = houseService.isPassHouse(id, ispass);
        return "\"result\":"+passHouse;
    }

    @RequestMapping("isPassMoreHouse")
    @ResponseBody
    public String isPassMoreHouse(Integer[] ids,Integer status){
        int passMoreHouse = houseService.isPassMoreHouse(ids, status);
        return  "\"result\":"+passMoreHouse;
    }
}
