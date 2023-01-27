package com.vattenfall.libraryv2.service;

import com.vattenfall.libraryv2.entity.Person;
import com.vattenfall.libraryv2.repository.PersonRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSavePerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setBirthDate(LocalDate.of(1980, 1, 1));
        person.setCity("New York");
        person.setPhoneNumber("555-555-5555");

        Person savedPerson = personService.addPerson(person);

        assertThat(savedPerson).isEqualTo(person);
        assertThat(personRepository.findPersonByPhoneNumber("555-555-5555").get()).isEqualTo(savedPerson);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPhoneNumberExists() {
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setBirthDate(LocalDate.of(1980, 1, 1));
        person1.setCity("New York");
        person1.setPhoneNumber("555-555-5555");
        personService.addPerson(person1);

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setBirthDate(LocalDate.of(1985, 1, 1));
        person2.setCity("New York");
        person2.setPhoneNumber("555-555-5555");

        assertThrows(IllegalArgumentException.class, () -> personService.addPerson(person2));
    }
}
