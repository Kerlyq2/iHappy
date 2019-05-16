package me.tesis.ihappy.data.entities;

import com.google.gson.annotations.SerializedName;

public class PersonEntity {

    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("apellidos")
    public String apellidos;
    @SerializedName("email")
    public String email;



    public PersonEntity(String username, String password, String nombre, String apellidos, String email) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
