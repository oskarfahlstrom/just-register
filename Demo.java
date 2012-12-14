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
		RegistrationComplete rc = new RegistrationComplete() {

			@Override
			public boolean signUpEnabled() {
				if (AccountReg.emailIsValid && AccountReg.passwordIsValid
						&& AccountReg.checkBoxIsChecked)
					return true;
				return false;
			}
		};

		JScrollPane scroll = new JScrollPane(new AccountReg(rc));

		// JScrollPane scroll = new JScrollPane(
		// new AccountRegisterBase.AccountReg().getContentPane());

		add(scroll);
		setTitle("Account Register");
		setVisible(true);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Demo();
			}
		});
	}
}
