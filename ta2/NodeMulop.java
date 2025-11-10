/**
 * Class to hold how we are multiplying two values together (or dividing)
 */
public class NodeMulop extends Node {

	private String mulop;
/**
 * Constructor to hold:
 * @param pos - position of operator in string
 * @param mulop - the operator that we will use to evaluate
 */
	public NodeMulop(int pos, String mulop) {
		this.pos=pos;
		this.mulop=mulop;
	}

	/**
	 * perform operation on specified numbers
	 * @param o1 first number in operation
	 * @param o2 second number in operation
	 * @return result
	 * @throws EvalException if not one of these operations
	 */
	public double op(double o1, double o2) throws EvalException {
		if (mulop.equals("*"))
			return o1*o2;
		if (mulop.equals("/"))
			return o1/o2;
		throw new EvalException(pos,"bogus mulop: "+mulop);
	}
/**
 * return operator to be included in C code
 */
	public String code() { return mulop; }

}
