package com.mytest.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 9:38
 */
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)  //进去方法前拦截
@EnableWebSecurity  //因为springboot的自动配置原理，此注解也可以改为@configuratiob
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //注入UserDetailService
/*    @Bean
    public UserDetailsService getUserDetailService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()  //关闭跨站请求
                .authorizeRequests()
                /*.antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")*/
                .antMatchers("/r/**").authenticated() //（1）
                .anyRequest().permitAll() //（2）
                .and()
                .formLogin()//允许表单登录
                .loginPage("/login-view")   //登录页面  会已重定向方式跳转
                .loginProcessingUrl("/login")   //登录请求提交url
                .successForwardUrl("/login-success");//登录成功跳转页面
    }

}
