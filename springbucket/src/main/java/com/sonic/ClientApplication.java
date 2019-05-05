package com.sonic;

import com.sonic.propertysource.MixPropertySourceFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * ClientApplication
 *
 * @author Sonic
 * @since 2019/4/28
 */
@ImportResource(value = {"classpath:beans.xml"})
// @PropertySource 支持 YAML
@PropertySource(value = {"classpath:custom.yml", "classpath:custom2.properties"}, factory = MixPropertySourceFactory.class)
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).web(WebApplicationType.NONE).run(args);
    }

}
