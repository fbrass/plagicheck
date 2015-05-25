package framework;

import alignerPackage.Aligner;
import alignerPackage.IAligner;
import alignerPackage.IAlignmentMatrix;
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

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlignmentController{

    final private String orginal;
    final private String suspect;



    public AlignmentController(String orginal, String suspect) {
        this.orginal=orginal;
        this.suspect=suspect;
    }

    public void run() throws IOException{
        PushbackReader pushbackReaderOrginal = new PushbackReader(new FileReader(orginal));

        // hier zweites File
        PushbackReader pushbackReaderSuspect = new PushbackReader(new FileReader(suspect));



        ILexer lexer = new FilterLexer(new BufferedReader(pushbackReaderOrginal));
        ITokenSequence s1 = new TokenSequence();
        IToken tk1=lexer.getNextToken();
        System.out.println(tk1);
        s1.add(tk1);

        while ( tk1.getClassCode()!=IToken.EOF || tk1.getClassCode()!=IToken.ERROR){
            s1.add(tk1);
        }

        lexer.setPushbackReader(pushbackReaderSuspect);
        ITokenSequence s2 =new TokenSequence();
        IToken tk2 = lexer.getNextToken();
        while ( tk2.getClassCode()!=IToken.EOF || tk2.getClassCode()!=IToken.ERROR){
            s2.add(tk2);
        }

        IScoring scoring = new SimpleScoring();
        ISelector selector = new SimpleSelector(s1,s2);
        IRegion region = selector.getRegion();
        IAligner aligner = new Aligner(s1,s2,scoring,region);
        IAlignmentMatrix matrix = aligner.forward();
        IPresenter presenter = new Presenter(s1,s2,lexer,matrix);
        String presentation = presenter.threeColumnOutput();
        System.out.println(presentation);





        IToken token = lexer.getNextToken();
        while(token != null){
            Logger.getLogger(AlignmentController.class.getName()).log(Level.INFO, " Gelesen: "+token);
            token = lexer.getNextToken();
        }
        /* später: hier Lexer an zweiten Input binden; Leseschleife */




    }
}