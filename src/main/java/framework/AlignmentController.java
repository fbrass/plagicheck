package framework;

import lexerPackage.BaseLexer;
import lexerPackage.ILexer;
import tokenPackage.IToken;
import lexerPackage.SimpleLexer;

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
        InputStream istreamOrginal = new FileInputStream(orginal);
        Reader readerOrginal= new InputStreamReader(istreamOrginal);
        BufferedReader inputOrginal = new BufferedReader(readerOrginal);

        // hier zweites File


        ILexer lexer = new BaseLexer(inputOrginal);
        IToken token = lexer.getNextToken();
        while(token != null){
            Logger.getLogger(AlignmentController.class.getName()).log(Level.INFO, " Gelesen: "+token);
            token = lexer.getNextToken();
        }
        /* später: hier Lexer an zweiten Input binden; Leseschleife */




    }
}