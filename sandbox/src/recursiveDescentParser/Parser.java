package recursiveDescentParser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class Parser {
    public static final Set<String> TOKENS = ImmutableSet.of("==");

    public final PushbackReader reader;

    public Parser(String string) {
        this.reader = new PushbackReader(new StringReader(string));
    }

    public void parse() throws ParseError {
        expression();
    }

    /**
     * If the next character in input is one of the legal operators,
     * read it and return it.  Otherwise, throw a ParseError.
     */
    char getOperator() throws ParseError {
        this.skipBlanks();
        char op = this.peek(); // look ahead at the next char, without reading it
        if ( op == '+' || op == '-' || op == '*' || op == '/' ) {
            this.getAnyChar();  // read the operator, to remove it from the input
            return op;
        }
        else if (op == '\n')
            throw new ParseError("Missing operator at end of line.");
        else
            throw new ParseError("Missing operator.  Found \"" +
                    op + "\" instead of +, -, *, or /.");
    } // end getOperator()

    private double numberValue() {
        String numberString = "";
        while (Character.isDigit(this.peek())) {
            numberString += this.getAnyChar();
        }
        int number = Integer.parseInt(numberString);
        return number;
    }

    private void expression() {
        if (this.getAnyChar() != '(') {
            throw new ParseError("Expected '('");
        }

        this.getOp();
        if (this.getAnyChar() == ' ') {
            throw new ParseError("Expected space after an op");
        }
        while (this.peek() != ')') {
            this.getArg();
        }

        this.getAnyChar(); // get the ')'
    }

    private String getOp() {
        return getWord();
    }

    private String getWord() {
        StringBuilder sb = new StringBuilder();
        while (this.peek() != ' ') {
            sb.append(this.getAnyChar());
        }
        return sb.toString();
    }

    private void getArg() {
        char nextChar = this.peek();
        if (nextChar == '(') {
            expression();
        } else if (nextChar == '\'') {
            this.getValue();
        } else {
            getField();
        }
    }

    private String getField() {
        return getWord();
        
    }

    private String getValue() {
        if (this.getAnyChar() != '\'') {
            throw new ParseError("Expected single-quote char");
        }
        StringBuilder sb = new StringBuilder(); 
        while (this.peek() != '\'') {
            sb.append(this.getAnyChar());
        }
        this.getAnyChar(); // Read ending single-quote char
        return sb.toString();
    }

    private char getAnyChar() {
        try {
            return (char) this.reader.read();
        } catch (IOException e) {
            throw new ParseError("Caught IOError", e);
        }
    }

    private char peek() {
        int consumedVal;
        try {
            consumedVal = this.reader.read();
            this.reader.unread(consumedVal);
        }
        catch (IOException e) {
            throw new ParseError("Caught IOError", e);
        }
        return (char) consumedVal;
    }

    private void skipBlanks() {
        while (Character.isWhitespace(this.peek())) {
            this.getAnyChar();
        }
    }
}
