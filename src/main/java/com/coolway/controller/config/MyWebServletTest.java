package com.coolway.controller.config;


import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myWebServletTest", urlPatterns = "/web/myWebServletTest", loadOnStartup = 1, asyncSupported = false, initParams = {
        @WebInitParam(name = "name", value = "zhangsan"),
        @WebInitParam(name = "age", value = "11")
})
public class MyWebServletTest extends HttpServlet {
    private static final long serialVersionUID = -6488889268352650321L;

    @Override
    public void init() throws ServletException {
        super.init();
        //获取init-param
        String name = this.getInitParameter("name");
        System.out.println(name);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("这里是servlet测试！");

        //获取ServletConfig
        ServletConfig servletConfig = getServletConfig();
        //获取ServletContext
        ServletContext servletContext = servletConfig.getServletContext();
        //获取context-param
        String name1 = servletContext.getInitParameter("name");
        System.out.println(name1);

        //HttpServletRequest
        //获取请求头的值
        req.getHeader("User-Agent");
        //获取请求方式
        req.getMethod();
        //获取请求的资源路径
        req.getRequestURI();
        //获取请求的资源定位
        req.getRequestURL();
        //获取IP
        req.getRemoteHost();
        //获取请求参数
        String name = req.getParameter("name");
        String[] names = req.getParameterValues("names");
        //如果乱码，可以指定字符集
        name = new String(name.getBytes("iso-8859-1"), "UTF-8");
        //请求转发
        //斜杠表示http://ip:port/工程名
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/servletTest2");
        requestDispatcher.forward(req, resp);

        //HttpServletResponse
        //设置响应头
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        //回传数据
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("这是回传数据！".getBytes());
        PrintWriter writer = resp.getWriter();
        writer.write("这是回传数据！");
        //请求重定向
        resp.sendRedirect("http://localhost:8000/web/servletTest2");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        this.doGet(req, resp);
        //指定字符集
        req.setCharacterEncoding("UTF-8");
    }
}
