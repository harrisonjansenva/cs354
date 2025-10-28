/**
 * Abstract class that allows all tokens to be a node- a beautiful example of inheritance and polymorphism
 * @author: a sadist
 * Date: Sun Oct 26 18:30:15 MDT 2025
 */
public class NodeAddop extends Node {

	private String addop;

/**
 * Constructor to make an addop binding
 * @param pos - position to evaluate
 * @param addop the string of the addop
 */
	public NodeAddop(int pos, String addop) {
		this.pos=pos;
		this.addop=addop;
	}

	/**
	 * perform addop specified
	 * @param o1 first number
	 * @param o2 second number
	 * @return operation completed
	 * @throws EvalException if somehow we were to pull an incorrect addop
	 */
	public int op(int o1, int o2) throws EvalException {
		if (addop.equals("+"))
			return o1+o2;
		if (addop.equals("-"))
			return o1-o2;
		throw new EvalException(pos,"bogus addop: "+addop);
	}

	/**
	 * return the addop to be added in the C file
	 */
	public String code() { return addop; }

}
