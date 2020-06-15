package com.rolodex.digitalrolodex;

import java.util.List;

import com.rolodex.digitalrolodex.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
PagingAndSortingRepository extends CrudRepository.
Here we provide some ways to use the GET verb and access the database.
*/
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>{
    List<Contact> findBylName(@Param("name") String lName);
    List<Contact> findByNickname(String nickname);
    List<Contact> findByPhoneNumber(String phoneNumber);
}