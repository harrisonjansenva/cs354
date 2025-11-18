public class NodeStmtWhile extends NodeStmtVal {

    private NodeBoolExpr boolExpr;
    private NodeStmt stmt;

    public NodeStmtWhile(NodeBoolExpr boolExpr, NodeStmt stmt) {
       this.boolExpr = boolExpr;
       this.stmt = stmt;
    }

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
