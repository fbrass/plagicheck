package scorePackage;

import tokenPackage.IToken;

/**
 *
 * Created by said on 25.05.2015.
 */
public class SimpleScoring implements IScoring {

    @Override
    public double getScore(IToken tk1, IToken tk2) {
        return (tk1.getClass()==tk2.getClass() && tk1.getRelativeCode()==tk2.getRelativeCode() ? 1.0:0);
    }


    @Override
    public double getGapScore() {
        return 1;
    }

    boolean isPerfect(double s){
        return false;
    }

    boolean isMismatch(double s){
        return false;
    }

    boolean isNearMatch(double s){
        return false;
    }


}
