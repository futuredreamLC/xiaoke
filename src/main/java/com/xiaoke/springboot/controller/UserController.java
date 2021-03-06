package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-02 11:15:32
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("Login", null);
        return "redirect:/index.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        //获取Subject
        Subject subject = SecurityUtils.getSubject();

        //封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //执行登录方法
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("Login", user);
            if (user.getPosition().equals("root")) {
                return "redirect:/goods/list";
            } else {
                return "redirect:/index.html";
            }

        } catch (UnknownAccountException e) {
            map.put("msg", "用户名不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            map.put("msg", "用户密码错误！");
            return "login";
        }

//        User user=userService.queryByName(username);
//        if(user!=null && user.getPassword().equals(password)){
//            session.setAttribute("Login",user);
//            return "redirect:/index.html";
//        }
//        else {
//            map.put("msg","用户名密码错误！");
//            return "login";
//        }
    }

    @PostMapping("/register")
    public String register(User user,
                           Map<String, Object> map) {
        User user1 = userService.queryByName(user.getUserName());
        if (user1 == null) {
            userService.insert(user);
            map.put("msg", "注册成功，请登录!");
            return "login";
        } else {
            map.put("msg", "该用户名已被注册！");
            return "register";
        }
    }

    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

}