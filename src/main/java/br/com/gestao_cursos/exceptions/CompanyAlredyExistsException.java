package br.com.gestao_cursos.exceptions;

public class CompanyAlredyExistsException extends RuntimeException{
    public CompanyAlredyExistsException(){
        super("Company alredy exist");
    }
}