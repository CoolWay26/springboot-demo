package com.coolway.config;

import org.springframework.context.annotation.Configuration;

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
