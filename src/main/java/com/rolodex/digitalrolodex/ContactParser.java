package com.rolodex.digitalrolodex;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {
    public String contactRaw;
    private String[] contactRawSplit;
    private String Fname;
    private String Mname;
    private String Lname;
    private String nickname;
    private String phoneNumber;
    private String email;
    private Date birthday;
    private static final String EMAILREGEX = "(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b"
    private static final String NICKNAMEREGEX = "(?i)[A-Z 0-9]*"
    private static final String PHONENUMREGEX = "\b[0-9]{10}\b|[0-9]{3}.[0-9]{3}.[0-9]{4}"

    /* 
    Each contact has a single-space separated First Name (or initial), optinal Middle Name (or initial), and Last Name (or initial)
    */
    public ContactParser parseName(){
        this.Fname = this.contactRaw.split(" ");
    }

    /*
    Here we process 3 regular expressions to identify contact attributes and remove them from the string for easier manipulation later.
    */ */
    public ContactParser parseRegexPatterns(){
        Pattern nickRegex = Pattern.compile(NICKNAMEREGEX);
        Matcher nickMatcher = nickRegex.matcher(contactRaw);

        Pattern emailRegex = Pattern.compile(EMAILREGEX);
        Matcher emailMatcher = emailRegex.matcher(contactRaw);

        Pattern phoneRegex = Pattern.compile(PHONENUMREGEX);
        Matcher phoneMatcher = phoneRegex.matcher(contactRaw);
        // Found a nickname, adding it to the contact and removing from the input string.
        if (nickMatcher.find() == true){
            this.nickname = nickMatcher.group(1);
            this.contactRaw = this.contactRaw.substring(0, nickMatcher.start()) + this.contactRaw.substring(nickMatcher.end(), this.contactRaw.length());
        }
        // Found an email, adding it to the contact and removing from the input string.
        if (emailMatcher.find() == true){
            this.email = emailMatcher.group(1);
            this.contactRaw = this.contactRaw.substring(0, emailMatcher.start()) + this.contactRaw.substring(emailMatcher.end(), this.contactRaw.length());
        }
        // Found a phone number, adding it to the contact and removing from the input string.
        if (phoneMatcher.find() == true){
            this.phoneNumber = phoneMatcher.group(1);
            this.contactRaw = this.contactRaw.substring(0, phoneMatcher.start()) + this.contactRaw.substring(phoneMatcher.end(), this.contactRaw.length());
        }
        return this;
    }

    public ContactParser parseBirthday(){

    }

    public Contact parseContacts(String contactRaw){
        this.contactRaw = contactRaw;
        parseRegexPatterns();

        this.contactRawSplit = contactRaw.split(" ");
        
    }
}
string = string.substring(0, start_index) + string.substring(end_index, string.length());
