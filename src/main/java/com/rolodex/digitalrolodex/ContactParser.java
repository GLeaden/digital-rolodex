package com.rolodex.digitalrolodex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {
    private static final String EMAILREGEX = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b";
    private static final String NICKNAMEREGEX = "\"(.*?)\"";
    private static final String PHONENUMREGEX = "\\b[0-9]{10}\\b|[0-9]{3}.[0-9]{3}.[0-9]{4}";

    public String contactRaw;
    private String[] contactRawSplit;
    private String fName;
    private String mName = "";
    private String lName;
    private String nickname;
    private String phoneNumber;
    private String email;
    //TODO: Convert to local date
    private String birthday;
    private Boolean hasBirthday = false;

    /* 
    Each contact has a single-space separated First Name (or initial), optinal Middle Name (or initial), and Last Name (or initial)
    */
    private void parseName(){
        Integer lenOffset = 1;

        if (this.hasBirthday){
            lenOffset++;
        }
        if (contactRawSplit.length - lenOffset == 2){
            this.fName = contactRawSplit[0];
            this.mName = contactRawSplit[1];
            this.lName = contactRawSplit[2];
        }
        else{
            this.fName = contactRawSplit[0];
            this.lName = contactRawSplit[1];
        }
    }

    /*
    Here we process 3 regular expressions to identify contact attributes and remove them from the string for easier manipulation later.
    */
    private void parseRegexPatterns(){


        Pattern nickRegex = Pattern.compile(NICKNAMEREGEX);
        Matcher nickMatcher = nickRegex.matcher(this.contactRaw);
        // Found a nickname, adding it to the contact and removing from the input string.
        if (nickMatcher.find() != false){
            this.nickname = nickMatcher.group(1);
            this.contactRaw = this.contactRaw.substring(0, nickMatcher.start()) + this.contactRaw.substring(nickMatcher.end(), this.contactRaw.length());
        }

        Pattern emailRegex = Pattern.compile(EMAILREGEX, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailRegex.matcher(this.contactRaw);
        // Found an email, adding it to the contact and removing from the input string.
        if (emailMatcher.find() != false){
            this.email = emailMatcher.group(0);
            this.contactRaw = this.contactRaw.substring(0, emailMatcher.start()) + this.contactRaw.substring(emailMatcher.end(), this.contactRaw.length());
        }

        Pattern phoneRegex = Pattern.compile(PHONENUMREGEX);
        Matcher phoneMatcher = phoneRegex.matcher(this.contactRaw);
        // Found a phone number, adding it to the contact and removing from the input string.
        if (phoneMatcher.find() != false){
            this.phoneNumber = phoneMatcher.group(0);
            this.contactRaw = this.contactRaw.substring(0, phoneMatcher.start()) + this.contactRaw.substring(phoneMatcher.end(), this.contactRaw.length());
        }
    }

    /*
    Because birthday is always last, we have an easy check to see if it exists.
    */
    private void parseBirthday(){
        String birthday = contactRawSplit[contactRawSplit.length-1];

        // If our first digit is a number, we have a date and can continue working.
        if (Character.isDigit(birthday.charAt(0))){
            this.birthday = birthday;
            this.hasBirthday = true;
        }
    }

    public Contact parseContact(String contactRaw){
        this.contactRaw = contactRaw;
        parseRegexPatterns();
        this.contactRawSplit = this.contactRaw.split("\\s+");
        parseBirthday();
        parseName();
        return new ContactBuilder().setName(this.fName, this.mName, this.lName).setEmail(this.email).setNickname(this.nickname).setPhoneNumber(phoneNumber).build();
    }
}