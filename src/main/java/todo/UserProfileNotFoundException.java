package todo;

public class UserProfileNotFoundException extends RuntimeException {
    public UserProfileNotFoundException(long id) {
        super("Could not find User " + id);
    }
}
