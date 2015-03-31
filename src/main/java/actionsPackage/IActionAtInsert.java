package actionsPackage;

/**
 * Created by Felix on 27.03.2015.
 */
public interface IActionAtInsert {

    public void setActualValue(Object value);
    public Object getActualValue();
    public Object actionAtKeyNotFound();
    public Object actionAtKeyFound(Object previous);
}
