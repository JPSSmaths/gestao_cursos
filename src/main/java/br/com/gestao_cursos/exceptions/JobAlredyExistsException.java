package br.com.gestao_cursos.exceptions;

public class JobAlredyExistsException extends RuntimeException{
    public JobAlredyExistsException(){
        super("Job alredy exists");
    }
}
