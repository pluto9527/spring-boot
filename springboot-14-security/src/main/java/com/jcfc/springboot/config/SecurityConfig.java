package com.jcfc.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
@EnableWebSecurity //包含了@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //定制 授权 规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        /**
         * 开启自动配置的登录功能，如果没有登录，没有权限，就会跳转到SpringSecurity的登录页面（页面SpringSecurity提供）
         *      loginPage定制登陆页面，usernameParameter指定提交的用户名，passwordParameter指定提交的密码
         *   1. 没有登录 /login 来到登录页（springsecurity处理这些请求）
         *   2. 重定向到 /login?error 表示登录失败
         *   3. 更多详细规定
         *   4. 默认post形式的 /login 代表处理登陆,get形式的 /login 是登录页面
         *   5. 一旦定制了登录页面loginPage, 那么 loginPage路径的post请求就是处理登陆
         *
         */
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");

        /**
         * 开启自动配置的注销功能
         *    1. 访问 /logout 表示用户注销（post请求）,清空session
         *    2. 注销成功后会返回 /login?logout 页面（页面SpringSecurity提供）
         */
        http.logout().logoutSuccessUrl("/");//注销成功后跳转到首页

        /**
         * 开启记住我功能
         *      默认参数名是rememberMe，rememberMeParameter指定记住用户提交的参数
         * 登陆成功以后，将cookie发给浏览器保存
         * 点击注销会删除cookie
         */
        http.rememberMe().rememberMeParameter("remember");
    }

    //定制 认证 规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication()数据库
        //默认用户及其角色存在内存
        auth.inMemoryAuthentication()
                //登陆时用BCrypt加密方式对用户密码进行处理
                .passwordEncoder(new BCryptPasswordEncoder())
                //对保存在内存中的密码加密，这样登录时才能比对一致
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123")).roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1", "VIP3");
    }

}
