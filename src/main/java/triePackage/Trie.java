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
    public Trie(IMapFactory mapFactory) {
    }

    @Override
    public ITrieReference insert(Iterator k, IActionAtInsert a) {



        while (k.hasNext()){
            /*
            logik fehlt
             */
        }
        return null;
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
