package selectorPackage;

/**
 *
 * Created by said on 25.05.2015.
 */
public class Region extends IRegion {
    private int lowerBound1;
    private int upperBound1;
    private int lowerBound2;
    private int upperBound2;

    public Region(int lowerBound1, int upperBound1, int lowerBound2, int upperbound2) {
        this.lowerBound1=lowerBound1;
        this.upperBound1=upperBound1;
        this.lowerBound2=lowerBound2;
        this.upperBound2=upperbound2;
    }
}
