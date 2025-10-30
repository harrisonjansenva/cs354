/**
 * Class to hold an identifier
 */
public class NodeFactId extends NodeFact {

	private String id;
/**
 * Constructor to hold an identifier
 * @param pos in string this occurs at
 * @param id: actual value of identifier
 */
	public NodeFactId(int pos, String id) {
		this.pos=pos;
		this.id=id;
	}


/**
 * Constructor to pull value associated with binding to ID from our environment
 */
	public double eval(Environment env) throws EvalException {
		return env.get(pos,id);
	}
/**
 * generate C code to create id
 */
	public String code() { return id; }

}
