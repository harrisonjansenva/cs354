/**
 * Class that holds a write statement. it holds the expr that we want to write
 */
public class NodeStmtWr extends NodeStmtVal {

    private int pos;
    private NodeExpr exprToWrite;
    /**
     * Constructor for the write statement
     * @param pos -- position in the source code
     * @param exprToWrite -- the expression whose value we want to write
     */
    public NodeStmtWr(int pos, NodeExpr exprToWrite) {
        this.pos = pos;
        this.exprToWrite = exprToWrite;
        
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public double eval(Environment env) throws EvalException {
       double i = exprToWrite.eval(env);
		if (i == Math.floor(i))
			System.out.printf("%.0f%n", i);
		else
			System.out.printf("%g%n", i);
		return i;
    }

  /**
   * {@inheritDoc}
   */
   @Override
public String code() {
    return "printf(\"%g\\n\", (double)(" + exprToWrite.code() + "));";
}
    
}
