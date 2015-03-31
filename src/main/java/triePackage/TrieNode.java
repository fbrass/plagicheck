package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.Iterator;

/**
 * Created by Felix on 27.03.2015.
 */
public class TrieNode implements ITrieNode {

    private IMapFactory mapFactory;
    private ITrieNode parent;
    private Object ingoingPartialKey;


    public TrieNode(IMapFactory mapFactory, ITrieNode parent, Object ingoingPartialKey){
        this.mapFactory = mapFactory;
        this.parent = parent;
        this.ingoingPartialKey = ingoingPartialKey;


    }
    @Override
    public ITrieReference recursiveInsert(Iterator k, IActionAtInsert a) {
        return null;
    }
}
