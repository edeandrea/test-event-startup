package com.clientapp.listener;

import java.util.Optional;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.ReflectionUtils;

public class AppListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println(String.format("Event: %s", event.getClass().getSimpleName()));

		Optional<ConfigurableApplicationContext> applicationContext = findApplicationContext(event);

		int beanCount = applicationContext
			.map(ConfigurableApplicationContext::getBeanDefinitionCount)
			.orElse(0);

		findEnvironment(event)
			.ifPresent(environment -> System.out.println(String.format("%s: client-app.some-property=%s, bean count = %d", event.getClass().getSimpleName(), environment.getProperty("client-app.some-property"), beanCount)));
	}

	private Optional<ConfigurableApplicationContext> findApplicationContext(Object obj) {
		return Optional.ofNullable(ReflectionUtils.findMethod(obj.getClass(), "getApplicationContext"))
			.map(method -> ReflectionUtils.invokeMethod(method, obj))
			.filter(ConfigurableApplicationContext.class::isInstance)
			.map(ConfigurableApplicationContext.class::cast);
	}

	private Optional<Environment> findEnvironment(Object obj) {
		return Optional.ofNullable(Optional.ofNullable(ReflectionUtils.findMethod(obj.getClass(), "getEnvironment"))
			.map(method -> ReflectionUtils.invokeMethod(method, obj))
			.orElseGet(() -> findApplicationContext(obj)
					.flatMap(this::findEnvironment)
					.orElse(null)
			))
			.filter(Environment.class::isInstance)
			.map(Environment.class::cast);
	}
}
