package com.rolodex.digitalrolodex;

import java.time.LocalDate;

public class Contact{
    /* 
        A contact CAN have all of the below. A contact MUST have an fName and lName.
    */
    private String fName;
    private String mName;
    private String lName;
    private String nickname;
    private String phoneNumber;
    private String email; 
    private String birthday;

    Contact(){}

    Contact(String fName, String mName, String lName, String nickname, String phoneNumber, String email, String birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this. birthday = birthday;
    }

    public String getName(){
        return this.fName + " " + this.mName + " " + this.lName;
    }

    public String getNickname(){
        return this.nickname;
    }

    public String getEmail(){
        return this.email;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getBirthday(){
        return this.birthday;
    }


}