package parser;

public class OpenParanthesis extends Operator {

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getOperatorType() {
		return "(";
	}

}
