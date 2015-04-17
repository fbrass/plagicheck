import actionsPackage.StringCoding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import mapPackage.TreeMapFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import triePackage.ITrie;
import triePackage.ITrieReference;
import triePackage.Trie;

/**
 * Created by Felix on 01.04.2015.
 * Testclass for Trie
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
        trie.insert("trietest", new StringCoding(123));
        //System.out.print(iTrieReference.toString());
    }

    @Test
    public void testInsertStringFourTimes() {
        StringCoding sc = new StringCoding(123);
        trie.insert("test1", sc);
        trie.insert("test2", sc);
        trie.insert("test3", sc);
        trie.insert("test4", sc);
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
    public void testStringCodingValue(){
        this.trie = new Trie(new TreeMapFactory());
        StringCoding sc= new StringCoding(4711);
        trie.insert("alpha", sc);
        ITrieReference trieReference=trie.insert("alpha", sc);
        assertEquals(trieReference.getValue(), 4711);
        assertEquals(sc.getActualValue(), 4712);
        trieReference=trie.insert("alphabet",sc);
        assertEquals(trieReference.getValue(), 4712);
        assertEquals(sc.getActualValue(), 4713);
    }

    @Test
    public void testTrieReference(){
        StringCoding sc = new StringCoding(4711);
        ITrieReference tr1 = trie.insert("alpha",sc);
        ITrieReference tr2 = trie.insert("alphabet",sc);
        ITrieReference tr3 = trie.insert("alpha",sc);

        assertSame(tr1.isFound(), tr2.isFound());
        assertNotSame(tr1.isFound(),tr3.isFound());
        assertSame(tr1.getNode(), tr3.getNode());
        assertEquals(tr1.getValue(),tr3.getValue());
    }

    @Test
    public void testTrieReferenceOfRecursiveInsert() {
        StringCoding sc= new StringCoding(4711);
        ITrieReference tr1=trie.insert("alpha", sc);
        ITrieReference tr2=trie.insert("alpha", sc);
        assertNotSame("testTrieReferenceOfRecursiveInsert", tr1.isFound(), tr2.isFound());
    }

    @Test
    public void testInsertFourTimesTheSame() {
        StringCoding sc= new StringCoding(4711);
        ITrieReference tr1=trie.insert("alpha",sc);
        trie.insert("alpha",sc);
        trie.insert("alpha",sc);
        ITrieReference tr2=trie.insert("alpha", sc);
        assertNotSame(tr1.isFound(),tr2.isFound());
        assertSame(tr1.getNode(),tr2.getNode());
        assertEquals(tr1.getValue(),tr2.getValue());
    }




}
