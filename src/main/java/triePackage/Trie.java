package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.Iterator;

/**
 * Created by Felix on 27.03.2015.
 */
public class Trie implements ITrie {
    public Trie(IMapFactory mapFactory) {
    }

    @Override
    public ITrieReference insert(Iterator k, IActionAtInsert a) {
        return null;
    }

    @Override
    public ITrieReference insert(String s, IActionAtInsert a) {
        return null;
    }
}
