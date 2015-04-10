import actionsPackage.StringCoding;
import static org.junit.Assert.assertNotSame;
import mapPackage.TreeMapFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import triePackage.ITrieReference;
import triePackage.Trie;

/**
 * Created by Wilhelm on 01.04.2015.
 */
public class TrieTest {

    private Trie trie;


    @Before
    public void setUp() {
        this.trie = new Trie(new TreeMapFactory());
    }

    @After
    public void tearDown() {
        this.trie = null;
    }

    @Test
    public void testInsertString() {
        ITrieReference iTrieReference = trie.insert("trietest", new StringCoding(123));
        //System.out.print(iTrieReference.toString());
    }

    @Test
    public void testInsertStringFourTimes() {
        StringCoding sc = new StringCoding(123);
        ITrieReference iTrieReference1 = trie.insert("test1", sc);
        ITrieReference iTrieReference2 = trie.insert("test2", sc);
        ITrieReference iTrieReference3 = trie.insert("test3", sc);
        ITrieReference iTrieReference4 = trie.insert("test4", sc);
    }

    @Test
    public void testOutputForMoenke(){
        this.trie = new Trie(new TreeMapFactory());
        StringCoding sc= new StringCoding(4710);
        trie.insert("alpha",sc);
        trie.insert("alphabet",sc);
        trie.insert("alfons",sc);
        trie.insert("alf", sc);
        //trie.toString();
    }

    @Test
    public void testStringCoding(){
        this.trie = new Trie(new TreeMapFactory());
        StringCoding sc= new StringCoding(4711);
        trie.insert("alpha",sc);
        trie.insert("alpha",sc);
        trie.insert("alpha",sc);
        ITrieReference trieReference=trie.insert("alpha", sc);
        System.out.println(trieReference.toString());

        trie.toString();
    }

    @Test
    public void testTrieReferenceOfRecursiveInsert() throws Exception {
        StringCoding sc= new StringCoding(4711);
        ITrieReference tr1=trie.insert("alpha", sc);
        ITrieReference tr2=trie.insert("alpha", sc);
        assertNotSame("testTrieReferenceOfRecursiveInsert",tr1.isFound(),tr2.isFound());

    }




}
