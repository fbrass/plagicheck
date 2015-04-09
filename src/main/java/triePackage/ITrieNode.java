package triePackage;

import actionsPackage.IActionAtInsert;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Felix on 27.03.2015.
 */
public interface ITrieNode {
    ITrieReference recursiveInsert(Iterator k, IActionAtInsert a);
    Map getOutgoingEdgeMap();
    Object getIngoingPartialKey();
    int getValue();
}
