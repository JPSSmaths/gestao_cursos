package br.com.gestao_cursos.exceptions;

public class CursoAlredyExistsException extends RuntimeException{
    public CursoAlredyExistsException(){
        super("Job alredy exists");
    }
}
