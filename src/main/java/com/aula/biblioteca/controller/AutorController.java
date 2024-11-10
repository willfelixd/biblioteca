package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.AutorDto;
import com.aula.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/autores")
public class AutorController {

    private final AutorService autorService;

    @Operation(description = "Listar todos os autores com paginação da base de dados", summary = "Listar todos os autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de autores", content = @Content(mediaType = "application/json", schema=@Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AutorDto>> findAll(@Parameter(required = true) @PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(autorService.findAll(pageable));
    }

    @Operation(description = "Criar um novo autor na base de dados", summary = "Criar um novo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de autores", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> save(@RequestBody AutorDto autorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autorDto));
    }

    @Operation(description = "Atualizar o autor pelo id na base de dados", summary = "Atualizar o autor pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de autores", content = @Content(mediaType = "application/json"))
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> update(@PathVariable String id, @RequestBody AutorDto autorDto){
        return ResponseEntity.ok(autorService.update(id, autorDto));
    }

}
