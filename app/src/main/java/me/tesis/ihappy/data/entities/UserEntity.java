package me.tesis.ihappy.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class UserEntity {

    private int id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;

    public UserEntity(String first_name,String last_name,String email){
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
    }

    protected UserEntity(Parcel in) {
        id = in.readInt();
        username = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }


    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(email);
    }
}
