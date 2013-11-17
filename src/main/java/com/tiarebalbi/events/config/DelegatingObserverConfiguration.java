package com.tiarebalbi.events.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tiarebalbi.events.initialization.BeanParseInit;
import com.tiarebalbi.events.initialization.InitializationBeanProcess;

@Configuration
@ComponentScan(basePackages="com.tiarebalbi.events.process")
public class DelegatingObserverConfiguration {
	
	@Bean
	public InitializationBeanProcess initProcess() {
		return new InitializationBeanProcess();
	}
	
	@Bean
	public BeanParseInit beanParse() {
		return new BeanParseInit();
	}

}