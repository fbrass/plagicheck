package tokenPackage;

/**
 * Created by Felix on 27.03.2015.
 */
public interface IToken {
    final static int IDENTIFIER=1;
    final static int INTCONS=2;
    final static int DATE=3;
    final static int PMARK=4;
    final static int WS = 5;

    int getClassCode();
    int getRelativeCode();
    int compareTo(IToken iToken);
}
