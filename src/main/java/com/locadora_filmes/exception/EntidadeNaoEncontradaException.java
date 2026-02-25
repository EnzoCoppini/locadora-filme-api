package com.locadora_filmes.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
