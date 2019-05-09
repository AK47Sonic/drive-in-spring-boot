package com.sonic;

import com.sonic.bean.Person;
import com.sonic.propertysource.MixPropertySourceFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * ClientApplication
 *
 * @author Sonic
 * @since 2019/4/28
 */
@ImportResource(value = {"classpath:beans.xml"})
// 相当于@Configuration或者 @Component + @ConfigurationProperties(prefix = "person")， bean name 不确定
@EnableConfigurationProperties(value = {Person.class})
// @PropertySource 支持 YAML
@PropertySource(value = {"classpath:custom.yml", "classpath:custom2.properties"}, factory = MixPropertySourceFactory.class)
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).web(WebApplicationType.NONE).run(args);
    }

}
