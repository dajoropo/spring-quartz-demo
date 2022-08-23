package com.example.demo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SchedulerManager {

    @Autowired
    private MyQuartzJobFactory quartzJobFactory;

    private Scheduler scheduler;

    private Map<String, ScheduleBuilder<?>> schedulingConfigMap;

    public void initScheduler() {
        try (InputStream propsInputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my-quartz.properties")) {
            log.info("Creating Quartz scheduler...");
            Properties myQuartzProperties = new Properties();
            myQuartzProperties.load(propsInputStream);

            SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
            schedulerFactory.setQuartzProperties(myQuartzProperties);
            schedulerFactory.afterPropertiesSet();
            schedulerFactory.setJobFactory(quartzJobFactory);

            this.scheduler = schedulerFactory.getScheduler();
            this.schedulingConfigMap = new HashMap<>();

            log.info("Quartz scheduler created.");
        } catch (Exception e) {
            log.error("Unable to create Quartz scheduler", e);
            throw new RuntimeException("Scheduler extension initialization error", e);
        }
    }

}
