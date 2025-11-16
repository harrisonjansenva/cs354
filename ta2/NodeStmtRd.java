import java.util.Scanner;
public class NodeStmtRd extends NodeStmtVal {
    
    private int pos;
    private String lex;

    public NodeStmtRd(int pos, String lex) {
        this.pos = pos;
        this.lex = lex;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        Scanner scnr = new Scanner(System.in);
        double input = scnr.nextDouble();
        scnr.close();
        return env.put(lex, input);
        
    }

    @Override
    public String code() {
       StringBuilder sb = new StringBuilder();
       sb.append("scanf(%f, &)").append(lex).append(");");
       return sb.toString();
    }
    
}
