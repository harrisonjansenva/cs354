public class EvalException extends Exception {

	private int pos;
	private String msg;
/**
 * Exception to be thrown if we try to access a binding that does not exist in our environment.
 * @param pos - position of argument that does not currently have a binding
 * @param msg - message to display to user
 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}
	@Override
	public String toString() {
		return "eval error"
			+", pos="+pos
			+", "+msg;
	}

}
