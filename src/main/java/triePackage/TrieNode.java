package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.Comparator;
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


    public TrieNode(IMapFactory mapFactory, ITrieNode parent, Object ingoingPartialKey){
        this.mapFactory = mapFactory;
        this.outgoingEdgeMap=mapFactory.create();
        this.parent = parent;
        this.ingoingPartialKey = ingoingPartialKey;


    }

    @Override
    public ITrieReference recursiveInsert(Iterator k, IActionAtInsert a) {
        if(k.hasNext()){
            Comparable kComparable = (Comparable) k.next();
            Iterator<Comparable> it = this.outgoingEdgeMap.keySet().iterator();

            while(it.hasNext()){
                Comparable mapComparable=it.next();
                if(mapComparable.compareTo(kComparable)==0){
                    TrieNode tn= (TrieNode) this.outgoingEdgeMap.get(mapComparable);
                    return tn.recursiveInsert(k,a);
                }
            }

            TrieNode tn=new TrieNode(mapFactory,this,kComparable);
            outgoingEdgeMap.put(kComparable,tn);
            if(k.hasNext()){
                return tn.recursiveInsert(k,a);
            } else{
                return new TrieReference(false,kComparable,tn);
            }

        } else {
            return new TrieReference(true,this.ingoingPartialKey,this);
        }

    }
}
