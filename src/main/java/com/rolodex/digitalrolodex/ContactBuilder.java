package com.rolodex.digitalrolodex;

import java.time.LocalDate;

//Builder pattern for contact creation

public class ContactBuilder {
    private String fName;
    private String mName = "";
    private String lName;
    private String nickname = "";
    private String phoneNumber = "";
    private String email = ""; 
    private String birthday;

    public ContactBuilder(){}
    
    public ContactBuilder(String fName, String mName, String lName, String nickname, String phoneNumber, String email, String birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
    }

    public ContactBuilder setName(String fName, String lName){
        this.fName = fName;
        this.lName = lName;
        return this;
    }

    // Overloaded for middle name
    public ContactBuilder setName(String fName, String mName, String lName){
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        return this;
    }

    public ContactBuilder setNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public ContactBuilder setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ContactBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public ContactBuilder setBirthday(String birthday){
        this.birthday = birthday;
        return this;
    }

    public Contact build(){
        return new Contact(fName, mName, lName, nickname, phoneNumber, email, birthday);
    }

}