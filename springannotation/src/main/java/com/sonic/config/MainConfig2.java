package com.sonic.config;

import com.sonic.bean.ColorFactoryBean;
import com.sonic.bean.Person;
import com.sonic.bean.Red;
import com.sonic.condition.LinuxCondition;
import com.sonic.condition.MyImportBeanDefinitionRegistrar;
import com.sonic.condition.MyImportSelector;
import com.sonic.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 *
 * @auther Sonic
 * @since 2018/12/15
 */
//类中组件统一设置：满足当前条件，类中所有配置的Bean注册才会生效，和内部@Conditional条件冲突时候，内部条件覆盖外部条件
//@Conditional(value = {WindowsCondition.class})
@Conditional(value = {LinuxCondition.class})
@Configuration
@Import({Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) //id为导入组件的全类名
public class MainConfig2 {

    @Bean("person") //默认是单例
    /**
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype 多例, IOC容器启动并不会去调用方法创建对象放在容器中
     * 每次获取的时候才会调用方法创建对象
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton 单例（默认）IOC容器启动会调用方法创建对象
     * 放到IOC容器中，以后每次获取直接从容器中拿。
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request 同一个请求创建一个实例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION session 同一个session创建一个实例
     *
     * 懒加载：单实例bean，默认在容器启动的时候创建对象， 容器启动不创建对象，第一次使用Bean创建对象，并初始化
     */
//    @Scope(value = "prototype")
//    @Lazy
    public Person person() {
//        System.out.println("Add person bean into IOC container");
        return new Person("Sonic", 25);
    }

    /**
     * @Conditional
     */
    @Conditional(value = {WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional(value = {LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
