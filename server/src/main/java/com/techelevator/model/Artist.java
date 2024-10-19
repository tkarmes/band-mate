package com.techelevator.model;

public class Artist {


    private int id;

    private final String name;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private final String bio;



    public Artist(int id , String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public Artist(String name, String bio) {
        this.name = name;

        this.bio = bio;
    }


    public int getArtistId() {
        return 0;
    }

    public void setName(String updatedName) {

    }


    public void setId(Integer id) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public String getBio() {
        return bio;
    }
}
