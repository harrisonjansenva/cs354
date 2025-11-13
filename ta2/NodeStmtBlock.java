import java.util.ArrayList;

public class NodeStmtBlock extends NodeStmtVal {


    private ArrayList<NodeStmt> stmts;
    
    public NodeStmtBlock(ArrayList<NodeStmt> stmts) {
        this.stmts = stmts;

    }

    @Override
    public double eval(Environment env) throws EvalException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eval'");
    }

    @Override
    public String code() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'code'");
    }
    
}
