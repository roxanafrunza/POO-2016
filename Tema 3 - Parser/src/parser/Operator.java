package parser;

public abstract class Operator extends Node{

	
	public Operator() {
		super();
		super.setOperator(true);
	}

	public abstract String getOperatorType();
	
}
