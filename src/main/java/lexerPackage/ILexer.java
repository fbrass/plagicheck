package lexerPackage;

import tokenPackage.IToken;

import java.io.IOException;

/**
 * Created by Felix on 27.03.2015.
 */
public interface ILexer {
    IToken getNextToken() throws IOException;
    String decode(IToken tk);
}
