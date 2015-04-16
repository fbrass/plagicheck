package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Felix on 27.03.2015.
 * TrieNode represents one node in den the datastructure
 */
public class TrieNode implements ITrieNode {

    private IMapFactory mapFactory;
    private Map outgoingEdgeMap;
    private ITrieNode parent;
    private Object ingoingPartialKey;
    private int value;


    public TrieNode(IMapFactory mapFactory, ITrieNode parent, Object ingoingPartialKey) {
        this.mapFactory = mapFactory;
        this.outgoingEdgeMap = mapFactory.create();
        this.parent = parent;
        this.ingoingPartialKey = ingoingPartialKey;
    }

    public TrieNode(IMapFactory mapFactory) {
        this.mapFactory = mapFactory;
        this.outgoingEdgeMap = mapFactory.create();
    }

    @Override
    public ITrieReference recursiveInsert(Iterator k, IActionAtInsert a) {
        if (k.hasNext()) {
            Comparable kComparable = (Comparable) k.next();
            for (Comparable mapComparable : (Iterable<Comparable>) this.outgoingEdgeMap.keySet()) {
                if (mapComparable.compareTo(kComparable) == 0) {
                    TrieNode tn = (TrieNode) this.outgoingEdgeMap.get(mapComparable);
                    return tn.recursiveInsert(k, a);
                }
            }
            TrieNode tn = new TrieNode(mapFactory, this, kComparable);
            outgoingEdgeMap.put(kComparable, tn);
            return tn.recursiveInsert(k, a);
        } else {
            if (outgoingEdgeMap.isEmpty()) {
                if (getValue() == 0) {
                    this.setValue((Integer) a.actionAtKeyNotFound());
                    return new TrieReference(false, this.getValue(), this);
                }
                return new TrieReference(true, this.getValue(), this);
            } else {
                if (getValue() == 0) {
                    this.setValue((Integer) a.actionAtKeyNotFound());
                }
                return new TrieReference(true, this.getValue(), this);
            }

        }
    }


    public Map getOutgoingEdgeMap() {
        return outgoingEdgeMap;
    }

    public Object getIngoingPartialKey() {
        return ingoingPartialKey;
    }


    private void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {

        String s = "";

        /*
        if(mapFactory==null){
            s=s+"keine Factory ";
        }else{
            s=s+"hat Factory; ";
        }
        s=s+"Value: "+value+"; ";
        s=s+"IngoingPartialKey: "+ingoingPartialKey+"; ";
        s=s+"Parent: "+parent.getValue()+"; ";
        s=s+"EdgeMap: ";
        for (Character mapCharacter : (Iterable<Character>) this.outgoingEdgeMap.keySet()) {
            s=s+mapCharacter+", ";
        }
        */
        return s;
    }

}
