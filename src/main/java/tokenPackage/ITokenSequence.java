package tokenPackage;

/**
 * Created by Felix on 30.03.2015.
 */
public interface ITokenSequence {
    void add(IToken tk);
    IToken getToken(int i);
    int length();
}
