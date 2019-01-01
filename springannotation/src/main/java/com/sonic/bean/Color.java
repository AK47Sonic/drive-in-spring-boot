package com.sonic.bean;

/**
 * Color
 *
 * @auther Sonic
 * @since 2018/12/16
 */
public class Color {

    private Car car;

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
