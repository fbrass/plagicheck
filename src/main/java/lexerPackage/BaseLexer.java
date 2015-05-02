package lexerPackage;

import mapPackage.IMapFactory;
import mapPackage.TreeMapFactory;
import tokenPackage.IToken;
import triePackage.ITrie;
import triePackage.Trie;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * BaseLexer is a simple implementation of the concept to give different character types different Tries
 * Created by Felix on 30.03.2015.
 */
public class BaseLexer implements ILexer{

    private IMapFactory mapFactory;
    private BufferedReader reader;
    private ITrie identifier;
    private ITrie intcons;
    private ITrie ws;
    private ITrie pmark;
    private ITrie date;

    public BaseLexer(BufferedReader reader){
        this.reader=reader;
        this.mapFactory=new TreeMapFactory();
        this.identifier= new Trie(mapFactory);
        this.intcons=new Trie(mapFactory);
        this.ws=new Trie(mapFactory);
        this.pmark=new Trie(mapFactory);
        this.date=new Trie(mapFactory);
    }



    @Override
    public IToken getNextToken() throws IOException {
        return null;
    }

    @Override
    public String decode(IToken tk) {
        return null;
    }
}
