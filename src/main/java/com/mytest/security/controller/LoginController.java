package com.mytest.security.controller;


import com.mytest.security.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 5:25
 */
@RestController
public class LoginController {

    //@Autowired
    //private AuthenService authenticationService;
    /**
     * 用户登录
     * @param authenticationRequest 登录请求
     * @return
     */
//    @PostMapping(value = "/login",produces = {"text/plain;charset=UTF-8"})
//    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
//
//        UserDto userDetails = authenticationService.authentication(authenticationRequest);
//        //登录后将用户信息放入session中
//        session.setAttribute(UserDto.SESSION_USER_KEY, userDetails);
//        return userDetails.getFullname() + " 登录成功";
//    }


    /**
     * 登出方法  将session销毁
     *
     */
/*    @GetMapping(value = "/logout", produces = {"text/plain;charset=UTF-8"})
    public String loginOut(HttpSession session){
        //销毁session
        session.invalidate();
        return "退出系统";
    }*/


    /**
     * 修改LoginController，增加测试资源1，它从当前会话session中获取当前登录用户，并返回提示信息给前台
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        String name = getUsername();

        return name + "访问资源r1";
    }

    /**
     * 测试资源2
     * @param session
     * @return
     */
    @PreAuthorize("isAnonymous()")  //允许匿名访问
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session){
        String fullname = getUsername();

        return fullname + " 访问资源2";
    }

    /**
     * 测试资源3
     * @param session
     * @return
     */
    @PreAuthorize("hasRole('ROLE_管理员')")  //允许管理员访问
    @GetMapping(value = "/r/r3",produces = {"text/plain;charset=UTF-8"})
    public String r3(HttpSession session){
        String fullname = getUsername();

        return fullname + " 访问资源3";
    }

    /**
     * 测试资源4
     * @param session
     * @return
     */
    //@PreAuthorize("isAnonymous()")  //允许匿名访问
    @GetMapping(value = "/r/r4",produces = {"text/plain;charset=UTF-8"})
    public String r4(HttpSession session){

        String fullname = getUsername();

        return fullname + " 访问资源4";
    }

    /**
     * 测试资源5
     * @param session
     * @return
     */
    @PreAuthorize("hasAnyAuthority('p1')")  //用于p1权限可以访问
    @GetMapping(value = "/r/r5",produces = {"text/plain;charset=UTF-8"})
    public String r5(HttpSession session){

        String fullname = getUsername();

        return fullname + " 访问资源5";
    }

    /**
     * 登录成功操作
     * @return
     */
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        String username = getUsername();
        return username + " 登录成功";
    }


    /**
     * 获取当前登录用户名
     * @return
     */
    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = "匿名";
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
            username =
                    ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}