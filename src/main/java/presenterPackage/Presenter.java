package presenterPackage;

import alignerPackage.Auxi;
import alignerPackage.Direction;
import alignerPackage.IAlignmentMatrix;
import lexerPackage.ILexer;
import scorePackage.IScoring;
import scorePackage.SimpleScoring;
import tokenPackage.ITokenSequence;

public class Presenter implements IPresenter {
    private ITokenSequence m;
    private ITokenSequence n;
    private ILexer lexer;
    private IAlignmentMatrix matrix;
    private IScoring scoring = new SimpleScoring();

    public Presenter(ITokenSequence m, ITokenSequence n, ILexer lexer,
                     IAlignmentMatrix matrix) {
        this.m = m;
        this.n = n;
        this.lexer = lexer;
        this.matrix = matrix;
    }

    @Override
    public String backwards() throws Exception{

        String tokenOutput1   = "";
        String tokenConsensus = "";
        String tokenOutput2   = "";

        int i = m.length();
        int j = n.length();

        while (i != 0) {
            while (j != 0) {

                String input1 = lexer.decode(m.getToken(i));
                String input2 = lexer.decode(n.getToken(j));

                if (matrix.get(i, j).getDirection() == Direction.DIAGONAL_MOVE) {

                    int maxLength = Math.max(input1.length(), input2.length());
                    int tk1Length = input1.length();
                    int tk2Length = input2.length();

                    if (tk1Length > tk2Length) {
                        input2 = Auxi.fill(input2, maxLength, ".");
                    } else {
                        input1 = Auxi.fill(input1, maxLength, ".");
                    }

                    tokenOutput1 = input1 + " " + tokenOutput1;
                    tokenOutput2 = input2 + " " + tokenOutput2;

                    // Nun kommt die Überprüfung des Score um zu wissen, was in den Consensus kommt
                    // score == 1
                    if (scoring.isPerfect(matrix.get(i, j).getValue()- matrix.get(i - 1, j - 1).getValue())) {

                        tokenConsensus = input1 + " " + tokenConsensus;

                        // score == 0
                    } else if (scoring.isMismatch(matrix.get(i, j).getValue()- matrix.get(i - 1, j - 1).getValue())){

                        //hier unterscheidung je nach dem wer länger ist
                        if(tk1Length > tk2Length){

                            tokenConsensus = Auxi.makeCrossString(input1) + " " + tokenConsensus;
                        }
                        else{

                            tokenConsensus = Auxi.makeCrossString(input2) + " " + tokenConsensus;
                        }

                        // 0.5 < score < 1
                    }else if (scoring.isNearMatch(matrix.get(i, j).getValue()- matrix.get(i - 1, j - 1).getValue())){
                        // hier könnte man nochwas überlegen z.B. Consensus mit * auffüllen
                    }

                    j--;
                    i--;
                }

                else if (matrix.get(i, j).getDirection() == Direction.VERTICAL_MOVE) {

                    tokenOutput1 = input1 + " " + tokenOutput1;
                    tokenConsensus = Auxi.makeGapString(input1) + " " + tokenConsensus;
                    tokenOutput2 =   Auxi.makeGapString(input1) + " " + tokenOutput2;

                    i--;

                } else if (matrix.get(i, j).getDirection() == Direction.HORIZONTAL_MOVE) {



                    tokenOutput1 	= Auxi.makeGapString(input2) + " " + tokenOutput1;
                    tokenConsensus 	= Auxi.makeGapString(input2) + " " + tokenConsensus;
                    tokenOutput2 	= input2 + " " + tokenOutput2;

                    j--;

                }





            }

        }
        System.out.println("Input 1  : "+tokenOutput1);
        System.out.println("Consensus: "+tokenConsensus);
        System.out.println("Input 2  : "+tokenOutput2);
        return tokenOutput1 + "\n"+ tokenConsensus + "\n"+ tokenOutput2 + "\n";
    }



    public String threeColumnLayout() throws Exception{

        int breadth=45;
        String input1 = "Input 1";
        String consensus = "Consensus";
        String input2 = "Input 2";
        int restInput1 = breadth - input1.length() - 1;
        int restConsensus = breadth - consensus.length() - 1;


        String[] texts = backwards().split("\n");
        String[] thirtyChars0 = splitStringEvery(texts[0], breadth);
        String[] thirtyChars1 = splitStringEvery(texts[1], breadth);
        String[] thirtyChars2 = splitStringEvery(texts[2], breadth);


//		System.out.println("Input 1  : "+texts[0]);
//		System.out.println("Consensus: "+texts[1]);
//		System.out.println("Input 2  : "+texts[2]);

        System.out
                .println("----------------------------------------------------------------------------------------------------------------------------------------------\n");

        System.out.print(input1 + generateWhitespace(restInput1) + consensus
                + generateWhitespace(restConsensus) + input2 + "\n");

        String out = "";
        for (int i = 0; i < thirtyChars0.length; i++) {
            out = out + thirtyChars0[i] + "|" + thirtyChars1[i] + "|" + thirtyChars2[i] + "\n";
        }
        return out;
    }

    private String[] splitStringEvery(String s, int interval) {
        int rest = s.length() % interval;
        for (int k = 0; k < interval - rest; k++) {
            s = s + " ";
        }
        int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
        String[] result = new String[arrayLength];
        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        }
        result[lastIndex] = s.substring(j);
        return result;
    }

    private String generateWhitespace(int restIsWhitespace) {
        String stringWhitespace = " ";
        for (int i = 0; i < restIsWhitespace; i++) {
            stringWhitespace = " " + stringWhitespace;
        }
        stringWhitespace = stringWhitespace + "|";
        return stringWhitespace;
    }

}
