package com.locadora_filmes.controller;

import com.locadora_filmes.DTOs.FilmeDTO;
import com.locadora_filmes.entity.Filme;
import com.locadora_filmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filme")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme){
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.cadastrarFilme(filme));
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilme(){
        return ResponseEntity.ok(filmeService.listarFilme());
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Filme> buscarPorTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(filmeService.buscarPorTitulo(titulo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        System.out.println("Chegou no controller");
        filmeService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id,@RequestBody FilmeDTO filmeDTO){

        Filme filmeAtualizado = filmeService.atualizar(id, filmeDTO);
        return ResponseEntity.ok(filmeAtualizado);
    }

}
