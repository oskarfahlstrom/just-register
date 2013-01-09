import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Just a little demo of our account register.
 * 
 * Created on Dec 10, 2012, 5:24:40 PM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
@SuppressWarnings("serial")
public class Demo extends JFrame {

	public Demo() {
		/*
		 * In our demo the sign up button is enabled when a valid email, a
		 * password longer than 6 characters has been entered and the check box
		 * has been checked.
		 */
		RegistrationComplete rc = new RegistrationComplete() {

			@Override
			public boolean signUpEnabled() {
				if (AccountReg.emailIsValid && AccountReg.passwordIsValid
						&& AccountReg.checkBoxIsChecked)
					return true;
				return false;
			}
		};

		/*
		 * We create a new email validator that takes in a string and matches it
		 * to our set pattern for what an email adress should look like. Returns
		 * true if the string matches, false if it doesn't.
		 */
		EmailValidator ev = new EmailValidator() {

			@Override
			public boolean validate(String s) {
				Pattern pattern;
				Matcher matcher;

				String ALLOWED_FORMAT = ("^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$");
				// Simply edit the pattern if you only want to allow more
				// specific email adresses. For example if it has to be a
				// @student.liu.se adress for something University-related.

				pattern = Pattern.compile(ALLOWED_FORMAT);

				matcher = pattern.matcher(s);
				return matcher.matches();
			}
		};

		JScrollPane scroll = new JScrollPane(new AccountReg(ev, rc));

		// WINDOW SETTINGS //
		add(scroll);
		setTitle("Account Register");
		setVisible(true);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * The standard main method for thread safety.
	 */
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demo();
			}
		});
	}
}