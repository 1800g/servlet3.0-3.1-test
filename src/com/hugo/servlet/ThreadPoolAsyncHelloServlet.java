package com.hugo.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 3:01
 */

@WebServlet(value = "/threadPoolAsync",asyncSupported = true)
public class ThreadPoolAsyncHelloServlet extends HttpServlet {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100,200,50000L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext =req.startAsync();

        executor.execute(()->{
            new LongRunningProcess().run();

            try {
                asyncContext.getResponse().getWriter().write("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
    }
}
