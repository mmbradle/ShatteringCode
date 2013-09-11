package recursiveDescentParser;

import java.io.IOException;

/**
 * An object of type ParseError represents a syntax error found in the user's
 * input.
 */
public class ParseError extends RuntimeException {
    ParseError(String message) {
        super(message);
    }

    public ParseError(String message, IOException e) {
        super(message, e);
    }
}