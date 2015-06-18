package lexerPackage;

import tokenPackage.IToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.Map;

/**
 * Created by said on 25.05.2015.
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
            token = this.getNextToken();
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

    @Override
    public void setPushbackReader(PushbackReader b) {
        this.baseLexer.setPushbackReader(b);
    }

    @Override
    public boolean isFinished(){
        return this.baseLexer.isFinished();
    }
}