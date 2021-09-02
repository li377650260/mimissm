package com.tonpower.listener;

import com.tonpower.domain.ProductType;
import com.tonpower.service.ProductTypeService;
import com.tonpower.service.impl.ProductTypeServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 22:36
 */

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = (ProductTypeService) context.getBean("productTypeServiceImpl");
        List<ProductType> list = productTypeService.getAll();
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("typeList",list);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
