package com.hugo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 4:58
 */

@WebListener
public class SimpleContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("===ServletContext初始化===");
        System.out.println(servletContextEvent.getServletContext().getServerInfo());
        System.out.println(servletContextEvent.getServletContext().getClassLoader().toString());
        servletContextEvent.getServletContext().setAttribute("Name","Hugo Liao");
        servletContextEvent.getServletContext().setAttribute("rolesAllowed","R1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("===ServletContext销毁===");
        servletContextEvent.getServletContext().removeAttribute("Name");
    }
}
