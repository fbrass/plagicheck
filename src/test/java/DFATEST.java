import DFAPackage.DFA;
import DFAPackage.IDFA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TestClass for DFA
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
        c= 'a';
        assertEquals(1,this.dfa.trans(0, c));
        c= 'a';
        assertEquals(1,this.dfa.trans(1,c));
        c= 'A';
        assertEquals(1,this.dfa.trans(0,c));
        c= 'A';
        assertEquals(1, this.dfa.trans(1, c));

        //failurestate
        c= 'A';
        assertEquals(127, this.dfa.trans(3, c));
    }

    @Test
    public void testTransForPunctuation() {
        c = ',';
        assertEquals(5, this.dfa.trans(0, c));
        c = '!';
        assertEquals(5, this.dfa.trans(0, c));
        c = ';';
        assertEquals(5, this.dfa.trans(0, c));
        c = '?';
        assertEquals(5, this.dfa.trans(0, c));
        c = '.';
        assertEquals(5, this.dfa.trans(0, c));
        c = '.';
        assertEquals(5, this.dfa.trans(5, c));

        //failurestate
        c = '.';
        assertEquals(127, this.dfa.trans(1, c));
    }

    @Test
    public void testTransWhiteSpaces() {
        c = ' ';
        assertEquals(3, this.dfa.trans(0, c));
        assertEquals(3, this.dfa.trans(3, c));
    }
    @Test
    public void testTransStateSeven(){
        c= '8';
        assertEquals(7,this.dfa.trans(15,c));
        assertEquals(7,this.dfa.trans(7,c));
    }
    @Test
    public void testTransNumbers(){
        c= '1';
        assertEquals(14,this.dfa.trans(0,c));
        assertEquals(15,this.dfa.trans(14,c));
        Character d= '.';
        assertEquals(16,this.dfa.trans(15,d));
        assertEquals(17,this.dfa.trans(16,c));
        assertEquals(18,this.dfa.trans(17,c));
        assertEquals(19,this.dfa.trans(18,d));
        assertEquals(20,this.dfa.trans(19,c));
        assertEquals(22,this.dfa.trans(20,c));

        assertEquals(127,this.dfa.trans(18,c));
        assertEquals(127,this.dfa.trans(16,d));
        assertEquals(127,this.dfa.trans(17,d));
        assertEquals(127,this.dfa.trans(19,d));
        assertEquals(127,this.dfa.trans(20,d));

        c= 'a';
        assertEquals(127,this.dfa.trans(14,c));
        assertEquals(127,this.dfa.trans(15,c));
        assertEquals(127,this.dfa.trans(16,c));
        assertEquals(127,this.dfa.trans(17,c));
        assertEquals(127,this.dfa.trans(18,c));
        assertEquals(127,this.dfa.trans(19,c));
        assertEquals(127,this.dfa.trans(20,c));
        assertEquals(127,this.dfa.trans(22,c));
    }

    @Test
    public void testCharacterStuff(){
        //System.out.println(c.getType('a'));
        //System.out.println(Character.DECIMAL_DIGIT_NUMBER);
    }
}
