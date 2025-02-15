package skynet.exceptions;

/**
 * Custom Exception for missing arguments in commands.
 */
public class MissingArgumentException extends Exception {
    /**
     * Throws error for missing argument.
     *
     * @param m Error message string.
     */
    public MissingArgumentException(String m) {
        super(m);
    }
}
