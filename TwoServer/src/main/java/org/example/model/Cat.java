package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cat {
    String name;
    int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Cat() {}
}
