package com.example.demo.lifecycle;

import com.example.demo.scheduling.SchedulerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyLifecycleListener implements ServletContextListener {

    private final SchedulerManager schedulerManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        schedulerManager.initScheduler();
    }

}
