package com.example.loginregister;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

@Entity
public class PersonalDetails implements Serializable {

    private String fname,lname,mob_no;

    @PrimaryKey
    @NonNull
    private String emailID;

   // private boolean encrypt;
    @NonNull
    private String password;

    public PersonalDetails(String fname, String lname, String mob_no, @NonNull String emailID, String password) {
        this.fname = fname;
        this.lname = lname;
        this.mob_no = mob_no;
        this.emailID = emailID;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMob_no() {
        return mob_no;
    }

    public String getEmailID() {
        return emailID;
    }

   /* public boolean isEncrypt() {
        return encrypt;
    }*/

    public String getPassword() {
        return password;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
