package database.project.carrental.model.exceptions;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String id ) {
        super(String.format("The vehicle with license plate %s is not found",id));
    }

}
