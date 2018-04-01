package com.hugo.servlet;

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

@WebServlet("/syncHello")
public class SyncHelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new LongRunningProcess().run();
        resp.getWriter().write("Hello World!");
    }

}
