/** 
 * Class to represent a fact that parses down to an expr
 */
public class NodeFactExpr extends NodeFact {

	private NodeExpr expr;
	private NodeNegExpr negExpr;
/**
 * Constructor to create this
 * @param negExpr - optional to see if value should be flipped after evaluated
 * @param expr - value to be evaluated
 */
	public NodeFactExpr(NodeExpr expr) {
		this.expr=expr;
	}
/**
 * Determine semantics of our Fact-Expr. if negative operator is present, invert value determined from evaluating expr.
 */
	public double eval(Environment env) throws EvalException {
		return negExpr == null ? expr.eval(env) : - expr.eval(env);
	}
/**
 * Return code to generate appropriate C code.
 */
	public String code() { return expr.code(); }

}
