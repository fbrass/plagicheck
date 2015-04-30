package DFAPackage;

import actionsPackage.*;
import mapPackage.IMapFactory;
import triePackage.Trie;

import java.util.Set;

/**
 * Created by Felix on 17.04.2015.
 */
public class DFA implements IDFA {


    /*
    -1 EOF_STATE
    0 START_STATE
    1 ID_STATE (WÖRTER)
    3 WS_STATE (Leerzeichen)
    5 PM_STATE (Punkte und shit)
    7 INTCONS_STATE (zahlen udn shit)
    14 1_OF_DAY_STATE
    15 2_OF_DAY_STATE
    16 DAY_STATE
    17 1_OF_MONTH_STATE
    18 2_OF_MONTH_STATE
    19 MONTh_STATE
    20 1_YEAR_OF_STATE
    22 DATE_STATE
    127 FAILURE_STATE
     */

    public DFA(){

    }


    @Override
    public int getInitial() {
        //TODO: muss unterschieden werde wegen Datumseingabe
        return 0;
    }

    @Override
    public boolean isFinal(int state) {
        switch (state){
            case -1 : return true;
            case 1: return true;
            case 3: return true;
            case 5: return true;
            case 7: return true;
            case 14:return true;
            case 15:return true;
            case 22:return true;
        }
        return false;
    }

    @Override
    public int trans(int state, Object symbol) {
        if(Character.getType((Character) symbol)==Character.OTHER_PUNCTUATION && ( state==0 || state==3)){
            return 3;
        }
        if(Character.getType((Character) symbol)==Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC && (state==0 || state==1)){
            return 1;
        }
        if(Character.getType((Character) symbol)==Character.DIRECTIONALITY_RIGHT_TO_LEFT && (state==0 || state==1)){
            return 1;
        }
        return 127;
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
