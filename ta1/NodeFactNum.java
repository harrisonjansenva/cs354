/**
 * Class to pull the value of a number from our environment
 */
public class NodeFactNum extends NodeFact {

	private String num;

	public NodeFactNum(String num) {
		this.num=num;
	}
/**
 * return the value of the double stored at this position in the string
 */
	public double eval(Environment env) throws EvalException {
		return Double.parseDouble(num);
	}

	/**
	 * return value of number to generate in C code
	 */
	public String code() { return num; }

}
