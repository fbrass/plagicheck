package presenterPackage;

import alignerPackage.IAlignmentMatrix;
import lexerPackage.ILexer;
import tokenPackage.ITokenSequence;

/**
 *
 * Created by said on 25.05.2015.
 */
public class Presenter implements IPresenter {
    public Presenter(ITokenSequence s1, ITokenSequence s2, ILexer lexer, IAlignmentMatrix matrix) {
    }

    @Override
    public String threeColumnOutput() {
        return null;
    }

    @Override
    public String backward() {
        return null;
    }
}
