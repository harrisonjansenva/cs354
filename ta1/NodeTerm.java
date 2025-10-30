/**
 * Class to hold term binding
 */
public class NodeTerm extends Node {

	private NodeFact fact;
	private NodeMulop mulop;
	private NodeTerm term;
/**
 * Constructor to build term binding:
 * @param fact - required, can hold any type of fact
 * @param mulop - optional, performs mult
 * @param term - optional, can hold another term parsed in any way
 */
	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}
/**
 * if this term is just a fact, set it up to be evaluated, or we append this to the next term so we can form a chain of evaluation.
 * @param term
 */
	public void append(NodeTerm term) {
		if (this.term==null) {
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} else
			this.term.append(term);
	}
/**
 * if this term is null (we have a fact), we just evaluate it, otherwise we multiply the two terms together as it is set up.
 */
	public double eval(Environment env) throws EvalException {
		return term==null
			? fact.eval(env)
			: mulop.op(term.eval(env),fact.eval(env));
	}
/**
 * Return the code to properly create this expr in C
 */
	public String code() {
		return (term==null ? "" : term.code()+mulop.code())+fact.code();
	}

}
