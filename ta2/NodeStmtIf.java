public class NodeStmtIf extends NodeStmtVal {

    protected NodeBoolExpr boolExpr;
    protected NodeStmt stmt;

    public NodeStmtIf(NodeBoolExpr boolExpr, NodeStmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        return boolExpr.eval(env) == 1.0 ? stmt.eval(env): 0.0;
    }

    @Override
    public String code() {
        return "if (" + boolExpr.code() + ") {\n" + stmt.code() + "\n}";
    }
}
