package com.locadora_filmes.controller;

import com.locadora_filmes.DTOs.request.filme.FilmeRequest;
import com.locadora_filmes.DTOs.request.filme.FilmeUpdateRequest;
import com.locadora_filmes.DTOs.response.filme.FilmeResponse;
import com.locadora_filmes.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> cadastrar(@Valid @RequestBody FilmeRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmeService.cadastrarFilme(request));
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listar(){
        return ResponseEntity.ok(filmeService.listarFilmes());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<FilmeResponse> buscarPorTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(filmeService.buscarPorTitulo(titulo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizar(@PathVariable Long id,@Valid @RequestBody FilmeUpdateRequest request){

        return ResponseEntity.ok(filmeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        filmeService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
