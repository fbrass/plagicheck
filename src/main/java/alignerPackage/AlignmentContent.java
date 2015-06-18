package alignerPackage;

public class AlignmentContent implements IAlignmentContent {

	private double value;
	private Direction direction;
	
	
	public AlignmentContent(double value, Direction direction){ 
		this.value=value;
		this.direction =direction;
	}
	public AlignmentContent(double value){ 
		this.value=value;
	}

	
	public AlignmentContent(){ 
		
	}

	@Override
	public void setValue(double value) {
		this.value = value;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public double getValue() {

		return value;
	}

	public Direction getDirection() {
		return direction;
	}

}
