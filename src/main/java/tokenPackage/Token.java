package tokenPackage;

/**
 * Created by Felix on 30.03.2015.
 */
public class Token implements IToken {

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
        return "ClassCode: " + getClassCode() + " RelativeCode: " + getRelativeCode();
    }

    @Override
    public int compareTo(IToken iToken){
        int result = -1;
        if(this.classCode == iToken.getClassCode() && this.relativeCode == iToken.getRelativeCode()){
            result = 0;
        }
        return result;
    }
}
