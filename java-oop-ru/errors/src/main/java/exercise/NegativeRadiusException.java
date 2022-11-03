package exercise;

// BEGIN
public class NegativeRadiusException extends Exception{
    private final String message;

    public NegativeRadiusException(String message) {
        this.message = message;
    }
}
// END
