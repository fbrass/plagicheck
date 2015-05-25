package alignerPackage;

import scorePackage.IScoring;
import selectorPackage.IRegion;
import tokenPackage.ITokenSequence;

/**
 *
 * Created by said on 25.05.2015.
 */
public class Aligner implements IAligner{
    public Aligner(ITokenSequence s1, ITokenSequence s2, IScoring scoring, IRegion region) {
    }

    @Override
    public IAlignmentMatrix forward() {
        return null;
    }
}
