package com.rolodex.digitalrolodex;

import java.util.List;

import com.rolodex.digitalrolodex.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long>{
    List<Contact> findBylName(@Param("name") String lName);
}