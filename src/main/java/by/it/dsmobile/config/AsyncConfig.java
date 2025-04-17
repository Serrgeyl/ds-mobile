package by.it.dsmobile.config;

import by.it.dsmobile.config.property.SmsPoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    public static final String SMS_EXECUTOR = "smsExecutor";

    @Bean(name = SMS_EXECUTOR)
    public Executor eventAsyncExecutor(final SmsPoolProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getMin());
        executor.setMaxPoolSize(properties.getMax());
        executor.setKeepAliveSeconds(properties.getLifetime());
        executor.setThreadNamePrefix("sms-executor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(120);
        executor.initialize();
        return executor;
    }

}
