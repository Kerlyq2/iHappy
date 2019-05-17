package me.tesis.ihappy.data.entities;

import com.google.gson.annotations.SerializedName;

public class PersonEntity {

    @SerializedName("id")
    public String id;
    @SerializedName("user")
    public UserEntity user;
    @SerializedName("genre")
    public String genre;
    @SerializedName("born_date")
    public String born_date;
    @SerializedName("person_image")
    public String person_image;


    public PersonEntity(String id, UserEntity user, String genre, String born_date, String person_image) {
        this.id = id;
        this.user = user;
        this.genre = genre;
        this.born_date = born_date;
        this.person_image = person_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBorn_date() {
        return born_date;
    }

    public void setBorn_date(String born_date) {
        this.born_date = born_date;
    }

    public String getPerson_image() {
        return person_image;
    }

    public void setPerson_image(String person_image) {
        this.person_image = person_image;
    }
}
