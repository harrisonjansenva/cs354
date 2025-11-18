/**
 * Class that holds an if statement. It contains a boolean expression and a statement to execute if the expression is true.
 * @author harrisonjansenvanbeek
 */
public class NodeStmtIf extends NodeStmtVal {

    protected NodeBoolExpr boolExpr;
    protected NodeStmt stmt;
/**
 * Constructor for the if statement
 * @param boolExpr -- the boolean expression to evaluate
 * @param stmt -- the statement to execute if the expression is true
 */
    public NodeStmtIf(NodeBoolExpr boolExpr, NodeStmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }
/**
 * {@inheritDoc}
 */
    @Override
    public double eval(Environment env) throws EvalException {
        return boolExpr.eval(env) == 1.0 ? stmt.eval(env): 0.0;
    }
/**
 * {@inheritDoc}
 */
    @Override
    public String code() {
        return "if (" + boolExpr.code() + ") {\n" + stmt.code() + "\n}";
    }
}
