package com.coolway.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@Configuration
public class ServletContextConfig {

    /**
     * context-param
     *
     * @return
     */
    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("org.apache.activemq.embeddedBroker", "true");
                servletContext.setInitParameter("name", "zhangsanContext");
                //servletContext.setAttribute(WebClient.CONNECTION_FACTORY_ATTRIBUTE, ((JmsPoolConnectionFactory) applicationContext.getBean(JmsPoolConnectionFactory.class)).getConnectionFactory());
            }
        };
    }
}
