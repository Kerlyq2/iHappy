package me.tesis.ihappy.data.entities;
import com.google.gson.annotations.SerializedName;

public class ResponseEntity {

    @SerializedName("status")
    public boolean status;
    @SerializedName("person_id")
    public int person_id;

    public ResponseEntity(boolean status, int person_id) {
        this.status = status;
        this.person_id = person_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
