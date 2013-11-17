package com.tiarebalbi.events.test.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.tiarebalbi.events.config.annotation.EnableObserver;
import com.tiarebalbi.events.initialization.BeanParseInit;
import com.tiarebalbi.events.initialization.InitializationBeanProcess;

@EnableObserver
@Configuration
@ComponentScan(basePackages={"com.tiarebalbi.events.test.simple" })
public class ApplicationTestContext {
	
	@Autowired(required=true)
	private BeanParseInit beanParse;
	
	@Bean
	public InitializationBeanProcess initProcess() {
		return new InitializationBeanProcess();
	}
	
	@Bean(name="executor")
	public Executor executorBean() {
		SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		simpleAsyncTaskExecutor.setThreadNamePrefix("Observer-Async-");
		return simpleAsyncTaskExecutor;
	}
}
