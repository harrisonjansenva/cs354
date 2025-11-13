/**
 * Class that holds a statement binding
 */
public abstract class NodeStmtVal extends Node {



/**
 * Evaluate the binding held at this statement
 */
	public abstract double eval(Environment env) throws EvalException;

	/**
	 * generate code to create proper binding semantics
	 */
	public abstract String code();

}
