package com.mytest.security.service;

import com.mytest.security.dao.UserDao;
import com.mytest.security.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @version 1.00
 * @time 2020/2/20 0020  上午 11:50
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
        System.out.println("username="+username);
        //根据账号去数据库查询...
        UserDto user = userDao.getUserByUsername(username);
        if(user == null){
            return null;
        }

        //查询用户权限
        List<String> permissions = userDao.findPermissionsByUserId(user.getId());
        System.out.println("权限为："+permissions);
        String[] perarray = new String[permissions.size()];
        permissions.toArray(perarray);
        //创建UserDetails
        UserDetails userDetails =
                User.withUsername(user.getFullname()).password(user.getPassword()).authorities(perarray).build();
        return userDetails;
    }
}