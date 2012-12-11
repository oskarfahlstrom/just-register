import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String ALLOWED_CHARACTERS = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@"
			+ "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(ALLOWED_CHARACTERS);
	}

	/**
	 * Validate String s with regular expression
	 * 
	 * @param s
	 * @return true if String s is valid, false otherwise
	 */
	public boolean validate(final String s) {

		matcher = pattern.matcher(s);
		return matcher.matches();
	}
}
