package alignerPackage;

import scorePackage.IScoring;
import selectorPackage.IRegion;
import tokenPackage.ITokenSequence;

public class Aligner implements IAligner {

    private ITokenSequence m;
    private ITokenSequence n;
    private IScoring scoring;
    private IAlignmentMatrix A;

    public Aligner(ITokenSequence s1, ITokenSequence s2, IScoring scoring,
                   IRegion region) {

        this.m = s1;
        this.n = s2;
        this.scoring = scoring;

    }

    @Override
    public IAlignmentMatrix forward() {

        // m = Höhe , n = Breite

        IAlignmentContent c = new AlignmentContent(0.0);


        A = new SimpleAlignmentMatrix(m.length(), n.length(),c);

        // Initialisierung für das Feld 0,0
        A.set(0, 0, new AlignmentContent(0 * scoring.getGapScore(),Direction.NONE));

        // Initialisierung für Spalten j der ersten Zeile
        for (int j = 1; j <= n.length(); j++) {
            A.set(0, j, new AlignmentContent(-j * scoring.getGapScore(),Direction.NONE));
        }

        // Initialisiering für die Zeilen i der ersten Spalte
        for (int i = 1; i <= m.length(); i++) {
            A.set(i, 0, new AlignmentContent(-i * scoring.getGapScore(),Direction.NONE));
        }

        // Initialisierung mit minus unendlich von 1,1 bis k,k
        for (int i = 1; i <= m.length(); i++) {
            for (int j = 1; j <= n.length(); j++) {
                A.set(i, j, new AlignmentContent(Double.NEGATIVE_INFINITY,Direction.DIAGONAL_MOVE));

            }}
        System.out.println("Aligner.forward() hat die Initialisierung der Matrix abgeschlossen");
        //printMatrix();

        double maxValue = 0.0;
        for (int i = 1; i <= m.length(); i++) {

            for (int j = 1; j <= n.length(); j++){

                double vonAbove = A.get(i - 1, j).getValue()- scoring.getGapScore();
                double vonLeft = A.get(i, j - 1).getValue()- scoring.getGapScore();
                double vonDiagonal = A.get(i - 1, j - 1).getValue()+ scoring.getScore(m.getToken(i), n.getToken(j));

                maxValue=Math.max(vonLeft, vonAbove);
                maxValue=Math.max(maxValue,vonDiagonal);


                if (maxValue==vonLeft) {
                    A.get(i,j).setValue(vonLeft);
                    A.get(i, j).setDirection(Direction.HORIZONTAL_MOVE);
                } else if (maxValue==vonAbove) {
                    A.get(i,j).setValue(vonAbove);
                    A.get(i, j).setDirection(Direction.VERTICAL_MOVE);
                } else if (maxValue==vonDiagonal) {
                    A.get(i, j).setValue(vonDiagonal);
                    A.get(i, j).setDirection(Direction.DIAGONAL_MOVE);

                }



            }

        }
        System.out.println("Aligner.forward() hat die Matrix erfolgreich erstellt.\n");
        printMatrix();
        return A;
    }


    public void printMatrix() {

        String dString="";
        String s="";

        for(int i = 0; i <= m.length(); i++ ) {

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for(int j = 0; j <= n.length(); j++) {


                int dInt = A.get(i, j).getDirection().ordinal();

                switch (dInt){
                    case 0: {dString="_"; break;}
                    case 1:{ dString="!"; break;}
                    case 2:{ dString="\\"; break;}
                    case 3:{ dString=" "; break;}
                }
                double v=A.get(i, j).getValue();
                if (v==Double.NEGATIVE_INFINITY){
                    v=-9.9;
                }
                s = String.format("%-5s", v);
                s+=dString+ "|";
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

}
