package com.team.house.frontcontroller;

import com.team.house.pojo.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 这是前台用户的控制器
 */
//使用注入依赖时候如果不同包有相同类名，可以通过设置<bean class='TypeController' id='type'>的id来区分不同的类
@Controller("type")
//巧用命名，使用的文件名正好与值相同，前台跳转少写
@RequestMapping("/page/")
public class TypeController {

    //注入typeService
    @Autowired
    private TypeService typeService;
    /**
     * 实现添加户型的时候显示类型
     */
    @RequestMapping("getTypeData")
    @ResponseBody
    public List<Type> getTypeData(){
       return typeService.getALLType();
    }
}