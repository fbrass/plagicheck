package mapPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Felix on 27.03.2015.
 *
 * PlagiCheck is used to run the AlignmentController and parse the files
 */
public class PlagiCheck {

    public static void main(String[] args) throws IOException{
        FileReader input1 = new FileReader(new File(args[0]));
        FileReader input2 = new FileReader(new File(args[1]));

        System.out.println(new BufferedReader(input1).readLine());
        System.out.println(new BufferedReader(input2).readLine());


    }
}
