package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//api url
@RequestMapping("api/v1/person")

//specify this is a rest controller class
//expose some end points that client can consume
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //this is POST method
    @PostMapping
    //@RequestBody, tell that we are recieve JSON body in this method
    //turn JSON object into a person Object
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }
}
