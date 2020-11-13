package org.os.GraduationProject.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.os.GraduationProject.pojo.User;
import org.os.GraduationProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class UserController {
    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public int insertUser(User user){
        //密码加密
        String hashAlgorithmName = "MD5";
        String password = user.getPassword();
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUser());
        Object obj = new SimpleHash(hashAlgorithmName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
        user.setPassword(obj.toString());
        return service.insertUser(user);
    }


    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String user, String password, Model model){
        System.out.println("name="+user);
        /*
          使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user,password);

        //3.执行登录方法
        try {
            subject.login(token);

            //登录成功
            //跳转到test.html
            return "test";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
