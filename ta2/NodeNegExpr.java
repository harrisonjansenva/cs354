/**
 * Class to just hold the minus sign to invert value of expr
 */
public class NodeNegExpr extends Node{

    private String optMinus;
    private int pos;
    public NodeNegExpr(int pos, String optMinus) {
        this.pos = pos;
        this.optMinus = optMinus;
    }
/**
 * return minus sign to correctly flip value of expr being evaluated
 */
    public String code() {
        return "-";
    }

    
}
