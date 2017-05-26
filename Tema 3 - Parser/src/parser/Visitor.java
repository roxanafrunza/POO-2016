package parser;

public interface Visitor {
	
	public Operand visit(Node op);
	public Operand visit(Operand op);
	public Operand visit(Plus op);
	public Operand visit(Minus op);
	public Operand visit(Divide op);
	public Operand visit(Multiply op);
	public Operand visit(OpenParanthesis op);
	public Operand visit(ClosedParanthesis op);
}
