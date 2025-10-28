import java.io.*;
/**
 * This class takes our Java code an turns it into a C file that we can then excecute lightning fast!
 * takes and writes all the files we need after eval
 * @author: A Sadist
 * Date: Sun Oct 26 17:49:38 MDT 2025
 */
public class Code {
/**
 * File Header for C files
 */
	private final String[] prologue={
		"#include <stdio.h>",
		"int main() {",
	};

	/**
	 * Ending for C files, return and ending bracket
	 */
	private final String[] epilogue={
		"return 0;",
		"}",
	};
/**
 * Takes and writes all variables in env and outputs result. 
 * @param code to generate
 * @param env which we have all our variables stored
 */
	public Code(String code, Environment env) {
		String fn=System.getenv("Code");
		if (fn==null)
			return;
		try {
			BufferedWriter f=new BufferedWriter(new FileWriter(fn+".c"));
			for (String s: prologue)
				f.write(s+"\n");
			f.write(env.toC());
			f.write(code);
			for (String s: epilogue)
				f.write(s+"\n");
			f.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
