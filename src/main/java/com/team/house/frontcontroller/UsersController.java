package com.team.house.frontcontroller;

import com.team.house.pojo.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 这是前台用户的控制器
 */
//使用注入依赖时候如果不同包有相同类名，可以通过设置<bean class='UsersController' id='users'>的id来区分不同的类
@Controller("users")
//巧用命名，使用的文件名正好与值相同，前台跳转少写
@RequestMapping("/page/")
public class UsersController {

    //注入usersService
    @Autowired
    private UsersService usersService;
    /**
     * 实现用户输入昵称时候验证数据库是否存在
     */
    @RequestMapping("isExistence")
    @ResponseBody
    public String isExistence(String uName){
        //返回一个json数据
        boolean b = usersService.isExistence(uName);
        return "{\"result\":"+b+"}";
    }
    //实现用户注册
    @RequestMapping("register")
    public String register(Users users){
        try {
            boolean b = usersService.addsUsers(users);
            //注册成功后跳转到登陆页面，注册失败返回当前页
            if(b){
                return "redirect:login.jsp";
            }else{
                return "redirect:regs.jsp";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:500.jsp";
        }
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(String username, String password, HttpSession session){
        Map<String, Object> map = usersService.loginUsers(username, password);
        session.setAttribute("map",map);
        if(map.containsKey("info")){
            return map;
        }else{
            return map;
        }
    }
}