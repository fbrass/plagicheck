
import lexerPackage.BaseLexer;
import lexerPackage.ILexer;
import lexerPackage.SimpleLexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tokenPackage.IToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Willi on 08.05.2015.
 */
public class BaseLexerTest {
    private ILexer lexer;


    @Before
    public void setUp() {

        String str = "Am 26.03.15 wissen wir,dass 1 und 1 gleich    2 ist.";

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
            Logger.getLogger(SimpleLexer.class.getName()).setLevel(Level.OFF);

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

            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

            BaseLexer bLexer = (BaseLexer) lexer;
            System.out.println("Tries:");

            System.out.println("Identifier");
            bLexer.getIdentifierTrie().toString();

            System.out.println("Intcons:");
            bLexer.getIntconsTrie().toString();

            System.out.println("Dates:");
            bLexer.getDateTrie().toString();

            System.out.println("PMarks");
            bLexer.getPmarkTrie().toString();

            System.out.println("Whitespaces:");
            bLexer.getWsTrie().toString();

            System.out.println("Default:");
            bLexer.getDefaultTrie();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
