package tokenPackage;

/**
 *
 * Created by Felix on 27.03.2015.
 */
public interface IToken {
    int IDENTIFIER = 1;
    int INTCONS = 2;
    int DATE = 3;
    int PMARK = 4;
    int WS = 5;
    int EOF = -1;
    int ERROR = 6;

    int getClassCode();

    int getRelativeCode();
}
