package database.project.carrental.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException() {
        super("Invalid argument.");
    }
}