package com.quintifi.kafkaeventsutil.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @author nchopra
 *
 */
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport {

	@Value("${async.corepool.size}")
	private Integer corePoolSize;
	@Value("${async.maxpool.size}")
	private Integer maxPoolSize;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setThreadNamePrefix("ServerLogThread-");
		executor.initialize();
		return executor;
	}

}
