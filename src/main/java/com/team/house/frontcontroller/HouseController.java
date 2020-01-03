package com.team.house.frontcontroller;


import com.github.pagehelper.PageInfo;
import com.team.house.pojo.House;
import com.team.house.pojo.Users;
import com.team.house.service.HouseService;
import com.team.house.utils.FileUploadUtil;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("house")
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //实现文件上传需要使用apache的upload坐标，表单提交方式必须时post方式，
    // 表单添加属性enctype="multipart/form-data属性,
    //springMVC设置文件上传的设置，设置文件最大上传大小，utf-8格式

    @RequestMapping("addHouse")
    public String addHouse(Model model, House house, HttpSession session, @RequestParam(value = "filename",required = true) CommonsMultipartFile cp){
        //处理传输的文件
        //获取文件名称
        try {
            String newName = FileUploadUtil.upload(cp);
            Map<String,Object> map = (Map<String,Object>)session.getAttribute("map");
            //设置用户id
            Users users = (Users)map.get("user");
            house.setUserId(users.getId());
            //设置图片的存储路径
            house.setPath(newName);
            //设置房屋id
            house.setId(System.currentTimeMillis()+"");
            int i = houseService.addHouse(house);
            if(i>0){
                model.addAttribute("insert","success");
            }else{
                model.addAttribute("insert","fail");
            }
            return "forward:houseData";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "500";
    }

    @RequestMapping("houseData")
    public String houseData(HttpSession session, PageUtil pageUtil,Model model){
        Map<String,Object> map = (Map<String, Object>) session.getAttribute("map");
        Users user = (Users)map.get("user");
        pageUtil.setRows(3);
        PageInfo<House> pageInfo = houseService.getHouseByUser(user.getId(), pageUtil);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }


    @RequestMapping("showHouse")
    public String showHouse(String id,Model model){
        House house = houseService.showHouse(id);
        model.addAttribute("house",house);
        return "update";
    }

    @RequestMapping("updateHouse")
    public String updateHouse(House house,@RequestParam(value = "filename",required = true)CommonsMultipartFile cp){
        if(!cp.getOriginalFilename().equals("")){
            String upload = FileUploadUtil.upload(cp);
            FileUploadUtil.deletePath(house.getPath());
            house.setPath(upload);
        }
        houseService.updateHouse(house);
        return "forward:houseData";
    }

    @RequestMapping("deleteHouse")
    public String deleteHouse(String id,Integer status){
        int i = houseService.deleteHouse(id, status);
        return "forward:houseData";
    }

    @RequestMapping("mainHouse")
    public String mainHouse(HouseCondition houseCondition,Model model){
        houseCondition.setRows(5);
        PageInfo<House> pageInfo = houseService.mainHouse(houseCondition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",houseCondition);
        return "list";
    }
}
