package com.sonic.driveinspringboot.bootstrap;

import com.sonic.driveinspringboot.bean.Dog;
import com.sonic.driveinspringboot.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Repository Bootstrap
 * Create by Sonic on 2018/11/25
 */
@ComponentScan(basePackages = {"com.sonic.driveinspringboot.repository", "com.sonic.driveinspringboot.bean"})
/*把用到的资源导入到当前容器中*/
@Import(value = {Dog.class})
public class RepositoryBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = new SpringApplicationBuilder(RepositoryBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        // myFirstLevelRepository bean 是否存在
        MyFirstLevelRepository myFirstLevelRepository = ac.getBean("myFirstLevelRepository", MyFirstLevelRepository.class);
//        Dog dog = ac.getBean("Dog", Dog.class);
//        System.out.println("Dog full name: " + dog.toString());

        Dog dog = ac.getBean(Dog.class);
        System.out.println("Dog : " + dog.toString());

        System.out.println("myFirstLevelRepository: " + myFirstLevelRepository.toString());

        for(String beanName : ac.getBeanDefinitionNames()){
            System.out.println(beanName);
        }
        //关闭上下文
        ac.close();
    }
}
