package DFAPackage;

import actionsPackage.*;
import mapPackage.IMapFactory;
import triePackage.Trie;

import java.util.Set;

/**
 * Created by Felix on 17.04.2015.
 */
public class DFA implements IDFA {
    private Trie IDENTIFIER;
    private Trie WS;
    private Trie DATE;
    private Trie PMARK;
    private Trie INTCONS;


    public DFA(IMapFactory mapFactory){
        this.IDENTIFIER = new Trie(mapFactory);
        this.WS = new Trie(mapFactory);
        this.DATE = new Trie(mapFactory);
        this.PMARK = new Trie(mapFactory);
        this.INTCONS = new Trie(mapFactory);
    }


    @Override
    public int getInitial() {
        //TODO: muss unterschieden werde wegen Datumseingabe
        return 0;
    }

    @Override
    public boolean isFinal(int state) {
        return false;
    }

    @Override
    public int trans(int state, Object symbol) {
        return 0;
    }

    @Override
    public Set<Object> getTokenClasses(int s) {
        return null;
    }

    @Override
    public boolean isStop(int state) {
        return false;
    }

    @Override
    public String stateToString(int state) {
        return null;
    }
}
