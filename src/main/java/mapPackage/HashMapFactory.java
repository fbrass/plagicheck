package mapPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Felix on 27.03.2015.
 */
public class HashMapFactory implements IMapFactory {
    @Override
    public Map create() {
        return new HashMap<>();
    }
}
