package triePackage;

/**
 * Created by Wilhelm on 28.03.2015.
 */
public class TrieReference implements ITrieReference {

    boolean found;
    Object value;
    ITrieNode node;

    public TrieReference(boolean found, Object value, ITrieNode node) {
        this.found = found;
        this.value = value;
        this.node = node;
    }

    @Override
    public boolean isFound() {
        return found;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public ITrieNode getNode() {
        return node;
    }

    @Override
    public String toString(){
        return found +" "+value.toString()+" "+node.toString();
    }
}
