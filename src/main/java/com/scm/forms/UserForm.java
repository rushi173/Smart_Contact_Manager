package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Min 3 Characters is required")
    private String uname;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    private String uemail;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String upassword;

    @NotBlank(message = "About is required")
    private String uabout;

    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    private String uponeNo;

    // Default constructor
    public UserForm() {
    }

    // All-args constructor
    public UserForm(String uname, String uemail, String upassword, String uabout, String uponeNo) {
        this.uname = uname;
        this.uemail = uemail;
        this.upassword = upassword;
        this.uabout = uabout;
        this.uponeNo = uponeNo;
    }

    // Getters and Setters
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUabout() {
        return uabout;
    }

    public void setUabout(String uabout) {
        this.uabout = uabout;
    }

    public String getUponeNo() {
        return uponeNo;
    }

    public void setUponeNo(String uponeNo) {
        this.uponeNo = uponeNo;
    }

    // toString method
    @Override
    public String toString() {
        return "UserForm{" +
                "uname='" + uname + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uabout='" + uabout + '\'' +
                ", uponeNo='" + uponeNo + '\'' +
                '}';
    }
}
