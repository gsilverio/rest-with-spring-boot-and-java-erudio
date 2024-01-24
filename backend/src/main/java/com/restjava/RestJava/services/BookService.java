package com.restjava.RestJava.services;

import com.restjava.RestJava.controllers.BookController;
import com.restjava.RestJava.dto.v1.BookDTO;
import com.restjava.RestJava.exceptions.RequiredObjectIsNullException;
import com.restjava.RestJava.exceptions.ResourceNotFoundException;
import com.restjava.RestJava.mapper.DozerMapper;
import com.restjava.RestJava.model.Book;
import com.restjava.RestJava.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;
    private Logger logger = Logger.getLogger(BookService.class.getName());

    public List<BookDTO> findAll(){
        logger.info("Finding all books");
        var books = DozerMapper.parseListObjects(repository.findAll(), BookDTO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    public BookDTO findById(Long id){
        logger.info("Finding one book");
        var entity =repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        BookDTO dto = DozerMapper.parseObject(entity,BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public BookDTO create(BookDTO book){

        if(book == null) throw new RequiredObjectIsNullException();


        logger.info("Creating one book");
        var entity = DozerMapper.parseObject(book, Book.class);
        var dto = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }
    public BookDTO update(BookDTO book){
        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("Att book");
        Book entity = repository.findById(book.getKey()).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        entity.setAuthor(book.getAuthor());
        entity.setLauchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }
    public void delete(Long id){
        logger.info("Deleting one book");
        Book entity = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records founds for this id"));
        repository.delete(entity);
    }

}
