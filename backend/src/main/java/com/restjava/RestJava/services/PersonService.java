package com.restjava.RestJava.services;

import com.restjava.RestJava.dto.v1.PersonDTO;
import com.restjava.RestJava.dto.v2.PersonDTOV2;
import com.restjava.RestJava.exceptions.ResourceNotFoundException;
import com.restjava.RestJava.mapper.DozerMapper;
import com.restjava.RestJava.mapper.custom.PersonMapper;
import com.restjava.RestJava.model.Person;
import com.restjava.RestJava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonMapper mapper;
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonDTO> findAll(){
        logger.info("Finding all people");
        return DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one person");
        var entity =repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));

        return DozerMapper.parseObject(entity,PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var dto = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }
    public PersonDTO update(PersonDTO person){
        logger.info("Att person");
        Person entity = repository.findById(person.getId()).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }
    public void delete(Long id){
        logger.info("Deleting one person");
        Person entity = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        repository.delete(entity);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating one person with V2");
        var entity = mapper.convertDtoToEntity(person);
        var dto = mapper.convertEntityToDto(repository.save(entity));
        return dto;
    }
}
