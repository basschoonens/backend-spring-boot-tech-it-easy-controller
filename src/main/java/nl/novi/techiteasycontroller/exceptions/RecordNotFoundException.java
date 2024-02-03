package nl.novi.techiteasycontroller.exceptions;


public class RecordNotFoundException extends RuntimeException {

    // Make a default exception
   public RecordNotFoundException() {
        super();
    }

    // Make an exception with a message

    public RecordNotFoundException(String message) {
        super(message);
    }

}
