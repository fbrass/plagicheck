package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Felix on 27.03.2015.
 */
public class TrieNode implements ITrieNode {

    private IMapFactory mapFactory;
    private Map outgoingEdgeMap;
    private ITrieNode parent;
    private Object ingoingPartialKey;
    private int value=0;


    public TrieNode(IMapFactory mapFactory, ITrieNode parent, Object ingoingPartialKey) {
        this.mapFactory = mapFactory;
        this.outgoingEdgeMap = mapFactory.create();
        this.parent = parent;
        this.ingoingPartialKey = ingoingPartialKey;
    }

    public TrieNode(IMapFactory mapFactory){
        this.mapFactory=mapFactory;
        this.outgoingEdgeMap=mapFactory.create();
    }

    @Override
    public ITrieReference recursiveInsert(Iterator k, IActionAtInsert a) {
        if (k.hasNext()) {
            Comparable kComparable = (Comparable) k.next();
            Iterator<Comparable> it = this.outgoingEdgeMap.keySet().iterator();
            while (it.hasNext()) {
                Comparable mapComparable = it.next();
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
                if(getValue()==0) {
                    this.setValue((Integer) a.actionAtKeyNotFound());
                    return new TrieReference(false,this.getValue(),this);
                }
                return new TrieReference(true, this.getValue(), this);
            } else {
                if(getValue()==0){
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


    private void setValue(int i){
        this.value=i;
    }

    public int getValue(){return value;}

    @Override
    public String toString(){
        return myRecursiveToString("");
    }

    public String myRecursiveToString(String s){
        if(this.parent==null){

        }
        return null;
    }
}
