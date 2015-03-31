package actionsPackage;

/**
 * Created by Felix on 27.03.2015.
 * Class that handles the string coding
 */
public class StringCoding implements IActionAtInsert{

    /* z‰hlt */
    private int counter = 0;

    /*Konstruktor setzt Z‰hler von auﬂen */
    public StringCoding (int start){
        counter = start;
    }

    @Override
    public void setActualValue(Object updateValue) {
        Integer updateVal = (Integer) updateValue;
        counter = updateVal.intValue();
    }

    @Override
    public Object getActualValue(Object value) {
        return counter;
    }

    @Override
    public Object actionAtKeyNotFound() {
        return new Integer(counter ++);
    }

    @Override
    public Object actionAtKeyFound(Object previous) {
        return previous;
    }
}
