/**
 * Class that holds a read statement. It contains the position and lexeme of the variable to read into.
 * @author harrisonjansenvanbeek
 */
import java.util.Scanner;
public class NodeStmtRd extends NodeStmtVal {
    
    private int pos;
    private String lex;
/**
 * Constructor for the read statement
 * @param pos -- position in the source code
 * @param lex -- the lexeme of the variable to read into
 */
    public NodeStmtRd(int pos, String lex) {
        this.pos = pos;
        this.lex = lex;
    }
/**
 * {@inheritDoc}
 */
    @Override
    public double eval(Environment env) throws EvalException {
        Scanner scnr = new Scanner(System.in);
        double input = scnr.nextDouble();
        return env.put(lex, input);
        
    }
/**
 * {@inheritDoc}
 */

    @Override
    public String code() {
       StringBuilder sb = new StringBuilder();
       sb.append("scanf(%f, &)").append(lex).append(");");
       return sb.toString();
    }
    
}
