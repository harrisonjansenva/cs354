import java.util.ArrayList;
public class NodeBlock extends Node {

    private ArrayList<NodeStmtVal> stmts;


    public NodeBlock(ArrayList<NodeStmtVal> stmts) {
        this.stmts = stmts;
    }

    public void add (NodeStmtVal stmt) {
        stmts.addLast(stmt);
    }

    @Override
    public double eval(Environment env) throws EvalException {

        double lastStmtReturn = 0;
        for (NodeStmtVal stmt : stmts) {
            lastStmtReturn = stmt.eval(env);
        }
        return lastStmtReturn;


    }

    
    
}
