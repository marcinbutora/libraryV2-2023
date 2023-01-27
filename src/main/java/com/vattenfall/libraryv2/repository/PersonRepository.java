package com.vattenfall.libraryv2.repository;

import com.vattenfall.libraryv2.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findPersonById(Long id);

    Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findPersonByPhoneNumber(String phoneNumber);
}
