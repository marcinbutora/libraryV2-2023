package com.vattenfall.libraryv2.service;

import com.vattenfall.libraryv2.entity.Person;
import com.vattenfall.libraryv2.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person addPerson(Person person) {
        isPhoneNumberExists(person);
        return repository.save(person);
    }

    private void isPhoneNumberExists(Person person) {
        if (phoneNumberExists(person.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
    }

    private boolean phoneNumberExists(String phoneNumber) {
        Optional<Person> person = repository.findPersonByPhoneNumber(phoneNumber);
        return person.isPresent();
    }
}
