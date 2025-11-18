
import java.util.ArrayList;

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

	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(), "<");

		} else if (curr().equals(new Token( "<="))) {
			match("<=");
			return new NodeRelop(pos(), "<=");

		} else if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(), ">");

		} else if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(), ">=");

		} else if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(), "<>");

		} else if (curr().equals(new Token("=="))) {
			match("==");
			return new NodeRelop(pos(), "==");

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
	private NodeStmtRd parseRdStmt() throws SyntaxException {
		Token id = curr();
		
		match("id");

		return new NodeStmtRd(pos(), id.lex());
		
	}
       

	private NodeStmtWr parseWrStmt() throws SyntaxException {
		NodeExpr exprToWrite = parseExpr();
		return new NodeStmtWr(pos(), exprToWrite);
	}

	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop op = parseRelop();
		NodeExpr expr2 = parseExpr();
		
		return new NodeBoolExpr(expr1, op, expr2);
	}

	private NodeStmtWhile parseWhileStmt() throws SyntaxException {
		NodeBoolExpr boolExpr = parseBoolExpr();
		match("do");
		NodeStmt stmt = parseStmt();

		return new NodeStmtWhile(boolExpr, stmt);
		
	}

	private NodeStmtIf parseIfStmt() throws SyntaxException {
		NodeBoolExpr boolExpr = parseBoolExpr();
		match("then");
		NodeStmt stmt = parseStmt();
		if (curr().equals(new Token("else"))) {
			match("else");
			NodeStmt elseBlock = parseStmt();
			return new NodeStmtIfElse(boolExpr, stmt, elseBlock);
		}
		return new NodeStmtIf(boolExpr, stmt);
	}

	private NodeBlock parseStmtBlock() throws SyntaxException {
		ArrayList<NodeStmt> blockStmts = new ArrayList<>();
		while (curr() != new Token("end")) {
			NodeStmt stmt = parseStmt();
			blockStmts.add(stmt);
		}
		match("end");
		return new NodeBlock(blockStmts);
	}

	

/** 
 * Parse out the statement into an assignment and ensure we have a semicolon at the end
*/
	private NodeStmt parseStmt() throws SyntaxException {
		// NodeAssn assn = parseAssn();
		// match(";");
		// NodeStmtAssn stmt = new NodeStmtAssn(assn);
		// return stmt;
		NodeStmtVal stmt; 

		if (curr().equals( new Token("rd"))) {
			match("rd");
			stmt = parseRdStmt();
		} else if (curr().equals( new Token("wr"))) {
			match("wr");
			stmt = parseWrStmt();
		} else if (curr().equals(new Token("if"))) {
			match("if");
			stmt = parseIfStmt();
		} else if (curr().equals(new Token("while"))) {
			match("while");
			stmt = parseWhileStmt();
		} else if (curr().equals( new Token("begin"))) {
			match("begin");
			stmt = parseBlock();
			match("end");
		} else {
			stmt = parseAssn();
		}
		return new NodeStmt(stmt);
	}

	private NodeBlock parseBlock() throws SyntaxException {
		ArrayList<NodeStmt> stmnts = new ArrayList<>();
		while (!scanner.done()) {
			NodeStmt stmnt = parseStmt();
			stmnts.add(stmnt);
			if (curr().equals(new Token("EOF")) || curr().equals(new Token("end"))) {
				break;
			}
			match(";");

		}
		return new NodeBlock(stmnts);
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
		NodeBlock prog = parseBlock();
		match("EOF");
		return prog;
	}

    

}
