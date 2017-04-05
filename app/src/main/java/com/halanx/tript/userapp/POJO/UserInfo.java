package com.halanx.tript.userapp.POJO;

/**
 * Created by samarthgupta on 13/02/17.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("AvgRating")
    @Expose
    private Double avgRating;
    @SerializedName("n")
    @Expose
    private Integer n;

    public UserInfo() {
        //Empty constructor
    }

    public UserInfo(String firstName, String lastName, String address, String phoneNo, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

}


