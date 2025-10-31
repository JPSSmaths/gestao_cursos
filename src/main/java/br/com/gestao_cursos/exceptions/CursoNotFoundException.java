package br.com.gestao_cursos.exceptions;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(){
        super("Course not found");
    }
}
