package com.rolodex.digitalrolodex;

import com.rolodex.digitalrolodex.Contact;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
class ContactsController {
    @PostMapping("/contact")
    Contact newContacts(@RequestBody String newContact) {

        return newContact;
  }
}