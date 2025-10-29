public class NodeNegExpr extends Node{

    private String optMinus;
    private int pos;
    public NodeNegExpr(int pos, String optMinus) {
        this.pos = pos;
        this.optMinus = optMinus;
    }

    public String code() {
        return "-";
    }

    
}
