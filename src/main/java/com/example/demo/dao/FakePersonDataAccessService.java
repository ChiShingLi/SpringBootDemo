package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
}
