package com.sssp.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lijichen
 * @date 2020/12/2 - 21:54
 */
@Controller
public class HelloSSSP implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() throws SQLException {
        DataSource bean = applicationContext.getBean(DataSource.class);
        return bean.getConnection().toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
