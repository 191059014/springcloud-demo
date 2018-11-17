package com.hb.style.test;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessWebTestController implements InitializingBean {

    @Autowired
    private HikariDataSource dataSource;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println(111);
    }

    @GetMapping("/test")
    public void test() {
        //System.out.println(111);
    }
}
