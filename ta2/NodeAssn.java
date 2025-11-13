/**
 * Class that holds an assn binding. it holds the id, and then evaluates the
 * expr that comes after that
 * @author: a sadist
 * Date: Sun Oct 26 18:30:15 MDT 2025
 */
public class NodeAssn extends NodeStmtVal {

	private String id;
	private NodeExpr expr;
/**
 * Constructor to build an assn binding
 * @param id - the name we reference for this binding
 * @param expr - the data it holds
 */
	public NodeAssn(String id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}
/**
 * Evaluate the semantics of the assn we are holding
 * @return the value eventually bound to this assignment
 */
	public double eval(Environment env) throws EvalException {
		return env.put(id, new NodeWr(expr).eval(env));
	}
/**
 * Generate the code to show the process of how this gets evaluated.
 * @return the string containing the C code
 */
	public String code() {
		return id + "=" + expr.code() + ";" + new NodeWr(expr).code();
	}

}
