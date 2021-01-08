package com.coolway.controller.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    /**
     * 配置后台管理的servlet	statViewServlet
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        //配置servlet拦截路径
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();
        //登录名
        initParams.put("loginUsername", "admin");
        //登陆密码
        initParams.put("loginPassword", "1234");
        //白名单，为空表示允许所有访问
        initParams.put("allow", "");
        //黑名单，和白名单冲突时黑名单优先，被黑名单拦截会提示Sorry, you are not permitted to view this page
        //initParams.put("deny", "192.168.37.1");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置用于监控的filter	webStatFilter
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //配置过滤规则，/*表示所有，监控所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));

        Map<String, String> initParams = new HashMap<>();
        //配置排除的请求，即访问静态资源和druid功能无需监控
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        bean.setInitParameters(initParams);
        return bean;
    }
}
