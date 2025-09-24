package br.com.gestao_cursos.exceptions;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(){
        super("Job not found");
    }
}
