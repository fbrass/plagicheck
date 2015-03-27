package triePackage;

import actionsPackage.IActionAtInsert;

import java.util.Iterator;

/**
 * Created by Felix on 27.03.2015.
 * Interface of the RetrievalTree classes
 */
public interface ITrie {
    ITrieReference insert(Iterator k, IActionAtInsert a);
    ITrieReference insert(String s, IActionAtInsert a);
}
