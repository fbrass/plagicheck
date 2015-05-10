package tokenPackage;

/**
 * Created by Felix on 30.03.2015.
 */
public class Token implements IToken, Comparable {

    int classCode;
    int relativeCode;

    public Token(int classCode, int relativeCode) {
        this.classCode = classCode;
        this.relativeCode = relativeCode;
    }

    @Override
    public int getClassCode() {
        return classCode;
    }

    @Override
    public int getRelativeCode() {
        return relativeCode;
    }

    public String toString(){
        String tokenClass = "";
        if (getClassCode() == IToken.IDENTIFIER){
            tokenClass = "IDENTIFIER";
        } else if (getClassCode() == IToken.INTCONS){
            tokenClass = "INTCONS";
        } else if (getClassCode() == IToken.PMARK){
            tokenClass = "PMARK";
        } else if (getClassCode() == IToken.WS){
            tokenClass = "WS";
        } else if (getClassCode() == IToken.DATE){
            tokenClass = "DATE";
        }

        return "ClassCode: " + getClassCode() + " Tokenclass:" + tokenClass + " RelativeCode: " + getRelativeCode();
    }


    /**
     *
     * @param o iToken
     * @return 0 wenn gleich, -1 wenn relativeCode kleiner, 1 wenn relativeCode größer
     */
    @Override
    public int compareTo(Object o) {
        int result = -1;
        IToken iToken = (IToken) o;

        if(this.classCode == iToken.getClassCode() && this.relativeCode == iToken.getRelativeCode()){
            result = 0;
        } else if(iToken.getRelativeCode() < this.getRelativeCode()){
            result = -1;
        } else if(iToken.getRelativeCode() > this.getRelativeCode()){
            result = 1;
        }
        return result;
    }
}
