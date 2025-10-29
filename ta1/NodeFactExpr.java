public class NodeFactExpr extends NodeFact {

	private NodeExpr expr;
	private NodeNegExpr negExpr;

	public NodeFactExpr(NodeNegExpr negExpr, NodeExpr expr) {
		this.expr=expr;
		this.negExpr = negExpr;
	}

	public int eval(Environment env) throws EvalException {
		return negExpr == null ? expr.eval(env) : - expr.eval(env);
	}

	public String code() { return (negExpr == null ? "": negExpr.code()) + "(" + expr.code() + ")"; }

}
