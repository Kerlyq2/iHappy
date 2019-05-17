package me.tesis.ihappy.data.entities;

import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("id")
    public int id;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("username")
    public String username;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("email")
    public String email;

    public UserEntity(int id, String nombre, String username, String first_name, String last_name, String email) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
