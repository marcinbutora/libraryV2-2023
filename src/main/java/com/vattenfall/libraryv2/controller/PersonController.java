package com.vattenfall.libraryv2.controller;

import com.vattenfall.libraryv2.entity.Person;
import com.vattenfall.libraryv2.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAllPeople();
    }
    
    @PostMapping
    public Person savePerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

}
