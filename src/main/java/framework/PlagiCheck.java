package framework;

/**
 * Created by Felix on 27.03.2015.
 * <p/>
 * PlagiCheck is used to run the AlignmentController and parse the files
 */
public class PlagiCheck {

    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            AlignmentController controller = new AlignmentController(args[0], args[1]);
            controller.run();
        } else throw new Exception("Wir erwarten den Namen des Orginals und den Namen des Plagiats");
    }

}
