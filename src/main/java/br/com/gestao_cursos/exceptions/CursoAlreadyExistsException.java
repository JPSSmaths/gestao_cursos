package br.com.gestao_cursos.exceptions;

public class CursoAlreadyExistsException extends RuntimeException{
    public CursoAlreadyExistsException(){
        super("Job alredy exists");
    }
}
