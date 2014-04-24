package org.greencheek.example.config;

import org.greencheek.example.service.AsyncDeferredResultExecutor;
import org.greencheek.example.service.DeferredResultExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dominictootell on 24/04/2014.
 */
// If you DO NOT PUT THIS... then the  deferredResultExecutor will run synchronously
@EnableAsync
@Configuration
public class TaskExecutorConfiguration {
    @Autowired
    private Environment env;

    @Bean(name = "executor",destroyMethod = "shutdownNow")
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(maxNumberOfExecutorThreads());
    }

    @Bean(name= "asyncTaskExecutor")
    public AsyncTaskExecutor getSyncUrlRequest() {
        return new ConcurrentTaskExecutor(getExecutor());
    }

    @Bean(name = "asyncExecutorMaxThreads")
    public int maxNumberOfExecutorThreads() {
        try {
            return Integer.parseInt(env.getProperty("async.exec.maxThreads", "10"));
        } catch(NumberFormatException exception) {
            return 1000;
        }
    }

    @Bean(name = "deferredResultExecutor")
    public DeferredResultExecutor getDeferredResultExector() {
        return new AsyncDeferredResultExecutor();
    }


}
