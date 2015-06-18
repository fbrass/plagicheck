package alignerPackage;

public interface IAlignmentMatrix {

    void set(int i, int j, IAlignmentContent ac);
    IAlignmentContent get(int i, int j);
}
