public class NodeStmtIfElse extends NodeStmtIf {

    private NodeStmt elseBlock;

    public NodeStmtIfElse(NodeBoolExpr boolExpr, NodeStmt stmt, NodeStmt elseBlock) {
        super(boolExpr, stmt);
        this.elseBlock = elseBlock;
    }

    @Override
    public double eval(Environment env) throws EvalException {
       return boolExpr.eval(env) == 1.0 ? stmt.eval(env) : elseBlock.eval(env);
    }

    @Override
    public String code() {
        StringBuilder sb = new StringBuilder();
        sb.append("if (").append(boolExpr.code()).append(") {\n");
        sb.append(stmt);
        sb.append("}\n else {").append(elseBlock.code()).append('}');

        return sb.toString();
    }
    
}
