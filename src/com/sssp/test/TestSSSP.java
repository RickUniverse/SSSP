package com.sssp.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lijichen
 * @date 2020/12/2 - 21:36
 */
public class TestSSSP {
    private XmlWebApplicationContext context = null;

    {
        context = new XmlWebApplicationContext();
        context.setConfigLocations("/WEB-INF/applicationContext.xml");
        //进行刷新
        context.refresh();
    }

    @Test
    public void test() throws SQLException {
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }
}
