package com.example.abstractDemo;

/**
 * @author zkCheng
 * @date 2022/11/16 11:11
 */
// 爷爷类（抽象类）
public abstract class Old {
    private String type;
    private String name;
    private String color;
    private int age;

    public abstract void eat();
    public abstract void sleep();
    public abstract void weight();



    public void ear() {
        System.out.println("圆圆的");
    }
    public void tail() {
        System.out.println("尾部是卷的");
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


}

