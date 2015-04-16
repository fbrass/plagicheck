package actionsPackage;

/**
 * Interface for Inseration
 * Created by Felix on 27.03.2015.
 */
public interface IActionAtInsert {

    void setActualValue(Object value);
    Object actionAtKeyNotFound();
    Object actionAtKeyFound(Object previous);
}
