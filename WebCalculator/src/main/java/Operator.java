public enum Operator {
	ADD("+"), SUBTRACT("-");
	
	private String sign;
	
	private Operator(String sign){
		this.sign = sign;
	}
	
	public String getSign(){
		return this.sign;
	}

}
