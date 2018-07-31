/*
 * Copyright 2014-2017 the original author or authors.
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

import com.xinchao.tech.xinchaoad.session.XinchaoHeaderSessionIdResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Import(EmbeddedRedisConfig.class)
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Value("${spring.redis.hostName}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${session.headerName}")
    private String headerName;
    @Value("${spring.redis.password}")
    private String password;

    //	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		return new LettuceConnectionFactory();
//	}
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        //redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public RedisTemplate<String,Object> redisConnection(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new XinchaoHeaderSessionIdResolver(headerName);
    }

}
// end::class[]
