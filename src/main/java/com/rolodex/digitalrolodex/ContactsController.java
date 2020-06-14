package com.rolodex.digitalrolodex;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rolodex.digitalrolodex.Contact;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
class ContactsController {
    @Autowired
    ContactRepository contactRepo;

    @PostMapping("/contactdump/")
    List<Contact> newContacts(@RequestBody String newContact) {
        List<Contact> contacts = new ArrayList<Contact>();

        // handle random empty lines in the input
        newContact = newContact.replaceAll("(?m)^\\s", "");
        // seperates by newline (contact)
        String[] contactList = newContact.split("\\R");
        for (String contact: contactList){
            ContactParser parser = new ContactParser();
            Contact parsedContact = parser.parseContact(contact);
            contacts.add(parsedContact);
            contactRepo.save(parsedContact);
        }
        return contacts;
  }
}