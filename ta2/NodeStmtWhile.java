/**
 * Class that holds a while statement. It contains a boolean expression and a statement to execute while the expression is true.
 * @author harrisonjansenvanbeek
 */
public class NodeStmtWhile extends NodeStmtVal {

    private NodeBoolExpr boolExpr;
    private NodeStmt stmt;
/**
 * Constructor for the while statement
 * @param boolExpr -- the boolean expression to evaluate
 * @param stmt -- the statement to execute while the expression is true
 */
    public NodeStmtWhile(NodeBoolExpr boolExpr, NodeStmt stmt) {
       this.boolExpr = boolExpr;
       this.stmt = stmt;
    }
/**
 * Evaluate the while statement by repeatedly evaluating the boolean expression and executing the statement while it is true
 * @return the value of the last executed statement, or 0.0 if the loop never executes
 */
    @Override
    public double eval(Environment env) throws EvalException {
        double ret = 0.0;
       while (boolExpr.eval(env) == 1.0){
        ret = stmt.eval(env);
       }
       return ret;
    }

    @Override
    public String code() {
       StringBuilder sb = new StringBuilder();
       sb.append("while ").append(boolExpr.code()).append(" {\n");
       sb.append(stmt.code()).append("\n}\n");

       return sb.toString();
    }
    
}
