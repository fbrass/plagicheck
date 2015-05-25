import DFAPackage.DFA;
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

    private Character c;


    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        this.c=null;
    }

    @Test
    public void testIsFinal(){
        assertTrue(DFA.isFinal(1));
        assertFalse(DFA.isFinal(0));
        assertFalse(DFA.isFinal(20));
    }

    @Test
    public void testTransForLetters(){
        c= 'a';
        assertEquals(1, DFA.trans(0, c));
        c= 'a';
        assertEquals(1,DFA.trans(1,c));
        c= 'A';
        assertEquals(1,DFA.trans(0,c));
        c= 'A';
        assertEquals(1, DFA.trans(1, c));

        //failurestate
        c= 'A';
        assertEquals(127, DFA.trans(3, c));
    }

    @Test
    public void testTransForPunctuation() {
        c = ',';
        assertEquals(5, DFA.trans(0, c));
        c = '!';
        assertEquals(5, DFA.trans(0, c));
        c = ';';
        assertEquals(5, DFA.trans(0, c));
        c = '?';
        assertEquals(5, DFA.trans(0, c));
        c = '.';
        assertEquals(5, DFA.trans(0, c));
        c = '.';
        assertEquals(5, DFA.trans(5, c));

        //failurestate
        c = '.';
        assertEquals(127, DFA.trans(1, c));
    }

    @Test
    public void testTransWhiteSpaces() {
        c = ' ';
        assertEquals(3, DFA.trans(0, c));
        assertEquals(3, DFA.trans(3, c));
    }
    @Test
    public void testTransStateSeven(){
        c= '8';
        assertEquals(7,DFA.trans(15,c));
        assertEquals(7,DFA.trans(7,c));
    }
    @Test
    public void testTransNumbers(){
        c= '1';
        assertEquals(14,DFA.trans(0,c));
        assertEquals(15,DFA.trans(14,c));
        Character d= '.';
        assertEquals(16,DFA.trans(15,d));
        assertEquals(17,DFA.trans(16,c));
        assertEquals(18,DFA.trans(17,c));
        assertEquals(19,DFA.trans(18,d));
        assertEquals(20,DFA.trans(19,c));
        assertEquals(22,DFA.trans(20,c));

        assertEquals(127,DFA.trans(18,c));
        assertEquals(127,DFA.trans(16,d));
        assertEquals(127,DFA.trans(17,d));
        assertEquals(127,DFA.trans(19,d));
        assertEquals(127,DFA.trans(20,d));

        c= 'a';
        assertEquals(127,DFA.trans(14,c));
        assertEquals(127,DFA.trans(15,c));
        assertEquals(127,DFA.trans(16,c));
        assertEquals(127,DFA.trans(17,c));
        assertEquals(127,DFA.trans(18,c));
        assertEquals(127,DFA.trans(19,c));
        assertEquals(127,DFA.trans(20,c));
        assertEquals(127,DFA.trans(22,c));
    }

    @Test
    public void testPunctattion(){
        //System.out.println(c.getType('a'));
        //System.out.println(Character.DIRECTIONALITY_RIGHT_TO_LEFT);
    }


}
