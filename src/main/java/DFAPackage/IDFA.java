package DFAPackage;

import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.util.Set;

/**
 * Created by Felix on 17.04.2015.
 */
public interface IDFA {
    int getInitial();
    boolean isFinal(int state);
    int trans(int state, Object symbol);
    //Set because it is extensible
    Set<Object> getTokenClasses(int s);
    boolean isStop(int state);
    String stateToString(int state);
}
