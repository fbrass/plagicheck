package alignerPackage;

public class SimpleAlignmentMatrix implements IAlignmentMatrix {

	private IAlignmentContent defaultAC;
	private IAlignmentContent[][] matrix;


	public SimpleAlignmentMatrix(int i, int j, IAlignmentContent defaultAC) {
		matrix = new AlignmentContent[i + 5][j + 5];
		this.defaultAC = defaultAC;
		matrix[i][j]=defaultAC;
	}

	@Override
	public void set(int i, int j, IAlignmentContent ac) {
		matrix[i][j]=ac;
		
	}

	@Override
	public IAlignmentContent get(int i, int j) {

		return matrix[i][j];
	}


}
