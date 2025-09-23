package br.com.gestao_cursos.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company not found");
    }
}
