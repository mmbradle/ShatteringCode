package recursiveDescentParser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ParserTest {

    @Test
    public void test() throws Exception {
        String testString = "(((34-17)*8)+(2*7))";
        Parser parser = new Parser(testString);
        parser.parse();
        
    }

}
