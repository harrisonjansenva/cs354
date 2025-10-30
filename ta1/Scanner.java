// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

/**
 * The Scanner class is used to tokenize an input source program. It identifies tokens
 * such as identifiers, numbers, keywords, operators, and manages white spaces and comments.
 * The Scanner processes the input character by character and groups them into meaningful
 * lexemes based on predefined sets such as digits, letters, operators, and keywords.
 */
public class Scanner {

	private String program;		// source program being interpreted
	private int pos;			// index of next char in program
	private Token token;		// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();
	private String commentStart = "*/";
	private String commentFinish = "/*";

	// initializers for previous sets

	/**
	 * Populates the provided set with string representations of characters
	 * ranging from the given lower bound to the given upper bound (inclusive).
	 *
	 * @param s the set to populate with characters
	 * @param lo the lower bound character (inclusive)
	 * @param hi the upper bound character (inclusive)
	 */
	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}

	/**
	 * Populates the provided set with common whitespace characters including a space, a newline, and a tab.
	 *
	 * @param s the set to populate with whitespace characters
	 */
	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	/**
	 * Initializes the provided set with string representations of digit characters
	 * ranging from '0' to '9' (inclusive).
	 *
	 * @param s the set to populate with digit characters
	 */
	private void initDigits(Set<String> s) {
		fill(s,'0','9');
	}

	/**
	 * Initializes the provided set with string representations of all uppercase
	 * and lowercase alphabetic characters ('A' to 'Z' and 'a' to 'z').
	 *
	 * @param s the set to populate with alphabetic characters
	 */
	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	/**
	 * Populates the provided set with string representations of all characters
	 * categorized as letters or digits.
	 *
	 * @param s the set to be populated with letters and digit characters
	 */
	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	/**
	 * Initializes the provided set with a predefined set of operator symbols.
	 *
	 * @param s the set to populate with operator symbols such as '=', '+', '-', '*', '/', '(', ')', and ';'
	 */
	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
	}

	/**
	 * Initializes the set of keywords for the scanner.
	 *
	 * @param s the set to initialize with keywords
	 */
	private void initKeywords(Set<String> s) {
		this.keywords = s;
	}

	// constructor:
	//     - squirrel-away source program
	//     - initialize sets
	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
	}

	// handy string-processing methods

	public boolean done() {
		return pos>=program.length();
	}

	private void many(Set<String> s) {
		while (!done()&&s.contains(program.charAt(pos)+""))
			pos++;
	}

	// This method advances the scanner,
	// until the current input character
	// is just after a sequence of one or more
	// of a particular character.
	// Arguments:
	//     c = the character to search for
	// Members:
	//     program = the scanner's input
	//     pos = index of current input character
	private void past(char c) {
		while (!done()&&c!=program.charAt(pos))
			pos++;
		if (!done()&&c==program.charAt(pos))
			pos++;
	}

	// scan various kinds of lexeme

	/**
	 * Scans the next number token from the program and updates the token instance
	 * to represent this new token.
	 * Modifies:
	 * - Updates the `token` field with a new `Token` object representing the number.
	 * - Advances the `pos` field to the character position following the identified token.
	 */
	private void nextNumber() {
		int old=pos;
		many(digits);
		if (program.charAt(pos) == '.') {
			pos++;
		}
		many(digits);
		token=new Token("num",program.substring(old,pos));
	}

	/**
	 * Scans the next keyword or identifier token from the source program and updates the `token` field
	 * to represent this new token. The method determines whether the scanned lexeme is a recognized
	 * keyword from the set of `keywords` or an identifier.
	 * Modifies:
	 * - Updates the `token` field with a new `Token` object representing the keyword or identifier.
	 * - Advances the `pos` field to the next position after the identified token.
	 */
	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}
/**
 * Creates a token of the next operator we are using
 */
	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (!done()) {
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}
/**
 * if comments are present, just advances position in program
 * 
 */
	private void checkForComments() {
		if(pos + 1 < program.length() && program.charAt(pos) == '/' && program.charAt(pos + 1) == '*') {
			pos += 2;
			while (pos + 1 < program.length())  {
				if (program.charAt(pos) == '*' && program.charAt(pos + 1) == '/') {
					pos += 2;
					break;
				} else {
					pos ++;
				}
				
		}
			// throw new SyntaxException(pos, new Token("/*"), new Token("eof"));
		}
	
	}


	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	public boolean next() throws SyntaxException {
		many(whitespace);
		checkForComments();
		many(whitespace);
		if (done()) {
			token=new Token("EOF");
			return false;
		}
		
		String c=program.charAt(pos)+"";
		if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else {
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}
/**
 * 
 * @return the current token we are evaluating
 * @throws SyntaxException if the token is null
 */
	public Token curr() throws SyntaxException {
		if (token==null)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		return token;
	}
/**
 * 
 * @return current position in program we are evaluating
 */
	public int pos() {
		return pos;
	}

	// for unit testing
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}
