package com.restjava.RestJava.controllers;

import com.restjava.RestJava.dto.v1.PersonDTO;
import com.restjava.RestJava.dto.v2.PersonDTOV2;
import com.restjava.RestJava.services.PersonService;
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

//@CrossOrigin
@RestController
@RequestMapping(value = "/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
    @Autowired
    private PersonService service;

    //LEGADO STUFF @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all people", description = "Finds all people",
    tags = {"People"},
    responses = {
        @ApiResponse(description = "Success", responseCode = "200",content = {
                @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
        }),
        @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)

    })
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a person", description = "Finds a person",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = PersonDTO.class))
                ),
                @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    //@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON, XML or YML representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public PersonDTO create(@RequestBody PersonDTO dto) {
        return service.create(dto);
    }

    /*@PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a person", description = "Finds a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 dto) {
        return service.createV2(dto);
    }*/


    //@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a person", description = "Updates a person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",content = @Content)
            })
    public PersonDTO update(@RequestBody PersonDTO dto) {
        return service.update(dto);
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a person", description = "Deletes a person",
            tags = {"People"},
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
