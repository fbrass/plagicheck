import actionsPackage.*;
import mapPackage.IMapFactory;
import mapPackage.TreeMapFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import triePackage.Trie;

/**
 * Created by Wilhelm on 01.04.2015.
 */
public class TrieTest {

    private Trie trie;

    @Before
    public void setUp(){
        this.trie = new Trie(new TreeMapFactory());
    }

    @After
    public void tearDown(){
        this.trie=null;
    }

    @Test
    public void testInsertString(){
        trie.insert("asd",new StringCoding(123));
    }
}
