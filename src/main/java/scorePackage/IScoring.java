package scorePackage;

import tokenPackage.IToken;

public interface IScoring {
    double getScore(IToken tk1, IToken tk2);
    double getGapScore();

    boolean isPerfect(double d);
    boolean isMismatch(double d);
    boolean isNearMatch(double d);

}
