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

	private HashMap<String, Integer> env = new HashMap<>(); 

	public int put(String var, int val) {
		env.put(var, val);
		return val;
	}

	public int get(int pos, String var) throws EvalException {
		if (!env.containsKey(var)) {
			throw new EvalException(pos, "Value does not exist in var lookup table");
		}
		else {
			return env.get(var);
		}
	}

	public String toC() {
		String s = "";
		String sep = " ";
		String equal = "=";
		String newLine = ";\n";
		for (String v : env.keySet()) {
			s += "int";
			s += sep + v;
			s += sep + equal;
			s += sep + env.get(v);
			s += newLine;

		}
		return s;
	}

}
