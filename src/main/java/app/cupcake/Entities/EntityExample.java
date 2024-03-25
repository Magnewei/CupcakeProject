package org.example.Entities;

public class EntityExample {
    private String name;
    private String description;
    private int age;

    public EntityExample(String name, String description, int age) {
        this.name = name;
        this.description = description;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAge() {
        return age;
    }
}
