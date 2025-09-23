package br.com.gestao_cursos.exceptions;

public class CompanyAlredyExistsException extends RuntimeException{
    public CompanyAlredyExistsException(String usuario_found){
        super("Company alredy exist");
    }
}