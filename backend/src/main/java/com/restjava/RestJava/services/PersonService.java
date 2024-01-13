package com.restjava.RestJava.services;

import com.restjava.RestJava.exceptions.ResourceNotFoundException;
import com.restjava.RestJava.model.Person;
import com.restjava.RestJava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private PersonRepository repository;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all people");

        return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding one person");
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
    }

    public Person create(Person person){
        logger.info("Creating one person");
        return repository.save(person);
    }
    public Person update(Person person){
        logger.info("Att person");
        Person entity = repository.findById(person.getId()).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }
    public void delete(Long id){
        logger.info("Deleting one person");
        Person entity = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        repository.delete(entity);
    }
}
