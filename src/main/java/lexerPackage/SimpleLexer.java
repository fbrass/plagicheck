package lexerPackage;

import actionsPackage.IActionAtInsert;
import actionsPackage.StringCoding;
import mapPackage.IMapFactory;
import mapPackage.TreeMapFactory;
import tokenPackage.IToken;
import triePackage.ITrie;
import tokenPackage.Token;
import triePackage.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Felix on 27.03.2015.
 */
public class SimpleLexer implements ILexer {

    final private BufferedReader reader;
    final private IMapFactory mapFactory = new TreeMapFactory();
    final private IActionAtInsert action = new StringCoding(4711);
    final private ITrie trie;
    private String line;
    private StringTokenizer tk = null;

    public SimpleLexer(BufferedReader reader) throws IOException {
        this.reader = reader;
        line = reader.readLine();
        if (line != null) tk = new StringTokenizer(line);
        this.trie = new Trie(mapFactory);
    }

    public IToken getNextToken() throws IOException {
        Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO, "--> next token");
        IToken result;
        boolean foundToken = false;
        boolean noMoreToken = false;
        do //invariante: es gibt einen Tokenizer; tk != null;
        {
            result = null;
            if (tk != null) {
                if (tk.hasMoreTokens()) {
                    String intermediate = tk.nextToken();
                    Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"--- nest token: "+intermediate);
                    //später: result
                    this.trie.insert(intermediate,action);
                    // trie. insert(intermediate, action);
                    // DIC
                    result = new Token(-1, -1); //ein dummy!!!!
                    foundToken = true;
                } else {
                    //neue Zeile lesen
                    tk = null;
                    line = reader.readLine();
                    if (line != null) {
                        tk = new StringTokenizer(line);
                    }
                }
            } else {
                noMoreToken = true;
            }
        }
        while (!foundToken && !noMoreToken);
        Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"<-- result token: "+result);
        return result;
    }



    public String decode(IToken tk) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("nochnichtimplementiert");
    }

    @Override
    public Map getDecodeMap() {
        return null;
    }

    public String toString(){
        return "\nResult Trie \n"+trie;
    }
}
