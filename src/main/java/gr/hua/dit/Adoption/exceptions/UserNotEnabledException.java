package gr.hua.dit.Adoption.exceptions;


public class UserNotEnabledException extends RuntimeException {
    public UserNotEnabledException(String message) {
        super(message);
    }
}
