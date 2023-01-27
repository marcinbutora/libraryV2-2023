package com.vattenfall.libraryv2.service;

import com.vattenfall.libraryv2.entity.Person;
import com.vattenfall.libraryv2.exception.PhoneNumberNotFoundException;
import com.vattenfall.libraryv2.exception.ResourceNotFoundException;
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

    public void deletePerson(long id) {
        Optional<Person> person = repository.findPersonById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
        } else {
            throw new ResourceNotFoundException("Person with id " + id + " not found");
        }
    }

    public void updatePerson(Long id, Person updatedPerson) {
        Person person = findPersonByID(id);
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setBirthDate(updatedPerson.getBirthDate());
        person.setCity(updatedPerson.getCity());
        person.setPhoneNumber(updatedPerson.getPhoneNumber());
        repository.save(person);
    }

    private Person findPersonByID(Long id) {
        return repository.findPersonById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
    }


    public void isPhoneNumberExists(Person person) {
        if (phoneNumberExists(person.getPhoneNumber())) {
            throw new PhoneNumberNotFoundException("Phone number already exists " + person.getPhoneNumber());
        }
    }

    public boolean phoneNumberExists(String phoneNumber) {
        Optional<Person> person = repository.findPersonByPhoneNumber(phoneNumber);
        return person.isPresent();
    }
}
