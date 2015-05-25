package scorePackage;

import tokenPackage.IToken;

/**
 *
 * Created by said on 25.05.2015.
 */
public interface IScoring {
    double getScore(IToken tk1, IToken tk2);
    double getGapScore();
}
