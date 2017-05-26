package parser;

public class Multiply extends Operator {

	public Multiply() {
		super();
	}

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getOperatorType() {
		return "*";
	}

	/**
	 * The multiply operation : operand1 * operand2
	 * 
	 * @param operand1
	 *            first operand
	 * @param operand2
	 *            second operand
	 * @return result operand
	 */
	public Operand multiply(Operand operand1, Operand operand2) {
		String resultName = operand1.getName() + " * " + operand2.getName();
		if (operand1 instanceof IntegerType) {
			if (operand2 instanceof IntegerType) {
				// integerType * integerType = integerType
				int result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getIntegerValue() * operand2.getIntegerValue();
					return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "int", Integer.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof DoubleType) {
				// integerType * doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() * operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// integerType * stringType = stringType
				String result = "";
				// if first operand is <=0 or NaN, the result is '\0'
				if (operand1.getIntegerValue() > 0 && operand1.isNumeric()) {
					int n = operand1.getIntegerValue();
					result = new String(new char[n]).replace("\0", operand2.getStringValue());
				}
				return OperandFactory.createOperand(resultName, "string", result);
			}
		} else if (operand1 instanceof DoubleType) {
			// doubleType * integerType = doubleType
			if (operand2 instanceof IntegerType) {
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() * operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof DoubleType) {
				// doubleType * doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() * operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// doubleType * stringType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric) {
					result = operand1.getDoubleValue() * operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			}

		} else if (operand1 instanceof StringType) {
			if (operand2 instanceof IntegerType) {
				// stringType * integerType = stringType
				String result = "";
				if (operand2.getIntegerValue() > 0 && operand2.isNumeric() && operand1.isNumeric) {
					int n = operand2.getIntegerValue();
					result = new String(new char[n]).replace("\0", operand1.getStringValue());
				}
				return OperandFactory.createOperand(resultName, "string", result);
			} else if (operand2 instanceof DoubleType) {
				// stringType * doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric()) {
					result = operand1.getDoubleValue() * operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// stringType * stringType = integerType
				int result = operand1.getIntegerValue() * operand2.getIntegerValue();
				return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
			}
		}
		throw new IllegalArgumentException("The operand's type is not recognized.");
	}

}
