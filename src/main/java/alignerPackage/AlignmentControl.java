package alignerPackage;

import lexerPackage.FilterLexer;
import lexerPackage.ILexer;
import presenterPackage.IPresenter;
import presenterPackage.Presenter;
import scorePackage.IScoring;
import scorePackage.SimpleScoring;
import selectorPackage.IRegion;
import selectorPackage.ISelector;
import selectorPackage.SimpleSelector;
import tokenPackage.IToken;
import tokenPackage.ITokenSequence;
import tokenPackage.TokenSequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;

public class AlignmentControl {

	public void run(File text2, File text1) {

		try {
			PushbackReader pbr1 = new PushbackReader(new BufferedReader(
					new FileReader(text1)));
			PushbackReader pbr2 = new PushbackReader(new BufferedReader(
					new FileReader(text2)));

			ILexer lexer = new FilterLexer(new BufferedReader(
					new FileReader(text1)));
			ITokenSequence s1 = new TokenSequence();
			ITokenSequence s2 = new TokenSequence();

			String s = "|%1$-15s|%2$-3s|%3$-3s|\n";
			System.out.format(s,"Token","CC","RC");
			while (!lexer.isFinished()) {
				IToken tk = lexer.getNextToken();
				if (tk != null) {
					
					System.out.format(s, lexer.decode(tk), tk.getClassCode(),tk.getRelativeCode());
					// while (tk.getClassCode() != TokenClassEnum.EOF) {
					s1.add(tk);
					// }
				}

			}
			System.out
					.println("\nDone with text1 ######################################################################\n");

			lexer.setPushbackReader(pbr2);
			System.out.format(s,"Token","CC","RC");
			while (!lexer.isFinished()) {
				IToken tk = lexer.getNextToken();
				if (tk != null) {
					System.out.format(s, lexer.decode(tk), tk.getClassCode(),tk.getRelativeCode());
					// while (tk.getClassCode() != TokenClassEnum.EOF) {
					s2.add(tk);
					// }
				}
			}
			System.out
					.println("\nDone with text2 ######################################################################\n");

			IScoring scoring = new SimpleScoring();
			ISelector selector = new SimpleSelector(s1, s2);

			IRegion region = selector.getRegion();
			IAligner aligner = new Aligner(s1, s2, scoring, region);
			IAlignmentMatrix matrix = aligner.forward();
			
			IPresenter presenter = new Presenter(s1, s2, lexer, matrix);
			String presentation = presenter.threeColumnLayout();
			System.out.println(presentation);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
