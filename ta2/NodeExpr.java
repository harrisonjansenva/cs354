/**
 * Class to hold an expr binding
 * 
 * @author a sadist
 *         Date: Sun Oct 26 18:30:15 MDT 2025
 * 
 */
public class NodeExpr extends Node {

	private NodeTerm term;
	private NodeAddop addop;
	private NodeExpr expr;

	/**
	 * constructor to build an expr
	 * 
	 * @param term  - the first part of an expr
	 * @param addop - second part of a token
	 * @param expr  - the second (optional, not sure why there isn't just an
	 *              overloaded constructor??)
	 */
	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
		this.term = term;
		this.addop = addop;
		this.expr = expr;
	}

	

    /**
	 * puts together an expr's structure. if this is a term, we set it up as part of
	 * the expr structure, otherwise we append the incoming expr to this one
	 * 
	 * @param expr we are about to reconstruct.
	 */
	public void append(NodeExpr expr) {
		if (this.expr == null) {
			this.addop = expr.addop;
			this.expr = expr;
			expr.addop = null;
		} else
			this.expr.append(expr);
	}

	/**
	 * Evaluate meaning of our expr, if this expr is just a term, eval that, or eval this expr and the term attached.
	 */
	public double eval(Environment env) throws EvalException {
		return expr == null
				? term.eval(env)
				: addop.op(expr.eval(env), term.eval(env));

	}

	/**
	 * create the C code to generate this expr
	 */
	public String code() {
		return (expr == null ? "" : expr.code() + addop.code()) + term.code();
	}

}