package presenterPackage;

import alignerPackage.Direction;
import alignerPackage.IAlignmentMatrix;
import lexerPackage.ILexer;
import scorePackage.IScoring;
import scorePackage.SimpleScoring;
import tokenPackage.ITokenSequence;

public class Presenter2 implements IPresenter {
	private ITokenSequence m;
	private ITokenSequence n;
	private ILexer lexer;
	private IAlignmentMatrix matrix;
	private IScoring scoring = new SimpleScoring();

	public Presenter2(ITokenSequence m, ITokenSequence n, ILexer lexer,
					  IAlignmentMatrix matrix) {
		this.m = m;
		this.n = n;
		this.lexer = lexer;
		this.matrix = matrix;
	}

	@Override
	public String backwards() throws Exception{

		String tokenOutputOriginal = "";

		String tokenConsensus = "";

		String tokenOutputPlagiat = "";

		String whitespace = " ";
		String punkte = ".";

		String tokenOriginal = "";
		String tokenPlagiat = "";
		String tokenCons = "";

		int i = m.length();
		int j = n.length();

		while (i > 0) {

			while (j > 0) {

				if (matrix.get(i, j).getDirection() == Direction.HORIZONTAL_MOVE) {

					tokenOutputOriginal = generateLuecke(lexer.decode(n
							.getToken(j))) + whitespace + tokenOutputOriginal;

					tokenConsensus = generateLuecke(lexer.decode(n.getToken(j)))
							+ whitespace + tokenConsensus;

					tokenOutputPlagiat = lexer.decode(n.getToken(j))
							+ whitespace + tokenOutputPlagiat;

					j--;

				} else if (matrix.get(i, j).getDirection() == Direction.VERTICAL_MOVE) {

					tokenOutputOriginal = lexer.decode(m.getToken(i))
							+ whitespace + tokenOutputOriginal;

					tokenConsensus = generateLuecke(lexer.decode(m.getToken(i)))
							+ whitespace + tokenConsensus;

					tokenOutputPlagiat = generateLuecke(lexer.decode(m
							.getToken(i))) + whitespace + tokenOutputPlagiat;

					i--;

				} else if (matrix.get(i, j).getDirection() == Direction.DIAGONAL_MOVE) {

					// score == 1
					if (scoring.isPerfect(matrix.get(i, j).getValue()
							- matrix.get(i - 1, j - 1).getValue())) {

						tokenOutputOriginal = lexer.decode(n.getToken(j))
								+ whitespace + tokenOutputOriginal;

						tokenConsensus = lexer.decode(m.getToken(i))
								+ whitespace + tokenConsensus;

						tokenOutputPlagiat = lexer.decode(m.getToken(i))
								+ whitespace + tokenOutputPlagiat;

					} else {

						int maxLength = Math.max((lexer.decode(n.getToken(j))
								.length()), lexer.decode(m.getToken(i))
								.length());
						int nLength = lexer.decode(n.getToken(j)).length();
						int mLength = lexer.decode(m.getToken(i)).length();
						if (nLength > mLength) {

							int diffLength = nLength - mLength;

							for (int k = 0; k < diffLength; k++) {

								tokenPlagiat = tokenPlagiat + punkte;

							}
							tokenCons = generateCross(lexer.decode(n
									.getToken(j)));

						} else if (mLength > nLength) {

							int diffLength = mLength - nLength;

							for (int k = 0; k < diffLength; k++) {

								tokenOriginal = tokenOriginal + punkte;

							}

							tokenCons = generateCross(lexer.decode(m
									.getToken(i)));
						} else {
							tokenCons = generateCross(lexer.decode(m
									.getToken(i)));
						}
						// score = 0

						tokenOutputOriginal = lexer.decode(n.getToken(i))
								+ tokenOriginal + whitespace
								+ tokenOutputOriginal;
						tokenConsensus = tokenCons + whitespace
								+ tokenConsensus;
						tokenOutputPlagiat = lexer.decode(m.getToken(i))
								+ tokenPlagiat + whitespace
								+ tokenOutputPlagiat;
						tokenOriginal = "";
						tokenPlagiat = "";
						tokenCons = "";

						//

					}

					j--;
					i--;
				}
			}
		}

		return tokenOutputOriginal + "\n" + tokenConsensus + "\n"
				+ tokenOutputPlagiat + "\n";
	}

	private String generateLuecke(String token) {
		String luecke = "";
		for (int i = 0; i < token.length(); i++) {
			luecke = luecke + "-";
		}
		return luecke;
	}

	private String generateCross(String token) {
		String crossString = "";
		for (int i = 0; i < token.length(); i++) {
			crossString = crossString + "+";
		}
		return crossString;
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
		} // Add the last bit
		result[lastIndex] = s.substring(j);
		return result;
	}
	
	public String threeColumnLayout() throws Exception{


		String input1 = "Input 1";
		String consensus = "Consensus";
		String input2 = "Input 2";
		int restInput1 = 30 - input1.length() - 1;
		int restConsensus = 30 - consensus.length() - 1;
		

		String[] texts = backwards().split("\n");
		String[] thirtyChars0 = splitStringEvery(texts[0], 30);
		String[] thirtyChars1 = splitStringEvery(texts[1], 30);
		String[] thirtyChars2 = splitStringEvery(texts[2], 30);
		
		
		System.out.println("Input 1  : "+texts[0]);
		System.out.println("Consensus: "+texts[1]);
		System.out.println("Input 2  : "+texts[2]);
		
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

	private String generateWhitespace(int restIsWhitespace) {
		String stringWhitespace = " ";
		for (int i = 0; i < restIsWhitespace; i++) {
			stringWhitespace = " " + stringWhitespace;
		}
		stringWhitespace = stringWhitespace + "|";
		return stringWhitespace;
	}

}

// public String threeColumnLayout(){
// String path = backwards();
// System.out.println(path);
// String[] in = path.split(Pattern.quote("\n"));
//
// try {
// BufferedReader[] reader = new BufferedReader[4];
// ByteArrayInputStream[] stream =new ByteArrayInputStream[4];
// for (int i = 0; i < in.length; i++) {
// stream[i]= new ByteArrayInputStream(in[i].getBytes());
// reader[i] = new BufferedReader(new InputStreamReader(stream[i]));
// }
//
// int readerLength = in[0].length() + in[1].length() + in[2].length();
// int column = 1;
// int columnLenth = 19;
// int counter = 1;
// int anzahl = in[0].length()/columnLenth;//System.out.println(anzahl);
// int rest = in[0].length()%columnLenth;
// if ( rest > 0 ) anzahl = anzahl + 1;
// readerLength = anzahl*columnLenth*3;
//
// path = "";
// for ( int i=0; i < readerLength; i++ ) {
// if ( column==1 ) {
// if (reader[0].ready() )
// path = path + (char) reader[0].read();
// else {
// path = path + " ";
// }
//
// } else if ( column==2 ) {
// if ( reader[1].ready() )
// path = path + (char) reader[1].read();
// else {
// path = path + " ";
// }
// } else if ( column==3 ) {
// if ( reader[2].ready() )
// path = path + (char) reader[2].read();
// else {
// path = path + " ";
// }
// }
//
// counter++;
// if ( counter==columnLenth ) {
// counter = 1;
// column++;
// path = path + " | ";
// if (column==4) {
// column = 1;
// path = path + "\n";
// }
// }
// }
//
// } catch (Exception ex) {
// ex.printStackTrace();
// }
// return path;
// }

