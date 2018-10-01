package com.clientapp.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class AppContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println(String.format("Initializing Context - Got client-app.some-property=%s, bean count = %d", applicationContext.getEnvironment().getProperty("client-app.some-property"), applicationContext.getBeanDefinitionCount()));
	}
}
