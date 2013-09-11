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
        double expressionValue = expressionValue();
        System.out.println(expressionValue);
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
    
    /**
     * Read an expression from the current line of input and return its value.
     * @throws ParseError if the input contains a syntax error
     */
    private double expressionValue() throws ParseError {
       this.skipBlanks();
       if ( Character.isDigit(this.peek()) ) {
              // The next item in input is a number, so the expression
              // must consist of just that number.  Read and return
              // the number.
          return this.numberValue();
       }
       else if ( this.peek() == '(' ) {
              // The expression must be of the form 
              //         "(" <expression> <operator> <expression> ")"
              // Read all these items, perform the operation, and
              // return the result.
          this.getAnyChar();  // Read the "("
          double leftVal = expressionValue();  // Read and evaluate first operand.
          char op = getOperator();             // Read the operator.
          double rightVal = expressionValue(); // Read and evaluate second operand.
          this.skipBlanks();
          if ( this.peek() != ')' ) {
                 // According to the rule, there must be a ")" here.
                 // Since it's missing, throw a ParseError.
             throw new ParseError("Missing right parenthesis.");
          }
          this.getAnyChar();  // Read the ")"
          switch (op) {   //  Apply the operator and return the result. 
          case '+':  return leftVal + rightVal;
          case '-':  return leftVal - rightVal;
          case '*':  return leftVal * rightVal;
          case '/':  return leftVal / rightVal;
          default:   return 0;  // Can't occur since op is one of the above.
                                // (But Java syntax requires a return value.)
          }
       }
       else {  // No other character can legally start an expression.
          throw new ParseError("Encountered unexpected character, \"" + 
                this.peek() + "\" in input.");
       }
    } // end expressionValue()
    
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
        //TODO
    }
}
