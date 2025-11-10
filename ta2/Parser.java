// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

public class Parser {

	private Scanner scanner;
/**
 * Ensure the token we are parsing it what is supposed to be
 * @param s - the token to be evaluated
 * @throws SyntaxException
 */
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}
/**
 * Find the current token we are evaluating
 * @return current token
 * @throws SyntaxException
 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}
/**
 * Return position in the program we are at
 * @return the posititon
 */
	private int pos() {
		return scanner.pos();
	}
/**
 * Determines which multiplicative op we want to use
 * @return the NodeMulOp we are using
 * @throws SyntaxException
 */
	private NodeMulop parseMulop() throws SyntaxException {
		
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}
/**
 * Determine if we are adding or subtracting
 * @return the op we are actually using
 * @throws SyntaxException
 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}
	/** 
	 * Parse to see if value of expression should be flipped to negative
	*/
	private NodeNegExpr parseNegExpr() throws SyntaxException {
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeNegExpr(pos(), "-");
		}
		return null;
	}
/**
 * Parse a fact, where that be an identifier, a number, or another expression in parenthesis
 * @return the parsed fact
 * @throws SyntaxException
 */
	private NodeFact parseFact() throws SyntaxException {
		NodeNegExpr negExpr = parseNegExpr();
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(negExpr, expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}
/**
 * Parse a term, whether that be a fact, multop, and term, or just a fact
 * @return the parsed term
 * @throws SyntaxException
 */
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}
/**
 * Parse an expression into either a term, addOp, and expression, or just a term
 * @return the parsed expression
 * @throws SyntaxException
 */
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	/**
	 * Parse an assignment binding (an identifier, an equals sign, and an expression)
	 * @return the parsed assignment
	 * @throws SyntaxException
	 */
	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}
/** 
 * Parse out the statement into an assignment and ensure we have a semicolon at the end
*/
	private NodeStmt parseStmt() throws SyntaxException {
		NodeAssn assn = parseAssn();
		match(";");
		NodeStmt stmt = new NodeStmt(assn);
		return stmt;
	}
 /**
  * Return the parsed out program
  * @param program that we want to parse
  * @return all the parsedout pieces
  * @throws SyntaxException
  */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		NodeStmt stmt = parseStmt();
		match("EOF");
		return stmt;
	}

}
