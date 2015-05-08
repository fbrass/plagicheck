package lexerPackage;

import DFAPackage.DFA;
import actionsPackage.*;
import mapPackage.IMapFactory;
import mapPackage.TreeMapFactory;
import tokenPackage.IToken;
import tokenPackage.Token;
import triePackage.ITrie;
import triePackage.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * BaseLexer is a simple implementation of the concept to give different character types different Tries
 * Created by Felix on 30.03.2015.
 */
public class BaseLexer implements ILexer{

    private IMapFactory mapFactory;
    private BufferedReader reader;
    private PushbackReader pushbackReader;
    private ITrie identifierTrie;
    private ITrie intconsTrie;
    private ITrie wsTrie;
    private ITrie pmarkTrie;
    private ITrie dateTrie;
    private IActionAtInsert identifierAction = new StringCoding(1);
    private IActionAtInsert intconsAction = new StringCoding(1);
    private IActionAtInsert wsAction      = new StringCoding(1);
    private IActionAtInsert pmarkAction = new StringCoding(1);
    private IActionAtInsert dateAction = new StringCoding(1);

    private Map<IToken, String> decodeMap = new HashMap<>();


    public BaseLexer(BufferedReader reader){
        pushbackReader = new PushbackReader(reader);
        this.mapFactory=new TreeMapFactory();
        this.identifierTrie= new Trie(mapFactory);
        this.intconsTrie=new Trie(mapFactory);
        this.wsTrie=new Trie(mapFactory);
        this.pmarkTrie=new Trie(mapFactory);
        this.dateTrie=new Trie(mapFactory);
    }



    @Override
    public IToken getNextToken() throws IOException {
        Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO, "--> next token");
        IToken result = null;
        DFA dfa = new DFA();
        int state = dfa.getInitial();

        String insertString = "";

        int lastState=-100;

        do //invariante: es gibt einen Tokenizer; tk != null;
        {

            int read = pushbackReader.read();
            if (read != -1) {

                char prChar = (char)(read);
                lastState = state;
                state = dfa.trans(state, prChar);



                if (state == dfa.FAILURE_STATE){
                    pushbackReader.unread(read);
                } else {
                    insertString = insertString + prChar;
                }

            } else {
                lastState = state;
                state = dfa.EOF_STATE;
            }
        }
        while (!dfa.isStop(state));

        try{
            Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"--- nest token: '"+insertString+"' " + dfa.stateToString(lastState));
        } catch (Exception e){
            System.out.println(e);
        }

        int classCode = -1;
        int relativeCode = -1;


        if(lastState == dfa.ID_STATE){
            classCode = IToken.IDENTIFIER;
            relativeCode = (Integer) identifierTrie.insert(insertString, identifierAction).getValue();
        } else if (lastState == dfa.INTCONS_STATE || lastState == dfa.FIRST_OF_DAY_STATE || lastState == dfa.SECOND_OF_DAY_STATE ){
            classCode = IToken.INTCONS;
            relativeCode = (Integer) intconsTrie.insert(insertString, intconsAction).getValue();
        } else if (lastState == dfa.DATE_STATE){
            classCode = IToken.DATE;
            relativeCode = (Integer) dateTrie.insert(insertString, dateAction).getValue();
        } else if (lastState == dfa.WS_STATE){
            classCode = IToken.WS;
            relativeCode = (Integer) wsTrie.insert(insertString, wsAction).getValue();
        } else if (lastState == dfa.PM_STATE){
            classCode = IToken.PMARK;
            relativeCode = (Integer) pmarkTrie.insert(insertString, pmarkAction).getValue();
        }

        if (relativeCode != -1){
            result = new Token(classCode, relativeCode);
            this.decodeMap.put(result, insertString);
        }

        Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"<-- result token: "+result);



        return result;
    }

    @Override
    public String decode(IToken tk) throws Exception {
        String result = "";
        for(IToken key : decodeMap.keySet()){
            if (key.compareTo(tk) == 0){
                result = decodeMap.get(key);
            }
        }
        if (result.equals("")){
            throw new Exception("keinen String zu Schlüssel gefunden");
        }
        return result;
    }

    public Map getDecodeMap(){
        return decodeMap;
    }
}
