package database.project.carrental.model.exceptions;

public class RentingNotFoundException extends RuntimeException{
    public RentingNotFoundException(Long id ) {
        super(String.format("The renting with id %d is not found",id));
    }
}
