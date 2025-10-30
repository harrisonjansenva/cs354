// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

	private String token;
	private String lexeme;

	public Token(String token, String lexeme) {
		this.token=token;
		this.lexeme=lexeme;
	}

	public Token(String token) {
		this(token,token);
	}
/**
 * @return the token being evaluated
 */
	public String tok() { return token; }
/**
 * 
 * @return the lexical value of the token we are evaluating
 */
	public String lex() { return lexeme; }
/**
 * 
 * @param t to ensure the token we are evaluating is the one we are looking at
 * @return true if the same token
 */
	public boolean equals(Token t) {
		return token.equals(t.token);
	}

	public String toString() {
		return "<"+tok()+","+lex()+">";
	}

}
