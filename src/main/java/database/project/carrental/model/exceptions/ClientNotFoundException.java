package database.project.carrental.model.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String id ) {
        super(String.format("The client with id %s is not found",id));
    }
}
