package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    //GET request method
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    //can get id from url id variable using @PathVariable
    @GetMapping(path = "{id}") //pass in the id into a uri
    public Person getPersonById(@PathVariable("id") UUID id){
        //return custom null or not found to the client
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }




}
