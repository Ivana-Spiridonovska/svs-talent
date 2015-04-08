public class Equation {
	private int operandOne;
	private int operandTwo;
	private int result;
	private Operator operator;
	
	public Equation(int operand1, int operand2, Operator operator){
		this.operandOne = operand1;
		this.operandTwo = operand2;
		this.operator = operator;
	}

	public int getOperandOne() {
		return operandOne;
	}

	public void setOperandOne(int operandOne) {
		this.operandOne = operandOne;
	}

	public void setOperandTwo(int operandTwo) {
		this.operandTwo = operandTwo;
	}

	public int getOperandTwo() {
		return operandTwo;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}
	
	public void setOperation(Operator operator) {
		this.operator = operator;
	}
	
	public Operator getOperation() {
		return operator;
	}

	public String toString(){
		return getOperandOne() + " " + getOperation().getSign() + " " + getOperandTwo() + " = " + getResult();
	}
}
