package lexerPackage;

import tokenPackage.IToken;
import tokenPackage.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Felix on 30.03.2015.
 */
public class FilterLexer implements ILexer {

    private BaseLexer baseLexer;

    public FilterLexer(BufferedReader reader){
        this.baseLexer = new BaseLexer(reader);
    }

    @Override
    public IToken getNextToken() throws IOException {
        IToken token = baseLexer.getNextToken();
        if (token.getClassCode() == IToken.WS){
            token = null;
        }
        return token;
    }

    @Override
    public String decode(IToken tk) {
        return null;
    }

    @Override
    public Map getDecodeMap() {
        return null;
    }
}
