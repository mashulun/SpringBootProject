package com.study.myshop.security;



import com.study.myshop.security.handler.LogOutSuccessHandler;
import com.study.myshop.security.handler.LoginErrorHandler;
import com.study.myshop.security.handler.LoginSuccessHandler;
import com.study.myshop.security.handler.PermissionErrorHandler;

import com.study.myshop.service.impl.AdminDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


import javax.annotation.Resource;

/**
 * @author msl
 */
@Configuration
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /***
     * Security 用户 业务层 对象
     */
    @Resource
    private AdminDetailServiceImpl adminService;

    /***
     *  授权对象
     */
    @Resource
    private PermissionInterceptor permissionInterceptor;

    /***
     * 认证对象
     */
    @Resource
    private PermissionValid validPermission;

    /***
     * 权限认证失败处理器
     */
    @Resource
    private PermissionErrorHandler permissionErrorHandle;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //就是根据输入的用户的密码判断是否和数据库查出出来的对象的密码一致
        // 配置 用户登录的验证方式
        // 根据用户填写的账户名称获取用户对象
        auth.userDetailsService( adminService )
                // 将获取的用户对象的密码和用户填写的密码加密后进行等性判断
                .passwordEncoder( new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置 Security 过滤请求的 例外
        web.ignoring().antMatchers("/admin/login","/error/**","/img/**","/css/**","/js/**","/favicon.ico","/assets/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置 SpringSecurity 流程
        http.authorizeRequests()   // 配置 请求需要进行权限认证
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(permissionInterceptor);   // 配置授权对象
                        o.setAccessDecisionManager(validPermission);    // 配置认证对象
                        return o;
                    }
                })
                .and()
                // 开启 表单登录验证
                .formLogin()
                // 指定 登录表单页面的url路径
                .loginPage("/admin/login")
                // 指定 验证登录表单的url路径
                .loginProcessingUrl("/admin/doLogin")
                // 账户名称的形参名称
                .usernameParameter("username")
                // 账户密码的形参名称
                .passwordParameter("password")
                // 登录验证失败处理器
                .failureHandler( new LoginErrorHandler())
                // 登录验证成功处理器
                .successHandler( new LoginSuccessHandler())
                .permitAll()
                .and()
                // 开启 安全退出验证
                .logout()
                // 指定安全退出的URL路径
                .logoutUrl("/admin/logout")
                // 安全退出成功处理器
                .logoutSuccessHandler(new LogOutSuccessHandler())
                .permitAll()
                .and()
                .csrf()      // 跨域请求配置
                .disable()  // 禁用
                .exceptionHandling()
                // 权限认证失败处理器
                .accessDeniedHandler(permissionErrorHandle);
    }
}