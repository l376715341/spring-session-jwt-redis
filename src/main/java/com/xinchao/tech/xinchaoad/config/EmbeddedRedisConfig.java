/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xinchao.tech.xinchaoad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@Profile("embedded-redis")
public class EmbeddedRedisConfig {

	private static final String DOCKER_IMAGE = "redis:4.0.9";
@Value("${spring.redis.hostName}")
	private String hostName;
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;

//	@Bean
//	public GenericContainer redisContainer() {
//		GenericContainer redisContainer = new GenericContainer(DOCKER_IMAGE)
//				.withExtraHost(hostName,host)
//				.withExposedPorts(port);
//		redisContainer.start();
//		return redisContainer;
//	}

	@Bean
	@Primary
	public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setHostName(hostName);
        //redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}

}
