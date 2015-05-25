package lexerPackage;

import tokenPackage.IToken;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.Map;

/**
 *
 * Created by Felix on 27.03.2015.
 */
public interface ILexer {
    IToken getNextToken() throws IOException;
    String decode(IToken tk) throws Exception;
    Map getDecodeMap();
    void setPushbackReader(PushbackReader b);
}
