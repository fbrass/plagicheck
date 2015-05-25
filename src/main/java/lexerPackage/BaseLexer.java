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
import java.util.TreeMap;
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

    private Map<IToken, String> decodeMap = new TreeMap<>();


    public BaseLexer(BufferedReader reader){
        pushbackReader = new PushbackReader(reader, 8);
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
        IToken result;
        int state = DFA.getInitial();
        String insertString = "";
        int lastState;



        do{
            int read = pushbackReader.read();
            if (read != -1) {
                char prChar = (char)(read);
                lastState = state;
                state = DFA.trans(state, prChar);

                if (state == DFA.FAILURE_STATE){
                    if (lastState > DFA.SECOND_OF_DAY_STATE
                            && lastState < DFA.DATE_STATE){

                        state=DFA.INTCONS_STATE;
                        handleWrongDate(read, insertString);
                        insertString="";
                    } else {
                        pushbackReader.unread(read);
                    }
                } else {
                    insertString = insertString + prChar;
                }

            } else {
                lastState = state;

                if (lastState > DFA.SECOND_OF_DAY_STATE
                        && lastState < DFA.DATE_STATE) {
                    state=DFA.INTCONS_STATE;
                    handleWrongDate(read, insertString);
                    insertString="";
                } else {
                    state = DFA.EOF_STATE;
                }
            }
        }
        while (!DFA.isStop(state));

        try{
            Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"--- nest token: '"+insertString+"' " + DFA.stateToString(lastState));
        } catch (Exception e){
            Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO, e.toString());
        }

        int classCode = -1;
        int relativeCode = -1;

        if(lastState == DFA.ID_STATE){
            classCode = IToken.IDENTIFIER;
            relativeCode = (Integer) identifierTrie.insert(insertString, identifierAction).getValue();
        } else if (lastState == DFA.INTCONS_STATE || lastState == DFA.FIRST_OF_DAY_STATE || lastState == DFA.SECOND_OF_DAY_STATE ){
            classCode = IToken.INTCONS;
            relativeCode = (Integer) intconsTrie.insert(insertString, intconsAction).getValue();
        } else if (lastState == DFA.WS_STATE){
            classCode = IToken.WS;
            relativeCode = (Integer) wsTrie.insert(insertString, wsAction).getValue();
        } else if (lastState == DFA.PM_STATE){
            classCode = IToken.PMARK;
            relativeCode = (Integer) pmarkTrie.insert(insertString, pmarkAction).getValue();
        }else if (lastState == DFA.DATE_STATE){
            classCode = IToken.DATE;
            relativeCode = (Integer) dateTrie.insert(insertString, dateAction).getValue();
        } else if(state == DFA.EOF_STATE){
            classCode = IToken.EOF;

        }

        result=new Token(classCode, relativeCode);
        if (relativeCode != -1 ){
            this.decodeMap.put(result, insertString);
        }

        Logger.getLogger(SimpleLexer.class.getName()).log(Level.INFO,"<-- result token: "+result);

        return result;
    }

    @Override
    public String decode(IToken tk) throws Exception {
        String result = "";
        for(IToken key : decodeMap.keySet()){
            Token keyT = (Token) key;
            if (keyT.compareTo(tk) == 0){
                result = decodeMap.get(key);
            }
        }
        if (result.equals("")){
            throw new Exception("keinen String zu Schlüssel gefunden");
        }
        return result;
    }

    private void handleWrongDate(int read, String insertString) throws IOException {


        char[] insertCharset = new char[insertString.length()];
        for(int i = 0; i<insertString.length(); i++){
            insertCharset[i] = insertString.charAt(i);
        }

        pushbackReader.unread(read);
        pushbackReader.unread(insertCharset);
    }

    public Map getDecodeMap(){
        return decodeMap;
    }

    @Override
    public void setPushbackReader(PushbackReader b) {
        this.reader= new BufferedReader(b);
    }

    public ITrie getIdentifierTrie() {
        return identifierTrie;
    }

    public ITrie getIntconsTrie() {
        return intconsTrie;
    }

    public ITrie getWsTrie() {
        return wsTrie;
    }

    public ITrie getPmarkTrie() {
        return pmarkTrie;
    }

    public ITrie getDateTrie() {
        return dateTrie;
    }



}
