package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.District;
import com.team.house.service.DistrictService;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    public Map<String,Object> getDistrict(PageUtil pageUtil){
        PageInfo<District> pageInfo = districtService.getDistrict(pageUtil);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("addDistrict")
    public Map<String,Object> addDistrict(District district){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = districtService.addDistrict(district);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("listDistrict")
    public District listDistrict(Integer id){
        District district = districtService.listDistrict(id);
        return district;
    }
    @RequestMapping("updateDistrict")
    public Map<String,Object> updateDistrict(District district){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = districtService.updateDistrict(district);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("deleteDistrict")
    public Map<String,Object> deleteDistrict(Integer id){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = districtService.deleteDistrict(id);
            map.put("result",i);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //接受页面数据，ids=1,2,3的方式
    @RequestMapping("deleteMoreDistrict")
    public String deleteMoreDistrict(String ids){
        try {
            //将字符串转换为string数组
            String[] split = ids.split(",");
            //创建数组
            Integer [] id=new Integer[split.length];
            //将string数组转换为Integer数组
            for (int i = 0; i < split.length; i++) {
                id[i]=new Integer(split[i]);
            }
            int i = districtService.deleteMoreDistrict(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }
}
