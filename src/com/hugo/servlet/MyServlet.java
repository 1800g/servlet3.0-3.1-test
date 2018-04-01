package com.hugo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 2:35
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = "manager"))
@WebServlet(name = "MyServlet",urlPatterns = "/MyServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String username = getCookie(cookies, "userName");
        String userAge = getCookie(cookies, "userAge");
        if (username == null) {
            resp.addCookie(new Cookie("userName", "hugo"));
        }
        if (userAge == null) {
            resp.addCookie(new Cookie("userAge", "23"));
        }
        resp.getHeader("Set-Cookie2");
        resp.getOutputStream().print("<html></body><h1>Hello World!</h1></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private String getCookie(Cookie[] cookies, String key) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
