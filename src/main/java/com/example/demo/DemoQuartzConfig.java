package com.example.demo;

import com.example.demo.scheduling.MyQuartzJobFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class DemoQuartzConfig {

    private ApplicationContext applicationContext;

    public DemoQuartzConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        log.info("Hello world from MyEngineConfig...");
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MyQuartzJobFactory myQuartzJobFactory() {
        MyQuartzJobFactory jobFactory = new MyQuartzJobFactory();
        log.info("Configuring Job Factory...");
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

}
