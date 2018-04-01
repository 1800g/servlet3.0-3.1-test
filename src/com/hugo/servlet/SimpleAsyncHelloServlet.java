package com.hugo.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 2:35
 */

@WebServlet(value = "/simpleAsyncHello",asyncSupported =true)
public class SimpleAsyncHelloServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取AsyncContext 对象
        AsyncContext asyncContext =req.startAsync();

        //开始，向Servlet申请一个新的线程
        asyncContext.start(()->{
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //通知Servlet容器完成
            asyncContext.complete();
        });
    }
}
