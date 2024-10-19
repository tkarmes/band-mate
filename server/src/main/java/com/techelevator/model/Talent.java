package com.techelevator.model;

public class Talent {

    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Talent(String name) {

        this.name = name;
    }

    public Talent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Talent() {

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Talent{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String testFirst) {
    }
}
