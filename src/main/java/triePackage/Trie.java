package triePackage;

import actionsPackage.IActionAtInsert;
import mapPackage.IMapFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Felix on 27.03.2015.
 */
public class Trie implements ITrie {

    private IMapFactory mapFactory;
    private ITrieNode rootNode;

    /**
     * Constructor for Trie
     * @param mapFactory
     */
    public Trie(IMapFactory mapFactory) {
        this.mapFactory = mapFactory;
        this.rootNode = new TrieNode(mapFactory);
    }


    @Override
    public ITrieReference insert(Iterator iterator, IActionAtInsert actionInsert) {
        return rootNode.recursiveInsert(iterator, actionInsert);
    }

    @Override
    public ITrieReference insert(String s, IActionAtInsert a) {
        List<Character> charList = new ArrayList();

        for(int i = 0; i<s.length(); i++){
            charList.add(s.charAt(i));
        }

        Iterator<Character> iteratorCharList= charList.iterator();
        return insert(iteratorCharList, a);
    }

    @Override
    public String toString(){
        Iterator it=this.rootNode.getOutgoingEdgeMap().values().iterator();
        String s=recursiveToString(it,"");

        return rootNode.toString();
    }

    public String recursiveToString(Iterator it,String s){
        String t = s;
        if(it.hasNext()){
            TrieNode tn= (TrieNode) it.next();
            System.out.print(s+tn.getIngoingPartialKey());
            if(tn.getValue()!=0){
                System.out.print("                  |->"+tn.getValue());

            }
            if(tn.getOutgoingEdgeMap().isEmpty()){
                System.out.print("\n");

            }
            else {
                System.out.print("\n");
                s = s + "..";
            }
            Iterator it2=tn.getOutgoingEdgeMap().values().iterator();
            recursiveToString(it2,s);
            recursiveToString(it,t);
        }
        return s;
    }

}
