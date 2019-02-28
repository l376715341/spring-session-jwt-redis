# spring-session-jwt-redis
spring session jwt redis 搭建
spring session redis  搭建 集成jwt 

主要pom：
这是 springboot 和springsession  springsession 集成redis的三个依赖;

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session</artifactId>
            <version>1.3.3.RELEASE</version>
            <exclusions>
                <exclusion>
                    <artifactId>commons-logging</artifactId>
                    <groupId>commons-logging</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        
        
        
        
主要的配置类,都是参考SpringSession 官网配置
https://docs.spring.io/spring-session/docs/current/reference/html5/guides/java-redis.html

EmbeddedRedisConfig  
HttpSessionConfig 
Initializer 

MyHeaderSessionIdResolver 继承 HeaderSessionIdResolver 重写 获取session的规则 主要就是  加入了jwt 解密

