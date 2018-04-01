package com.hugo.servlet;

import javax.servlet.*;
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
 * 2018/4/1 3:07
 */

@WebServlet(value = "/nonBlockingThreadPoolAsync",asyncSupported = true)
public class NonBlockingAsyncHelloServlet extends HttpServlet {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100,200,50000L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();

        ServletInputStream inputStream = req.getInputStream();

        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {

            }

            @Override
            public void onAllDataRead() throws IOException {
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

            @Override
            public void onError(Throwable throwable) {
                asyncContext.complete();
            }
        });
    }
}
