package br.com.gestao_cursos.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid passowrd.");
    }
}
