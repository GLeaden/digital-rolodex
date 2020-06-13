package com.rolodex.digitalrolodex;

import java.util.Date;

//Builder pattern for contact creation

public class ContactBuilder {
    private String name; // required
    private String nickname = "";
    private String phoneNumber = "";
    private String email = ""; 
    private Date birthday;

    ContactBuilder(String name, String nickname, String phoneNumber, String email, Date birthday) {
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
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

    public ContactBuilder setBirthday(Date birthday){
        this.birthday = birthday;
        return this;
    }

    public Contact build(){
        return new Contact(name, nickname, phoneNumber, email, birthday);
    }

}