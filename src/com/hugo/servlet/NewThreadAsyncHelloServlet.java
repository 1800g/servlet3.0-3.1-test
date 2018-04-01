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
 * 2018/4/1 2:56
 */

@WebServlet(value = "/newThreadAsync",asyncSupported = true)
public class NewThreadAsyncHelloServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext=req.startAsync();

        Runnable runnable = ()->{
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        };

        new Thread(runnable).start();
    }
}
