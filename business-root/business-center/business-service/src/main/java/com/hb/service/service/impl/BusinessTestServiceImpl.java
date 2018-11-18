package com.hb.service.service.impl;

import com.hb.base.repository.IBaseRepository;
import com.hb.rbac.entity.RbacUserEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("service/service/businessTestService")
public class BusinessTestServiceImpl {

    @Autowired
    private IBaseRepository baseRepository;

    //@Override
    @GetMapping("/getPasswordByUserName/{userName}")
    public String getPasswordByUserName(@PathVariable("userName") String userName) {
        return "123456";
    }

    //@Override
    @GetMapping("/insert")
    public void insert() {
        RbacUserEO rbacUserEO = new RbacUserEO();
        rbacUserEO.setUserName("zhangsan");
        rbacUserEO.setPassword("123456");
        //entityManager.persist(rbacUserEO);
        List<RbacUserEO> all = null;
        System.out.println(all);
    }

    //@Override
    @GetMapping("/insertAnother")
    public void insertAnother() {
        RbacUserEO rbacUserEO = new RbacUserEO();
        rbacUserEO.setUserName("zhangsan");
        rbacUserEO.setPassword("123456");
//        Session session = sessionFactory.getCurrentSession();
//        session.save(rbacUserEO);
    }


}
