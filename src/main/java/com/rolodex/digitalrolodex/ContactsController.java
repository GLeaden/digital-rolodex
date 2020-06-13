package com.rolodex.digitalrolodex;

import java.util.ArrayList;
import java.util.List;

import com.rolodex.digitalrolodex.Contact;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
class ContactsController {
    @PostMapping("/contact")
    List<Contact> newContacts(@RequestBody String newContact) {
        List<Contact> contacts = new ArrayList<Contact>();
        String[] contactList = newContact.split("\\R");
        for (String contact: contactList){
            ContactParser parser = new ContactParser();
            contacts.add(parser.parseContact(contact));
        }
        return contacts;
  }
}