# test-event-startup

This contains 2 child projects to demonstrate the issue discussed at https://stackoverflow.com/questions/52516720/best-way-in-custom-spring-boot-starter-library-to-read-current-properties-and-se
- [config-server](config-server)
    - Will run on port 8888
- [client-app](client-app)
    - Will run on port 8080
    
Run the [config-server](config-server) application first. Then run the [client-app](client-app) application. When looking at the logs for [client-app](client-app) you should see something like

```
Event: ApplicationStartingEvent
Event: ApplicationStartingEvent
com.clientapp.listener.AppEnvironmentPostProcessor: client-app.some-property=null
Event: ApplicationEnvironmentPreparedEvent
ApplicationEnvironmentPreparedEvent: client-app.some-property=null, bean count = 0
Initializing Context - Got client-app.some-property=null, bean count = 6
Event: ApplicationPreparedEvent
ApplicationPreparedEvent: client-app.some-property=null, bean count = 11
2018-10-01 16:53:21.806  INFO 56665 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@3c46e67a: startup date [Mon Oct 01 16:53:21 EDT 2018]; root of context hierarchy
2018-10-01 16:53:22.014  INFO 56665 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'configurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$8e1959d7] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
Event: ApplicationPreparedEvent
ApplicationPreparedEvent: client-app.some-property=null, bean count = 23
Event: ContextRefreshedEvent
ContextRefreshedEvent: client-app.some-property=null, bean count = 23
Event: ApplicationStartedEvent
ApplicationStartedEvent: client-app.some-property=null, bean count = 23
Event: ApplicationReadyEvent
ApplicationReadyEvent: client-app.some-property=null, bean count = 23
com.clientapp.listener.AppEnvironmentPostProcessor: client-app.some-property=some-value
Event: ApplicationEnvironmentPreparedEvent
ApplicationEnvironmentPreparedEvent: client-app.some-property=some-value, bean count = 0

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.5.RELEASE)

Initializing Context - Got client-app.some-property=A new value from config server, bean count = 6
2018-10-01 16:53:22.468  INFO 56665 --- [           main] com.clientapp.ClientAppApplication       : No active profile set, falling back to default profiles: default
Event: ApplicationPreparedEvent
ApplicationPreparedEvent: client-app.some-property=A new value from config server, bean count = 7
Event: ParentContextAvailableEvent
ParentContextAvailableEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ParentContextAvailableEvent
ParentContextAvailableEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ApplicationPreparedEvent
ApplicationPreparedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ApplicationPreparedEvent
ApplicationPreparedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ContextRefreshedEvent
ContextRefreshedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ContextRefreshedEvent
ContextRefreshedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ServletWebServerInitializedEvent
ServletWebServerInitializedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ServletWebServerInitializedEvent
ServletWebServerInitializedEvent: client-app.some-property=A new value from config server, bean count = 291
2018-10-01 16:53:24.698  INFO 56665 --- [           main] com.clientapp.ClientAppApplication       : Started ClientAppApplication in 3.266 seconds (JVM running for 3.811)
Event: ApplicationStartedEvent
ApplicationStartedEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ApplicationStartedEvent
ApplicationStartedEvent: client-app.some-property=A new value from config server, bean count = 291
TestRunner ran!!!
Event: ApplicationReadyEvent
ApplicationReadyEvent: client-app.some-property=A new value from config server, bean count = 291
Event: ApplicationReadyEvent
ApplicationReadyEvent: client-app.some-property=A new value from config server, bean count = 291
```
