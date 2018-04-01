package com.hugo.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Create by Hugo Liao
 * Email: i@1800g.net
 * 2018/4/1 5:40
 */

@WebListener
public class SimpleContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("===ServletContext 属性" + "Name:" + servletContextAttributeEvent.getName()
                + "Value:" + servletContextAttributeEvent.getValue() + " 添加===");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("===ServletContext 属性" + "Name:" + servletContextAttributeEvent.getName()
                + "Value:" + servletContextAttributeEvent.getValue() + " 移除===");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("===ServletContext 属性" + "Name:" + servletContextAttributeEvent.getName()
                + "Value:" + servletContextAttributeEvent.getValue() + " 替换===");
    }
}
