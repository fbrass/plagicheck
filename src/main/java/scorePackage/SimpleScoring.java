package scorePackage;

import tokenPackage.IToken;

public class SimpleScoring implements IScoring {

    private double gapScore=0.5;
    @Override
    public double getScore(IToken tk1, IToken tk2) {
        double score=0;

        if(tk1.getClassCode()==tk2.getClassCode() && tk1.getRelativeCode()==tk2.getRelativeCode()){
            score= 1.0;
        }else{
            score= 0.0;
        }
        //System.out.println("Scoring.getScore: "+ score);
        return score;
    }

    @Override
    public double getGapScore() {

        return gapScore;
    }

    @Override
    public boolean isPerfect(double d) {

        return ((1.0-d)<0.1);
    }

    @Override
    public boolean isMismatch(double d) {
        return ((1.0-d)>0.9);
    }

    @Override
    public boolean isNearMatch(double d) {

        return (d>=0.4 && d<=1.0);
    }
}
