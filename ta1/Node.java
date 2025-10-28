// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated,
// and/or code()-generated.

public abstract class Node {

	protected int pos=0;

	/**
	 * If we hit this method, there's a problem, we should never be evaluating an abstract class
	 * @param env - the environment where we encountered this
	 * @return nothing
	 * @throws EvalException -- impossible to evaluate
	 */
	public int eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

	public String code() { return ""; }

}
