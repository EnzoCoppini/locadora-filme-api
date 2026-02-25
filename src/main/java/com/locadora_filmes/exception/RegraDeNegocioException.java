package com.locadora_filmes.exception;


public class RegraDeNegocioException extends RuntimeException{

    public RegraDeNegocioException(String mensagem){
        super(mensagem);
    }
}
