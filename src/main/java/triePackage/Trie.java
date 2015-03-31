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

    public Trie(IMapFactory mapFactory) {
        this.mapFactory = mapFactory;
    }

    @Override
    public ITrieReference insert(Iterator iterator, IActionAtInsert actionInsert) {

        //key für anfangsbuchstabe muss herausgefunden werden
        Integer key = Integer.valueOf(12345);

        ITrieNode nodeFirstCharacter = new TrieNode(mapFactory, null, key);

        return nodeFirstCharacter.recursiveInsert(iterator, actionInsert);


    }

    @Override
    public ITrieReference insert(String s, IActionAtInsert a) {
        List<Character> charList = new ArrayList();

        for(int i = 0; i<s.length(); i++){
            charList.add(s.charAt(i));
        }

        Iterator<Character> iteratorCharList= charList.iterator();
        return insert (iteratorCharList, a);
    }
}
