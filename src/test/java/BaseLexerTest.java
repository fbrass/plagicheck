import DFAPackage.DFA;
import DFAPackage.IDFA;
import framework.AlignmentController;
import lexerPackage.BaseLexer;
import lexerPackage.ILexer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tokenPackage.IToken;
import tokenPackage.Token;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Willi on 08.05.2015.
 */
public class BaseLexerTest {
    private ILexer lexer;
    private DFA dfa;


    @Before
    public void setUp() {

        String str = "Am 01.05.15 wissen wir,dass 1 und 1 gleich    2 ist.";

        // convert String into InputStream
        InputStream is = new ByteArrayInputStream(str.getBytes());

        // read it with BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        lexer = new BaseLexer(br);
        dfa = new DFA();



    }

    @After
    public void tearDown(){
        this.lexer = null;
        this.dfa = null;
    }


    @Test
    public void testBaseLexer() {
        try {



            List<IToken> tokenList = new ArrayList<>();
            IToken token = lexer.getNextToken();
            tokenList.add(token);

            while (token != null) {
                token = lexer.getNextToken();
                if(token != null) {
                    tokenList.add(token);
                }
            }

            for (int i = 0; i < tokenList.size(); i++) {
                IToken tokenFromList = tokenList.get(i);


                String tokenClass = "";


                if (tokenFromList.getClassCode() == IToken.IDENTIFIER){
                    tokenClass = "IDENTIFIER";
                } else if (tokenFromList.getClassCode() == IToken.INTCONS){
                    tokenClass = "INTCONS";
                } else if (tokenFromList.getClassCode() == IToken.PMARK){
                    tokenClass = "PMARK";
                } else if (tokenFromList.getClassCode() == IToken.WS){
                    tokenClass = "WS";
                } else if (tokenFromList.getClassCode() == IToken.DATE){
                    tokenClass = "DATE";
                }

                System.out.println("TokenClass: " + tokenClass + " RelativeCode: " + tokenFromList.getRelativeCode() + " String: '" + lexer.decode(tokenFromList) + "'");

            }

            /*Map<IToken, String> decodeMap = lexer.getDecodeMap();
            System.out.println(decodeMap.size());
            for(IToken key : decodeMap.keySet()){
                String decoded = decodeMap.get(key);
                System.out.println("Key: " + key + "String: '" + decoded+"'");
            }*/
        } catch(Exception e){
            System.out.println(e);
        }
    }


}
