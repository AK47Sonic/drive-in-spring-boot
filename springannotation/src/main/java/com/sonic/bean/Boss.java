package com.sonic.bean;

import org.springframework.stereotype.Component;

/**
 * Boss
 *
 * @auther Sonic
 * @since 2018/12/18
 */
@Component
public class Boss {

    private  Car car;

//    public Boss(){
//        System.out.println("Boss no param constructor...");
//    }


//    @Autowired //@Autowired 标注在构造器上
    public Boss(Car car) { //@Autowired 标注在参数上，如果定义Boss无参构造，有参构造就没有被调用，导致Car没有注入
        this.car = car;
        System.out.println("Boss param constructor...");
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    public void setCar(Car car) {
        System.out.println("Boss set car");
        this.car = car;
    }
}
