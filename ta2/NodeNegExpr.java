/**
 * Class to just hold the minus sign to invert value of expr
 */
public class NodeNegExpr extends NodeFactExpr{

    public NodeNegExpr(NodeExpr expr) {
        super(expr);
    }


    @Override
    public double eval(Environment env) throws EvalException {
        return - super.eval(env);
    }
/**
 * return minus sign to correctly flip value of expr being evaluated
 */
    public String code() {
        return "-(" + super.code() + ")";
    }

    
}
