public class NodeRelop extends Node {
    private String relop;
    private int pos;


    public NodeRelop(int pos, String relop) {
        this.pos = pos;
        this.relop = relop;
    }


    public double op(NodeExpr expr1, NodeExpr expr2, Environment env) throws EvalException {
        switch(relop) {
            case "<":
            return expr1.eval(env) < expr2.eval(env) ? 1.0: 0.0;

            case "<=" :
            return expr1.eval(env) <= expr2.eval(env) ? 1.0: 0.0;

            case ">":
            return expr1.eval(env) > expr2.eval(env) ? 1.0: 0.0;

            case ">=":
            return expr1.eval(env) >= expr2.eval(env) ? 1.0: 0.0;

            case "<>":
            return expr1.eval(env) != expr2.eval(env) ? 1.0: 0.0;

            case "==":
            return expr1.eval(env) == expr2.eval(env) ? 1.0: 0.0;

            default:
            throw new EvalException(pos, "Bogus relop: " + relop);
        }

    }
    
    @Override
    public String code() {return relop;}
    
    
}
