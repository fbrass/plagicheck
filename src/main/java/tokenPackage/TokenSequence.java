package tokenPackage;

import tokenPackage.ITokenSequence;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * Created by said on 25.05.2015.
 */
public class TokenSequence implements ITokenSequence {
    private ArrayList<IToken> tokenArrayList= new ArrayList<>();

    @Override
    public void add(IToken tk) {;
        this.tokenArrayList.add(tk);
    }

    @Override
    public IToken getToken(int i) {
        return this.tokenArrayList.get(i);
    }

    @Override
    public int length() {
        return this.tokenArrayList.size()-1;
    }
}
