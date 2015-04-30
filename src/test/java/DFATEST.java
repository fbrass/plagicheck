import DFAPackage.DFA;
import DFAPackage.IDFA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Felix on 30.04.2015.
 */
public class DFATEST {

    private IDFA dfa;
    private Character c;


    @Before
    public void setUp() {

        this.dfa = new DFA();
    }

    @After
    public void tearDown() {
        this.dfa = null;
        this.c=null;
    }

    @Test
    public void testIsFinal(){
        assertTrue(this.dfa.isFinal(1));
        assertFalse(this.dfa.isFinal(0));
        assertFalse(this.dfa.isFinal(20));
    }

    @Test
    public void testTransForLetters(){
        c= new Character('a');
        assertEquals(1,this.dfa.trans(0, c));
        c=new Character('a');
        assertEquals(1,this.dfa.trans(1,c));
        c=new Character('A');
        assertEquals(1,this.dfa.trans(0,c));
        c=new Character('A');
        assertEquals(1, this.dfa.trans(1, c));

        //failurestate
        c=new Character('A');
        assertEquals(127, this.dfa.trans(3, c));
    }

    @Test
    public void testTransForPunctuation(){
        c=new Character(',');
        assertEquals(3, this.dfa.trans(0, c));
        c=new Character('!');
        assertEquals(3, this.dfa.trans(0, c));
        c=new Character('.');
        assertEquals(3, this.dfa.trans(0, c));
        c=new Character('.');
        assertEquals(3, this.dfa.trans(3, c));

        //failurestate
        c=new Character('.');
        assertEquals(127, this.dfa.trans(1, c));


        System.out.println(c.getType(','));
        System.out.println(Character.OTHER_PUNCTUATION);
    }
}
