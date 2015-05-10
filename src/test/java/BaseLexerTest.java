
import lexerPackage.BaseLexer;
import lexerPackage.ILexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tokenPackage.IToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Willi on 08.05.2015.
 */
public class BaseLexerTest {
    private ILexer lexer;


    @Before
    public void setUp() {

        String str = "Am 26.03.1 wissen wir,dass 1 und 1 gleich    2 ist.";

        // convert String into InputStream
        InputStream is = new ByteArrayInputStream(str.getBytes());

        // read it with BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        lexer = new BaseLexer(br);
    }

    @After
    public void tearDown(){
        this.lexer = null;
    }

    @Test
    public void testBaseLexer() {
        try {
            List<IToken> tokenList = new ArrayList<>();
            IToken token = lexer.getNextToken();
            System.out.println(token.toString() + " '" + lexer.decode(token) + "'");
            tokenList.add(token);

            while (token != null) {
                token = lexer.getNextToken();
                if(token != null) {
                    System.out.println(token.toString() + " '" + lexer.decode(token) + "'");
                    tokenList.add(token);
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
