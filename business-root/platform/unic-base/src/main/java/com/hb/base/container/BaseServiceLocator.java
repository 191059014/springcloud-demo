package com.hb.base.container;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by huangbiao on 2018/10/5.
 */
@Component
public class BaseServiceLocator implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ConcurrentMap<String, ApplicationContext> acMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext ac = event.getApplicationContext();
        String displayName = ac.getDisplayName();
        if (logger.isInfoEnabled()) {
            logger.info("ApplicationContext:{} is completed!", displayName);
        }
        ApplicationContext parent = ac.getParent();
        if (parent != null) {
            String parentName = parent.getDisplayName();
            if (logger.isInfoEnabled()) {
                logger.info("Parent ApplicationContext:{} is completed!", parentName);
            }
            System.out.println("Application Platform has successfully started!");
            System.out.println("   ");
            System.out.println("You can enjoy it!");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String id = null;
        if (applicationContext != null) {
            id = applicationContext.getDisplayName();
            if (StringUtils.isBlank(id)) {
                id = applicationContext.getId();
            }
        }
        if (StringUtils.isNotBlank(id)) {
            acMap.put(id, applicationContext);
            if (logger.isInfoEnabled()) {
                logger.info("ApplicationContext:" + id + " is put!");
            }
        } else {
            if (logger.isErrorEnabled()) {
                logger.error("ApplicationContext has no displayName or id!");
            }
        }

    }

    /**
     * 通过bean的名称获取实例
     *
     * @param beanName bean名称
     * @return 实例
     */
    public static Object getBean(String beanName) {
        for (ApplicationContext ac : acMap.values()) {
            if (ac != null) {
                if (ac.containsBean(beanName)) {
                    return ac.getBean(beanName);
                }
            }
        }
        return null;
    }

}
