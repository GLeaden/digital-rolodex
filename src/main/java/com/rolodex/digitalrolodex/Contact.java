package com.rolodex.digitalrolodex;

import java.util.Date;

public class Contact{
    /* 
        A contact CAN have all of the below. A contact MUST have a name.
    */
    private String name; // required
    private String nickname;
    private String phoneNumber;
    private String email; 
    private Date birthday;

    Contact(String name, String nickname, String phoneNumber, String email, Date birthday) {
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this. birthday = birthday;
    }

    public String getName(){
        return this.name;
    }



}