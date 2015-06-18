import framework.Framework;
import org.junit.Test;

/**
 * Created by Felix on 27.03.2015.
 * JUnit test for Java Class PlagiCheck
 */
public class PlagiCheckTest {

    @Test
    public void testingOpenFile() throws Exception {
        String s= System.getProperty("user.dir")+"\\src\\test\\resource\\";
        String s2 = s+"Input 2.txt";
        s=s+"Input 1.txt";

        String[] sarr= new String[2];
        sarr[0]=s;
        sarr[1]=s2;
        PlagiCheck.main(sarr);
    }
}
