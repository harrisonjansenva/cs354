import java.util.ArrayList;
public class NodeBlock extends NodeStmtVal {

    private ArrayList<NodeStmt> stmts;


    public NodeBlock(ArrayList<NodeStmt> stmts) {
        this.stmts = stmts;
    }

    public void add (NodeStmt stmt) {
        stmts.addLast(stmt);
    }

    @Override
    public double eval(Environment env) throws EvalException {

        double lastStmtReturn = 0;
        for (NodeStmt stmt : stmts) {
            lastStmtReturn = stmt.eval(env);
        }
        return lastStmtReturn;


    }
    @Override
    public String code() {

        StringBuilder sb = new StringBuilder();
        for (NodeStmt stmt: stmts) {
            sb.append(stmt.code());
        }

        return sb.toString();

    }

    
    
}
