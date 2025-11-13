public class NodeBoolExpr extends NodeStmtVal {

    private NodeExpr expr1;
    private NodeExpr expr2;
    private NodeRelop relop;

    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.relop = relop;

    }

    @Override
    public double eval(Environment env) throws EvalException {
       return relop.op(expr1, expr2, env);
    }

    @Override
    public String code() {
       return "(" + expr1 + " " + relop + " " + expr2 + ")";
    }
    
}
