package parser;

import java.util.*;

public class Parser {

	Stack<Node> operators = new Stack<Node>();
	Stack<Node> variables = new Stack<Node>();

	/**
	 * Checks if given string is a valid operator. Valid operators are: "+",
	 * "-", "*", "/"
	 * 
	 * @param s
	 *            the string to check
	 * @return true if string is a valid operator, false otherwise
	 */
	public boolean isOperator(String s) {
		if (s == "+" || s == "-" || s == "*" || s == "/")
			return true;
		return false;
	}

	/**
	 * Given a string and a list of operands, checks if an operand with the name
	 * given as string is a valid operands
	 * 
	 * @param s
	 *            name of operand
	 * @param operands
	 *            list of operands
	 * @return true if operand is valid, false otherwise
	 */
	public boolean isOperand(String s, ArrayList<Operand> operands) {
		for (int i = 0; i < operands.size(); i++) {
			if (s.equals(operands.get(i).getName()))
				return true;
		}
		return false;
	}

	/**
	 * CHecks if string is a closed parenthesis
	 * 
	 * @param s
	 *            string to be checked
	 * @return true if string is a closed parenthesis, false otherwise
	 */
	public boolean isClosedParanthesis(String s) {
		return s.equals(")");
	}

	/**
	 * CHecks if string is an open parenthesis
	 * 
	 * @param s
	 *            string to be checked
	 * @return true if string is an open parenthesis, false otherwise
	 */
	public boolean isOpenParanthesis(String s) {
		return s.equals("(");
	}

	/**
	 * Checks if operation op1 has higher priority than op2. Priority order: "*"
	 * = "/" < "+" = "-"
	 * 
	 * @param op1
	 *            first operator
	 * @param op2
	 *            second operator
	 * @return 0 if operators have the same priority, 1 if operator1 has higher
	 *         priority and -1 otherwise
	 */
	public int hasHigherPriority(String op1, String op2) {
		switch (op1) {
		case ("+"):
			if (op2.equals("-") || op2.equals("+"))
				return 0;
			else
				return -1;
		case ("-"):
			if (op2.equals("-") || op2.equals("+"))
				return 0;
			else
				return -1;
		case ("*"):
			if (op2.equals("/") || op2.equals("*"))
				return 0;
			else
				return 1;
		case ("/"):
			if (op2.equals("/") || op2.equals("*"))
				return 0;
			else
				return 1;
		}
		return 0;
	}

	/**
	 * Given an expression and a list of operands, it creates a binary tree
	 * which contains nodes of type either Operand or Operator, using two stacks
	 * one for operands and one for operators. This tree will be used to
	 * evaluate the expression by doing a right-root-left search.
	 * 
	 * @param expression
	 *            the expression used to create the tree
	 * @param operands
	 *            a list of operands
	 * @return the root of the tree
	 */
	public Node createTree(String expression, ArrayList<Operand> operands) {
		Scanner exp = new Scanner(expression);
		// get each token of expression
		while (exp.hasNext()) {
			String token = exp.next();
			if (isOperand(token, operands)) {
				// if the token is in the list of operands
				// get Operand and add it to operands queue
				for (int i = 0; i < operands.size(); i++)
					if (token.equals(operands.get(i).getName())) {
						variables.push(operands.get(i));
						break;
					}
			} else if (isOpenParanthesis(token)) {
				// if the token is "(" we add it to operators stack
				operators.push(OperatorFactory.createOperator(token));
			} else if (isClosedParanthesis(token)) {
				// if the token is ")" we get one operator and two operands and
				// create a new node. the process is repeated until we find ")"
				while (!isOpenParanthesis(((Operator) operators.peek()).getOperatorType())) {
					Node aux = operators.pop();
					Node op1 = variables.pop();
					Node op2 = variables.pop();

					aux.setLeft(op1);
					aux.setRight(op2);
					variables.push(aux);
				}
				// pop the "(" from operators stack
				if (isOpenParanthesis(((Operator) operators.peek()).getOperatorType()))
					operators.pop();
			} else {
				// if there the operators stack is empty, we add the token
				if (operators.isEmpty()) {
					operators.push(OperatorFactory.createOperator(token));
				} else {
					// get the operators from stack top
					Operator op = (Operator) operators.peek();
					String opType = op.getOperatorType();
					// if current operator has higher priority than stack top or
					// the top is "(", add the operator to stack
					if (hasHigherPriority(token, opType) > 0 || isOpenParanthesis(opType))
						operators.push(OperatorFactory.createOperator(token));
					else {
						// if the stack top has higher priority, create a nod
						// where the root is operators stack top and the two
						// sons are the first two nodes from operands stack
						Node aux = operators.pop();
						Node op1 = variables.pop();
						Node op2 = variables.pop();
						aux.setLeft(op1);
						aux.setRight(op2);
						// push the node two operands stack
						variables.push(aux);
						// repeat the process until current operators has higher
						// priority or there are no operators left in stack
						if (operators.isEmpty()) {
							operators.push(OperatorFactory.createOperator(token));
						} else {
							while (!operators.isEmpty()) {
								op = (Operator) operators.peek();
								opType = op.getOperatorType();
								if (hasHigherPriority(token, opType) > 0 || isOpenParanthesis(opType)) {
									operators.push(OperatorFactory.createOperator(token));
									break;
								} else {
									aux = (Operator) operators.pop();
									op1 = variables.pop();
									op2 = variables.pop();

									aux.setLeft(op1);
									aux.setRight(op2);
									variables.push(aux);
								}
							}
							if (operators.isEmpty())
								operators.push(OperatorFactory.createOperator(token));
						}
					}
				}
			}
		}
		// after finishing all the tokens from expression, get each operators
		// from operators stack and create a node where the root is the
		// operators and the sons are the first 2 nodes from operands stack
		while (!operators.isEmpty()) {
			Node aux = operators.pop();
			Node op1 = variables.pop();
			Node op2 = variables.pop();
			aux.setLeft(op1);
			aux.setRight(op2);
			variables.push(aux);
		}
		exp.close();
		return variables.pop();
	}
}
