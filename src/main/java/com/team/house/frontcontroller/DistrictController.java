package com.team.house.frontcontroller;

import com.team.house.pojo.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 这是前台用户的控制器
 */
//使用注入依赖时候如果不同包有相同类名，可以通过设置<bean class='DistrictController' id='district'>的id来区分不同的类
@Controller("district")
//巧用命名，使用的文件名正好与值相同，前台跳转少写
@RequestMapping("/page/")
public class DistrictController {

    //注入districtService
    @Autowired
    private DistrictService districtService;
    /**
     * 实现添加户型的时候显示类型
     */
    @RequestMapping("getDistrictData")
    @ResponseBody
    public List<District> getDistrictData(){
       return districtService.getAllDistrict();
    }
}