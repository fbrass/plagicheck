package selectorPackage;

import tokenPackage.ITokenSequence;

/**
 *
 * Created by said on 25.05.2015.
 */
public class SimpleSelector implements ISelector{
    private ITokenSequence s1;
    private ITokenSequence s2;

    public SimpleSelector(ITokenSequence s1, ITokenSequence s2) {
        this.s1=s1;
        this.s2=s2;
    }

    @Override
    public IRegion getRegion() {
        return new Region(0,this.s1.length()-1,0,this.s2.length()-1);
    }
}
