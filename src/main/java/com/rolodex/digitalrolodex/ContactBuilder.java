package com.rolodex.digitalrolodex;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

//Builder pattern for contact creation

public class ContactBuilder {
    private String fName;
    private String mName = "";
    private String lName;
    private String nickname = "";
    private String phoneNumber = "";
    private String email = ""; 
    private LocalDate birthdayLocalDate;
    private YearMonth birthdayYearMonth;
    private Year      birthdayYear;
    
    public ContactBuilder(){}
    
    public ContactBuilder(String fName, String mName, String lName, String nickname, String phoneNumber, String email, LocalDate birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdayLocalDate = birthday;
    }
    public ContactBuilder(String fName, String mName, String lName, String nickname, String phoneNumber, String email, YearMonth birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdayYearMonth = birthday;
    }
    public ContactBuilder(String fName, String mName, String lName, String nickname, String phoneNumber, String email, Year birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdayYear = birthday;
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

    //Overloaded for the various possible birthday Types
    public ContactBuilder setBirthday(LocalDate birthday){
        this.birthdayLocalDate = birthday;
        return this;
    }
    public ContactBuilder setBirthday(YearMonth birthday){
        this.birthdayYearMonth = birthday;
        return this;
    }
    public ContactBuilder setBirthday(Year birthday){
        this.birthdayYear = birthday;
        return this;
    }

    public Contact build(){

        Contact newContact = new Contact();

        if (birthdayYearMonth != null){
            newContact = new Contact(this.fName, this.mName, this.lName, this.nickname, this.phoneNumber, this.email, this.birthdayYearMonth);
        }
        else if (birthdayYear != null){
            newContact = new Contact(this.fName, this.mName, this.lName, this.nickname, this.phoneNumber, this.email, this.birthdayYear);
        }
        else{
            newContact = new Contact(this.fName, this.mName, this.lName, this.nickname, this.phoneNumber, this.email, this.birthdayLocalDate);
        }

        return newContact;
    }

}