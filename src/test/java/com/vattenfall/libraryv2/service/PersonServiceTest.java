package com.vattenfall.libraryv2.service;

import com.vattenfall.libraryv2.entity.Person;
import com.vattenfall.libraryv2.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
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
        assertThat(person.getPhoneNumber()).isEqualTo(savedPerson.getPhoneNumber());
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

    @Test
    public void deletePersonTest() {
        Person person = new Person(1L, "John", "Smith", LocalDate.of(2000, 1, 1), "NY", "123-456-7890");
        Person personAdded = personService.addPerson(person);
        personService.deletePerson(personAdded.getId());
        boolean removedPerson = personService.phoneNumberExists(person.getPhoneNumber());
        assertThat(removedPerson).isFalse();
    }

    @Test
    public void updatePersonTest() {
        // Given
        Long id = 1L;
        Person person = new Person(1L, "John", "Doe", LocalDate.of(1990, 1, 1), "New York", "555-555-555");
        personService.addPerson(person);
        Person updatedPerson = new Person(1L, "Jane", "Doe", LocalDate.of(1995, 1, 1), "Los Angeles", "555-555-555");
        // When
        personService.updatePerson(id, updatedPerson);
        Person result = personRepository.findPersonById(id).get();
        // Then
        assertEquals(updatedPerson.getFirstName(), result.getFirstName());
        assertEquals(updatedPerson.getLastName(), result.getLastName());
        assertEquals(updatedPerson.getBirthDate(), result.getBirthDate());
        assertEquals(updatedPerson.getCity(), result.getCity());
        assertEquals(updatedPerson.getPhoneNumber(), result.getPhoneNumber());
    }
}
