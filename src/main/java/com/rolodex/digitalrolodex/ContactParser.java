package com.rolodex.digitalrolodex;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactParser {
    private static final String EMAILREGEX = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b";
    private static final String NICKNAMEREGEX = "\"(.*?)\"";
    private static final String PHONENUMREGEX = "\\b[0-9]{10}\\b|\\(?[0-9]{3}\\)?.[0-9]{3}.[0-9]{4}";

    public String contactRaw;
    private String[] contactRawSplit;
    private String fName;
    private String mName = "";
    private String lName;
    private String nickname;
    private String phoneNumber;
    private String email;
    private LocalDate birthdayLocalDate;
    private YearMonth birthdayYearMonth;
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
        if (Character.isDigit(birthday.charAt(0))){
            this.hasBirthday = true;
            this.birthdayLocalDate = validateLocalDate(birthday);
            if (Objects.isNull(this.birthdayLocalDate)){
                this.birthdayYearMonth = validateYearMonth(birthday);
            }
        }
        else{
            //there is no birthday
        }
    }

    private LocalDate validateLocalDate(String date){
        List<DateTimeFormatter> localDateFormatters = new ArrayList<>();

        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM-dd-uuuu" ));// 01-23-2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM/dd/uuuu" ));// 01/23/2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM.dd.uuuu" ));// 01.23.2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM-d-uuuu" )); // 01-3-2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM/d/uuuu" )); // 01/3/2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "MM.d.uuuu" )); // 01.3.2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "M-d-uuuu" ));  // 1-3-2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "M/d/uuuu" ));  // 1/3/2019
        localDateFormatters.add(DateTimeFormatter.ofPattern( "M.d.uuuu" ));  // 1.3.2019

        LocalDate dateTime = null;

        for (DateTimeFormatter formatter: localDateFormatters){
            try{
                dateTime = LocalDate.parse(date,formatter);
            } catch ( DateTimeParseException e ) {
                // Ignoring exception, expected. 
            }
        }
        return dateTime;
    }

    private YearMonth validateYearMonth(String date){
        List<DateTimeFormatter> yearMonthFormatters = new ArrayList<>();
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "MM-uuuu" ));   // 01-2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "M-uuuu" ));    // 1-2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "MM.uuuu" ));   // 01.2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "M.uuuu" ));    // 1.2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "MM/uuuu" ));   // 01/2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "M/uuuu" ));    // 1/2019
        yearMonthFormatters.add(DateTimeFormatter.ofPattern( "uuuu" ));      // 2019

        YearMonth monthTime = null;
        for (DateTimeFormatter formatter: yearMonthFormatters){
            try{
                monthTime = YearMonth.parse(date, formatter);
            } catch (DateTimeParseException e){
                // Ignoring exception, expected.
            }
        }
        return monthTime;
    }

    public Contact parseContact(String contactRaw){
        Contact newContact;
        this.contactRaw = contactRaw;
        parseRegexPatterns();
        this.contactRawSplit = this.contactRaw.split("\\s+");
        Integer z=0;
        for(String i:contactRawSplit){
            System.out.println(i + z++);
        }
        if (this.contactRawSplit.length-1 >= 4){
            //invalid contact information, should have at maximium fName, mName, lName, and birthday.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //String is not formatted correctly
        }
        parseBirthday();
        parseName();
        if (birthdayLocalDate == null && birthdayYearMonth != null){
            newContact = new ContactBuilder().setName(this.fName, this.mName, this.lName).setEmail(this.email).setNickname(this.nickname).setPhoneNumber(this.phoneNumber).setBirthday(this.birthdayYearMonth).build();
        }
        else{
            newContact = new ContactBuilder().setName(this.fName, this.mName, this.lName).setEmail(this.email).setNickname(this.nickname).setPhoneNumber(this.phoneNumber).setBirthday(this.birthdayLocalDate).build();
        }
        return newContact;
    }
}