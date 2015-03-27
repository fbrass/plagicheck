package mapPackage;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Felix on 27.03.2015.
 * Factory that creates a Treemap.
 */
public class TreeMapFactory implements IMapFactory{
    @Override
    public Map create() {
        return new TreeMap<>();
    }
}
