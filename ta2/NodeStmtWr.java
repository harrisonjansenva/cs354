public class NodeStmtWr extends NodeStmtVal {

    private int pos;
    private NodeExpr exprToWrite;
    public NodeStmtWr(int pos, NodeExpr exprToWrite) {
        this.pos = pos;
        this.exprToWrite = exprToWrite;
        
    }

    @Override
    public double eval(Environment env) throws EvalException {
       double i = exprToWrite.eval(env);
		if (i == Math.floor(i))
			System.out.printf("%.0f%n", i);
		else
			System.out.printf("%g%n", i);
		return i;
    }

   @Override
public String code() {
    return "printf(\"%g\\n\", (double)(" + exprToWrite.code() + "));";
}
    
}
