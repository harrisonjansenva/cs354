/**
 * Class that outputs a Node once fully parsed into a C 
 */
public class NodeWr extends Node {

	private NodeExpr expr;

	public NodeWr(NodeExpr expr) {
		this.expr=expr;
	}
/**
 * Evaluate to ensure we are returning the right number
 */
	public double eval(Environment env) throws EvalException {
		double i = expr.eval(env);
		
		if (i == Math.floor(i))
			System.out.printf("%.0f%n", i);
		else
			System.out.printf("%g%n", i);
		return i;
	}
/**
 * Generate C code to print out the expression in it's final evaluated form
 */
	public String code() {
		return "printf(\"%g\\n\","
			+"(double)("
			+expr.code()
			+"));";
	}

}
