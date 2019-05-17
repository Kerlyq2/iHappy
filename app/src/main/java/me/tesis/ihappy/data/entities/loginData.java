package me.tesis.ihappy.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class loginData {

    @SerializedName("status")
    public boolean status;
    @SerializedName("person_id")
    public String person_id;

    public loginData(boolean status, String person_id) {
        this.status = status;
        this.person_id = person_id;
    }

    public boolean isStatus() {
        return status=true;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }
}
