package br.com.gestao_cursos.modules.curso;

public class CursoFoundException extends RuntimeException{
    public CursoFoundException(){
        super("Curso já existe");
    }
}