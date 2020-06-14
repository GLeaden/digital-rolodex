package com.rolodex.digitalrolodex;

import java.time.LocalDate;
import java.time.YearMonth;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact{
    /* 
        A contact CAN have all of the below. A contact MUST have an fName and lName.
    */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long   id;
    private String fName;
    private String mName;
    private String lName;
    private String nickname;
    private String phoneNumber;
    private String email; 
    private LocalDate birthdayLocalDate;
    private YearMonth birthdayYearMonth;

    Contact(){}

    Contact(String fName, String mName, String lName, String nickname, String phoneNumber, String email, LocalDate birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdayLocalDate = birthday;
    }
    Contact(String fName, String mName, String lName, String nickname, String phoneNumber, String email, YearMonth birthday) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdayYearMonth = birthday;
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

    public LocalDate getBirthdayLocalDate(){
        return this.birthdayLocalDate;
    }

    public YearMonth getBirthdayYearMonth(){
        return this.birthdayYearMonth;
    }


}