package com.adapt.rak.main.models;

/**
 * Created by Jasmin Raju on 04/01/2022
 */
public class Variable {
    private String name;
    private String value;

    public Variable() {
    }

    public Variable(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
