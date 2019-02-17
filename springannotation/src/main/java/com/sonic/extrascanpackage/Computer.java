package com.sonic.extrascanpackage;

/**
 * Computer
 *
 * @author Sonic
 * @since 2019/2/17
 */
public class Computer {

    public Computer(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                '}';
    }
}
