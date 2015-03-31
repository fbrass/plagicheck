package lexerPackage;

import tokenPackage.IToken;

import java.io.IOException;

/**
 * Created by Felix on 30.03.2015.
 */
public class BaseLexer implements ILexer{
    @Override
    public IToken getNextToken() throws IOException {
        return null;
    }

    @Override
    public String decode(IToken tk) {
        return null;
    }
}
