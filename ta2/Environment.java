// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;

public class Environment {

	private HashMap<String, Double> env = new HashMap<>(); 
/**
 * Add a variable to our var lookup table
 * @param var name of var to add
 * @param val - value of the variable to be output
 * @return the variable, indicating sucessful put
 */
	public double put(String var, double val) {
		env.put(var, val);
		return val;
	}
/**
 * Gets the value of the variable named with var
 * @param pos - position in program we are evaluating
 * @param var - name of token we are identifying
 * @return value of the token or var we are looking for
 * @throws EvalException if that token is not in our var lookup table
 */
	public double get(int pos, String var) throws EvalException {
		if (!env.containsKey(var)) {
			throw new EvalException(pos, "Value does not exist in var lookup table");
		}
		else {
			return env.get(var);
		}
	}
/**
 * generates the C code of our lookup table
 * @return a string representing the code we need to execute
 */
	public String toC() {
		String s = "";
		String sep = " ";
		for (String v : env.keySet()) {
			s += sep + v;
			sep = ",";
		}
		return s == "" ? "" : "double" + s + ";\nx=0;x=x;\n";
	}


}
