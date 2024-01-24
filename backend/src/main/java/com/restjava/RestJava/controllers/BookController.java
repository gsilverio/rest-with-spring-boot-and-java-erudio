package com.restjava.RestJava.controllers;

import com.restjava.RestJava.dto.v1.BookDTO;
import com.restjava.RestJava.services.BookService;
import com.restjava.RestJava.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {
    @Autowired
    private BookService service;

    //LEGADO STUFF @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all book", description = "Finds all book",
    tags = {"Book"},
    responses = {
        @ApiResponse(description = "Success", responseCode = "200",content = {
                @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
        }),
        @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)

    })
    public List<BookDTO> findAll() {
        return service.findAll();
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a book", description = "Finds a book",
            tags = {"Book"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = BookDTO.class))
                ),
                @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookDTO findById(@PathVariable(value = "id") Long id) {
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
    @Operation(summary = "Adds a new Book", description = "Adds a new Book by passing in a JSON, XML or YML representation of the book!",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookDTO create(@RequestBody BookDTO dto) {
        return service.create(dto);
    }

    /*@PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a book", description = "Finds a book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookDTOV2 createV2(@RequestBody BookDTOV2 dto) {
        return service.createV2(dto);
    }*/


    //@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a book", description = "Updates a book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public BookDTO update(@RequestBody BookDTO dto) {
        return service.update(dto);
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a book", description = "Deletes a book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
