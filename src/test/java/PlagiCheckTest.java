import mapPackage.PlagiCheck;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Felix on 27.03.2015.
 * JUnit test for Java Class PlagiCheck
 */
public class PlagiCheckTest {

    @Test
    public void testingOpenFile() throws IOException {
        String s= System.getProperty("user.dir");
        s=s+"\\src\\test\\resource\\Input 1.txt";
        String[] sarr= new String[2];
        sarr[0]=s;
        sarr[1]=s;
        PlagiCheck.main(sarr);
    }
}
