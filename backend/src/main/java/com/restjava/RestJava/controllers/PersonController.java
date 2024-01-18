package com.restjava.RestJava.controllers;

import com.restjava.RestJava.dto.v1.PersonDTO;
import com.restjava.RestJava.dto.v2.PersonDTOV2;
import com.restjava.RestJava.services.PersonService;
import com.restjava.RestJava.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {
    @Autowired
    private PersonService service;

    //LEGADO STUFF @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    //@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonDTO create(@RequestBody PersonDTO dto) {
        return service.create(dto);
    }

    @PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 dto) {
        return service.createV2(dto);
    }


    //@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public PersonDTO update(@RequestBody PersonDTO dto) {
        return service.update(dto);
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
