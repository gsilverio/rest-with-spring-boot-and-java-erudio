package com.restjava.RestJava.mapper.custom;

import com.restjava.RestJava.dto.v2.PersonDTOV2;
import com.restjava.RestJava.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonDTOV2 convertEntityToDto(Person person)
    {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setBirthDay(new Date());
        dto.setGender(person.getGender());
        return dto;
    }
    public Person convertDtoToEntity(PersonDTOV2 person)
    {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }
}
