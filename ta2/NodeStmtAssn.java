/**
 * Class that holds a statement binding
 */
public class NodeStmtAssn extends Node {

	private NodeAssn assn;
/**
 * constructor to hold binding to an assignment
 * @param assn bound
 */
	public NodeStmtAssn(NodeAssn assn) {
		this.assn=assn;
	}
/**
 * Evaluate the binding held at this statement
 */
	public double eval(Environment env) throws EvalException {
		return assn.eval(env);
	}

	/**
	 * generate code to create proper binding semantics
	 */
	public String code() { return assn.code(); }

}
