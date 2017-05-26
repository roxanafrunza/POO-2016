package parser;

public class Plus extends Operator {

	public Plus() {
		super();
	}

	@Override
	public Operand accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String getOperatorType() {
		return "+";
	}

	/**
	 * The addition operation: operand1 + operand2
	 * 
	 * @param operand1
	 *            first operand
	 * @param operand2
	 *            second operand
	 * @return result operand
	 */
	public Operand addition(Operand operand1, Operand operand2) {
		String resultName = operand1.getName() + " + " + operand2.getName();
		if (operand1 instanceof IntegerType) {
			// integerType + integerType = integerType
			if (operand2 instanceof IntegerType) {
				int result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getIntegerValue() + operand2.getIntegerValue();
					return OperandFactory.createOperand(resultName, "int", Integer.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "int", Integer.toString(result));
					op.setNumeric(false);
					return op;
				}

			} else if (operand2 instanceof DoubleType) {
				// integerType + doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric()) {
					result = operand1.getDoubleValue() + operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// integerType + stringType = stringType
				String result = "\0";
				// if operand isn't NaN
				if (operand1.isNumeric()) {
					result = operand1.getStringValue() + operand2.getStringValue();
				} else
					// if operand is NaN, result is "NaN" + string;
					result = "NaN" + operand2.getStringValue();
				return OperandFactory.createOperand(resultName, "string", result);
			}
		} else if (operand1 instanceof DoubleType) {
			if (operand2 instanceof IntegerType) {
				// doubleType + integerType = doubleType
				double result = 0;
				if (operand1.isNumeric() && operand2.isNumeric) {
					result = operand1.getDoubleValue() + operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}

			} else if (operand2 instanceof DoubleType) {
				// doubleType + doubleType = doubleType
				double result = 0;
				// operands aren't NaN
				if (operand1.isNumeric() && operand2.isNumeric) {
					result = operand1.getDoubleValue() + operand2.getDoubleValue();
					return OperandFactory.createOperand(resultName, "double", Double.toString(result));
				} else {
					// if one operand is NaN, the result is NaN
					Operand op = OperandFactory.createOperand(resultName, "double", Double.toString(result));
					op.setNumeric(false);
					return op;
				}
			} else if (operand2 instanceof StringType) {
				// doubleType + stringType = stringType
				String result = "\0";
				// if operand isn't NaN
				if (operand1.isNumeric()) {
					result = operand1.getStringValue() + operand2.getStringValue();
				} else
					// if operand is NaN, result is "NaN" + string;
					result = "NaN" + operand2.getStringValue();
				return OperandFactory.createOperand(resultName, "string", result);
			}

		} else if (operand1 instanceof StringType) {
			if (operand2 instanceof IntegerType) {
				// stringType + integerType = stringType
				String result = "\0";
				// if operand isn't NaN
				if (operand2.isNumeric()) {
					result = operand1.getStringValue() + operand2.getStringValue();
				} else
					// if operand is NaN, result is "NaN" + string;
					result = operand1.getStringValue() + "NaN";
				return OperandFactory.createOperand(resultName, "string", result);

			} else if (operand2 instanceof DoubleType) {
				// stringType + doubleType = stringType
				String result = "\0";
				// if operand isn't NaN
				if (operand2.isNumeric()) {
					result = operand1.getStringValue() + operand2.getStringValue();
				} else
					// if operand is NaN, result is "NaN" + string;
					result = operand1.getStringValue() + "NaN";
				return OperandFactory.createOperand(resultName, "string", result);
			} else if (operand2 instanceof StringType) {
				// stringType + stringType = stringType
				String result = operand1.getStringValue() + operand2.getStringValue();
				return OperandFactory.createOperand(resultName, "string", result);
			}
		}
		throw new IllegalArgumentException("The operand's type is not recognized.");
	}

}
