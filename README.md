# Digital Rolodex 📇

### About the project

I'm a popular person. I've always been a popular person. In fact, I've been popular for so long that I don't have all my contacts saved digitally. In fact, I'm *so* old-fashioned I have some of my contacts in a rolodex, which, unfortunately, just broke. That's actually why I'm here, it's time to write a new chapter in my life! By writing code!

The goal is to take contacts found on a "rolodex" and put them in a computer.

1. Each contact has a single-space separated First Name (or initial), optional Middle Name (or initial), and Last Name (or initial)
2. A contact can have a nickname surrounded with quotes " "
3. A contact can have a telephone number
    * It will always have 10 digits
    * Separators are not necessary but, if there is a separator, the format will always be: `3 digits <separator> 3 digits <separator> 4 digits`
4. A contact can have an email address
5. A contact can have a birthdate
    * The separator is not constant, but there will be one between day, month and year
    * The day or day and month may not be present

### Installation
Installation is simple! 
1. Make sure you have Java 8 (or higher) installed [click here to learn how to do that](https://www.java.com/en/download/help/version_manual.xml) 
2. You will also need [Maven 3.2+](https://maven.apache.org/download.cgi)
3. clone this repository with `git clone https://github.com/GLeaden/digital-rolodex.git`
4. navigate to the newly downloaded project and run this command `./mvnw spring-boot:run` from the main directory
5. The server is now running on `localhost:8080`


* There are two main ways to interact with the API.
  * `POST` to `/contactdump/` and
  * `GET`/`PUT`/`POST` to `/contacts/`
    * There are also `findByLastName`, `findByNickname` and a few other `/contact/search/` options.

### How to use
Once you have the server up and running, the first thing you should do is:
* Add contacts.
  * Take a look at the example contacts below and copy/paste them into your favorite API development tool (Postman, cURL) with content type as `text/plain` on the endpoint `localhost:8080/contactdump/`
  * These contacts have now been loaded into the JPA repository and can be accessed with `GET` `/contacts/`.
  * From here you can play around with different HTTP verbs and even update existing contacts with `PUT`

### Example Contacts
Below are all *valid* contact entries.
```
Samuel L. Clemens "Mark Twain" marktwain@steamboat.edu 207.478.3823 02.31.1835
Katherine Johnson katherine.johnson@dontemailnasa.gov 8884475594 08/26/1918
Augusta Ada King "The Countess of Lovelace" 1235556614 ada_lovelace@babbage.math 10/12/1815
John Oates "Oates" (719) 266-2837 john@hallandoates.com 1948
J. Jonah Jameson
```

### Purpose
This digital rolodex was created as part of a technical interview with an industry leading startup in the cloud governance sector. 

I challenged myself to use Spring Boot, a language used by said company but one I had never used before. I enjoyed the experience and would work with Spring Boot again. However if I started over knowing what I know now about Domain Driven Design I would have approached this project more strategically. Overall I hope this project and its commits effectively illustrate my thought process and my adaptability to learn new things.

If you have any questions feel free to reach out to me, my email is on my profile.