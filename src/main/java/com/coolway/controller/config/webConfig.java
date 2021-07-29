package com.coolway.controller.config;

import org.apache.catalina.SessionListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class webConfig {


    /**
     <!-- filters -->
     <filter>
     <filter-name>session</filter-name>
     <filter-class>org.apache.activemq.web.SessionFilter</filter-class>
     <async-supported>true</async-supported>
     </filter>
     <filter-mapping>
     <filter-name>session</filter-name>
     <url-pattern>/*</url-pattern>
     </filter-mapping>
     */
//    @WebFilter(asyncSupported=true,urlPatterns="/*")
//    public class MyFilter extends SessionFilter {
//        //...
//    }
    /**
     <!-- listener -->
     <listener>
     <listener-class>org.apache.activemq.web.SessionListener</listener-class>
     </listener>
     */
//    @WebListener()
//    public class MyListener extends SessionListener {
//        //...
//    }
}
