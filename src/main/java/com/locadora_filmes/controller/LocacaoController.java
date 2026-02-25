package com.locadora_filmes.controller;

import com.locadora_filmes.DTOs.request.locacao.LocacaoRequest;
import com.locadora_filmes.DTOs.response.locacao.LocacaoResponse;
import com.locadora_filmes.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService){
        this.locacaoService = locacaoService;
    }

    @PostMapping
    public ResponseEntity<LocacaoResponse> realizarLocacao(@Valid @RequestBody LocacaoRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(locacaoService.realizarLocacao(request));
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<LocacaoResponse> devolver(@PathVariable Long id){
        return ResponseEntity.ok(locacaoService.devolverFilme(id));
    }

    @GetMapping
    public ResponseEntity<List<LocacaoResponse>> listar(){
        return ResponseEntity.ok(locacaoService.listarLocacoes());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<LocacaoResponse>> listarPorUsuario(@PathVariable Long idUsuario){

        return ResponseEntity.ok(locacaoService.listarLocacoesPorUsuario(idUsuario));
    }
}
