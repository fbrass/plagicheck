package DFAPackage;

import java.util.Set;

public class DFA  {

    public static int EOF_STATE            = -1;
    public static int START_STATE          = 0;
    public static int ID_STATE             = 1;    //WÖRTER
    public static int WS_STATE             = 3;    //Leerzeichen
    public static int PM_STATE             = 5;    //Punkte und shit
    public static int INTCONS_STATE        = 7;    //zahlen udn shit
    public static int FIRST_OF_DAY_STATE   = 14;
    public static int SECOND_OF_DAY_STATE  = 15;
    public static int DAY_STATE            = 16;
    public static int FIRST_OF_MONTH_STATE = 17;
    public static int SECOND_OF_MONTH_STATE= 18;
    public static int MONTH_STATE          = 19;
    public static int FIRST_OF_YEAR_STATE  = 20;
    public static int DATE_STATE           = 22;
    public static int FAILURE_STATE        = 127;

    public static int getInitial() {
        return 0;
    }

    public static boolean isFinal(int state) {
        switch (state) {
            case -1:
                return true;
            case 1:
                return true;
            case 3:
                return true;
            case 5:
                return true;
            case 7:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            case 22:
                return true;
        }
        return false;
    }

    public static int trans(int state, Object symbol) {
        // EOF state implementieren
        if (Character.getType((Character) symbol) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC && (state == 0 || state == 1)) {
            return 1;
        } else if (Character.getType((Character) symbol) == Character.DIRECTIONALITY_RIGHT_TO_LEFT && (state == 0 || state == 1)) {
            return 1;
        } else if (Character.getType((Character) symbol) == Character.DIRECTIONALITY_WHITESPACE && (state == 0 || state == 3)) {
            return 3;
        } else if (Character.getType((Character) symbol) == Character.OTHER_PUNCTUATION && (state == 0 || state == 5)) {
            return 5;
        } else if (Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER && (state == 15 || state == 7)) {
            return 7;
        } else if (Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER && (state == 0)) {
            return 14;
        } else if (Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER && (state == 14)) {
            return 15;
        } else if (state == 15 && symbol.equals('.')) {
            return 16;
        } else if (state == 16 && Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER) {
            return 17;
        } else if (state == 17 && Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER) {
            return 18;
        } else if (state == 18 && symbol.equals('.')) {
            return 19;
        } else if (state == 19 && Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER) {
            return 20;
        } else if (state == 20 && Character.getType((Character) symbol) == Character.DECIMAL_DIGIT_NUMBER) {
            return 22;
        } else {
            return 127;
        }
    }

    public static Set<Object> getTokenClasses(int s) {
        return null;
    }

    public static boolean isStop(int state) {
        return state == -1 || state == 127;
    }

    public static String stateToString(int state) throws Exception {
        switch (state){
            case -1:return "EOF_STATE";
            case 0:return "START_STATE";
            case 1:return "ID_STATE";
            case 3:return "WS_STATE";
            case 5:return "PM_STATE";
            case 7:return "INTCONS_STATE";
            case 14:return "1_OF_DAY_STATE";
            case 15:return "2_OF_DAY_STATE";
            case 16:return "DAY_STATE";
            case 17:return "1_OF_MONTH_STATE";
            case 18:return "2_OF_MONTH_STATE";
            case 19:return "MONTH_STATE";
            case 20:return "1_YEAR_OF_STATE";
            case 22:return "DATE_STATE";
            case 127:return "FAILURE_STATE";
        }
        throw new Exception("STATE does not exist");
    }
}