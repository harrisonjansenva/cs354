/**
 * Class that holds a statement binding
 */
public abstract class NodeStmtVal extends Node {


 /**
     * Evaluate the write statement by evaluating the expression and printing it
     * to standard output
     * @return the value that was written
     */
	public abstract double eval(Environment env) throws EvalException;

	/**
 * Generate the C code for the while statement
 * @return the string containing the C code
 */
	public abstract String code();

}
