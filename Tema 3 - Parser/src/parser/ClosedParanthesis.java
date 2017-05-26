package parser;

public class ClosedParanthesis extends Operator {

	@Override
	public String getOperatorType() {
		return ")";
	}

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);

	}

}
