package triePackage;

import actionsPackage.IActionAtInsert;

import java.util.Iterator;

/**
 * Created by Felix on 27.03.2015.
 */
public interface ITrieNode {
    ITrieReference recursiveInsert(Iterator k, IActionAtInsert a);
}
