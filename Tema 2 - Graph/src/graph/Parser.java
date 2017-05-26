package graph;

public class Parser {
	/**
	 * From a string, it returns a number equal to the number formed with all
	 * the digits from the string
	 * 
	 * @param s
	 *            string to be parsed
	 * @return integer equal to the number formed with the digits from string
	 */
	public static int getNumber(String s) {
		// remove everything except digits
		s = s.replaceAll("\\D+", "");
		// get integer value from string
		int number = Integer.parseInt(s);
		return number;
	}

	/**
	 * The string has the following form: <Nume> word </Nume>
	 * 
	 * @param s
	 *            string to be parsed
	 * @return word between <Nume> and </Nume>
	 */
	public static String getName(String s) {
		// Remove all blank spaces
		s = s.replaceAll("\\s+", "");
		// Remove <Nume>
		s = s.replace("<Nume>", "");
		// Remove </Nume>
		s = s.replace("</Nume>", "");

		return s;

	}
}
