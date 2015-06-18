package alignerPackage;

public class Auxi {

	
	public static String fill(String tokenStringFromInput, int commonlength, String normalizationSign) {

		int diffLength =  commonlength-tokenStringFromInput.length();
		String s=tokenStringFromInput;

		for (int k = 0; k < diffLength; k++) {

			s = s + normalizationSign;

		}
		return s;
	
	}
	public static String makeCrossString(String token) {
		String crossString = "";
		for (int i = 0; i < token.length(); i++) {
			crossString = crossString + "+";
		}
		return crossString;
	}

	public static String makeGapString(String token) {
		String minusString = "";
		for (int i = 0; i < token.length(); i++) {
			minusString =minusString + "-";
		}
		return minusString;
	}
	
	
	
}
