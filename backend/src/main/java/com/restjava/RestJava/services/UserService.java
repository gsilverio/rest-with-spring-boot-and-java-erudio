package com.restjava.RestJava.services;

import com.restjava.RestJava.controllers.PersonController;
import com.restjava.RestJava.dto.v1.PersonDTO;
import com.restjava.RestJava.dto.v2.PersonDTOV2;
import com.restjava.RestJava.exceptions.RequiredObjectIsNullException;
import com.restjava.RestJava.exceptions.ResourceNotFoundException;
import com.restjava.RestJava.mapper.DozerMapper;
import com.restjava.RestJava.mapper.custom.PersonMapper;
import com.restjava.RestJava.model.Person;
import com.restjava.RestJava.repositories.PersonRepository;
import com.restjava.RestJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name" + username +"!");
        var user = repository.findByUsername(username);
        if(user!=null)
        {
            return user;
        } else {
            throw new UsernameNotFoundException("Username" + username + "not found!");
        }
    }
}
