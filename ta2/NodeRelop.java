/**
 * Class that holds a relational operator node.
 * @author harrisonjansenvanbeek
 */
public class NodeRelop extends Node {
    private String relop;
    private int pos;

/**
 * Constructor for the relational operator
 * @param pos
 * @param relop
 */
    public NodeRelop(int pos, String relop) {
        this.pos = pos;
        this.relop = relop;
    }

/**
 * method to evaluate the relational operation
 * @param expr1 -- first expression
 * @param expr2 -- second expression
 * @param env -- the environment
 * @return -- 1.0 if true, 0.0 if false
 * @throws EvalException
 */
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
    /**
     * {@inheritDoc}
     */
    @Override
    public String code() {return relop;}
    
    
}
