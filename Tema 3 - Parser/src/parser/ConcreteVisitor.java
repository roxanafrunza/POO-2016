package parser;

public class ConcreteVisitor implements Visitor {

	/**
	 * Visits a node of Operand type
	 */
	@Override
	public Operand visit(Operand op) {
		return op;
	}

	/**
	 * Visits a node of Plus type
	 */
	@Override
	public Operand visit(Plus op) {
		if (op == null)
			return null;
		Operand operand1 = visit(op.getRight());
		Operand operand2 = visit(op.getLeft());

		return op.addition(operand1, operand2);

	}

	/**
	 * Visits a node of Minus type
	 */
	@Override
	public Operand visit(Minus op) {
		if (op == null)
			return null;
		Operand operand1 = visit(op.getRight());
		Operand operand2 = visit(op.getLeft());

		return op.substitution(operand1, operand2);

	}

	/**
	 * Visits a node of Divide Type
	 */
	@Override
	public Operand visit(Divide op) {
		if (op == null)
			return null;
		Operand operand1 = visit(op.getRight());
		Operand operand2 = visit(op.getLeft());

		return op.divide(operand1, operand2);

	}

	/**
	 * Visits a node of Multiply type
	 */
	@Override
	public Operand visit(Multiply op) {
		if (op == null)
			return null;
		Operand operand1 = visit(op.getRight());
		Operand operand2 = visit(op.getLeft());

		return op.multiply(operand1, operand2);

	}

	/**
	 * Visits a node of OpenParanthesis Type
	 */
	@Override
	public Operand visit(OpenParanthesis op) {
		return null;

	}

	/**
	 * Visits a node of ClosedParanthesis Type
	 */
	@Override
	public Operand visit(ClosedParanthesis op) {
		return null;

	}

	/**
	 * Visits a node according to its type
	 */
	@Override
	public Operand visit(Node node) {

		if (node instanceof Operand)
			return visit((Operand) node);
		if (node instanceof Plus)
			return visit((Plus) node);
		if (node instanceof Minus)
			return visit((Minus) node);
		if (node instanceof Multiply)
			return visit((Multiply) node);
		if (node instanceof Divide)
			return visit((Divide) node);
		return null;
	}

}
