/**
 * Class that holds an if-else statement. It contains a boolean expression, a statement for the if block,
 * and a statement for the else block.
 * @author harrisonjansenvanbeek
 */
public class NodeStmtIfElse extends NodeStmtIf {

    private NodeStmt elseBlock;
/**
 * Constructor for the if-else statement
 * @param boolExpr -- the boolean expression to evaluate
 * @param stmt -- the statement to execute if the expression is true
 * @param elseBlock -- the statement to execute if the expression is false
 */
    public NodeStmtIfElse(NodeBoolExpr boolExpr, NodeStmt stmt, NodeStmt elseBlock) {
        super(boolExpr, stmt);
        this.elseBlock = elseBlock;
    }
/**
 * {@inheritDoc}
 */
    @Override
    public double eval(Environment env) throws EvalException {
       return boolExpr.eval(env) == 1.0 ? stmt.eval(env) : elseBlock.eval(env);
    }
/**
 * {@inheritDoc}
 */
    @Override
    public String code() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(boolExpr.code()).append(") {\n");
        sb.append(stmt);
        sb.append("}\n else {").append(elseBlock.code()).append('}');

        return sb.toString();
    }
    
}
