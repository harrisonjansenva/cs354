/**
 * Class that holds a statement binding
 */
public class NodeStmt extends Node {

	private NodeStmtVal stmt;
/**
 * constructor to hold binding to an assignment
 * @param assn bound
 */
	public NodeStmt(NodeStmtVal stmt) {
		this.stmt = stmt;
	}
/**
 * Evaluate the binding held at this statement
 */
	public double eval(Environment env) throws EvalException {
		return stmt.eval(env);
	}

	/**
	 * generate code to create proper binding semantics
	 */
	public String code() { return stmt.code(); }

}
