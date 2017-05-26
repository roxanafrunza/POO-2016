package parser;

public class OperatorFactory {

	private static OperatorFactory instance = null;

	private OperatorFactory() {

	}

	public static OperatorFactory getInstance() {
		if (instance == null)
			instance = new OperatorFactory();
		return instance;
	}

	/**
	 * Create the operator with type given. Type can be: + , - , * , / . Throws
	 * IllegalArgument exception if operator type isn't recognised.
	 * 
	 * @param type
	 *            operator's type
	 * @return
	 */
	public static Operator createOperator(String type) {
		switch (type) {
		case ("+"):
			return new Plus();
		case ("*"):
			return new Multiply();
		case ("-"):
			return new Minus();
		case ("/"):
			return new Divide();
		case ("("):
			return new OpenParanthesis();
		case (")"):
			return new ClosedParanthesis();
		}
		throw new IllegalArgumentException("The operation type " + type + " is not recognized.");
	}
}
