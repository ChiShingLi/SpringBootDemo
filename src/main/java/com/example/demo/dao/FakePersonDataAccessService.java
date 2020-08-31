package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// annotation that indicates that the decorated class is a repository
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    //database
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        //add a person into the database
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {

        //if get the first person Id thats equals to id
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();

    }

    @Override
    public int deletePersonById(UUID id) {
        //get the person by id
        Optional<Person> personMaybe = selectPersonById(id);

        if (personMaybe.isEmpty()){
            return 0;
        }
        //if not empty, remove the person by id;
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        //select the person by Id, and 'set' the person to something else
        return selectPersonById(id).map(person -> {
            int indexOfPersonToUpdate = DB.indexOf(person);
            if(indexOfPersonToUpdate >= 0){
                //list.set() java stream, update with new Person() object
                DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                return 1;
            } else {
                return 0;
            }
        }).orElse(0);
    }




}
