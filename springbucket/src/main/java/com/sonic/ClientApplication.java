package com.sonic;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ClientApplication
 *
 * @author Sonic
 * @since 2019/4/28
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).web(WebApplicationType.NONE).run(args);
    }

}
