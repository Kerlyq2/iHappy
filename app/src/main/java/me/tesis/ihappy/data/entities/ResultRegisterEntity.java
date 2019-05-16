package me.tesis.ihappy.data.entities;

public class ResultRegisterEntity {
    private boolean status;

    public ResultRegisterEntity(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
