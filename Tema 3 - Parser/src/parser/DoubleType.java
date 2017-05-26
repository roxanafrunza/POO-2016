package parser;

public class DoubleType extends Operand {
	double value;

	public DoubleType(String name, double value) {
		super(name);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (!isNumeric())
			return "NaN";
		return Double.toString(Math.round(this.value * 100.0) / 100.0);
	}

	@Override
	public String getType() {
		return "double";
	}

	@Override
	public String getStringValue() {
		return Double.toString( Math.round(this.value * 100.0) / 100.0);
	}

	@Override
	public int getIntegerValue() {
		Double val = new Double(this.value);
		return val.intValue();
	}

	@Override
	public double getDoubleValue() {
		return this.getValue();
	}
}
