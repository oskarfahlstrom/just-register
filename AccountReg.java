import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Created on Dec 7, 2012, 7:12:04 PM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
@SuppressWarnings("serial")
public class AccountReg extends JComponent {
	// CREATE COMPONENTS //
	static boolean emailIsValid;
	static boolean passwordIsValid;
	static boolean checkBoxIsChecked;

	EmailValidator ev;
	RegistrationComplete rc;

	static JLabel email, password, firstName, lastName, emailNotes,
			passwordNotes, emailImage, passwordImage;
	static JTextField emailField, firstNameField, lastNameField;
	static JButton signUp;
	static JCheckBox checkBox;
	static ImageIcon accepted, required;
	static JPasswordField passwordField;

	/**
	 * A simple program that lets you register an account or sign up for a
	 * service.
	 * 
	 * @param ev
	 * @param rc
	 */
	public AccountReg(EmailValidator ev, RegistrationComplete rc) {
		// INITIALIZE COMPONENTS //
		this.ev = ev;
		this.rc = rc;

		email = new JLabel("Email Adress:");
		password = new JLabel("Password:");
		firstName = new JLabel("First Name:");
		lastName = new JLabel("Last Name:");

		accepted = new ImageIcon(getClass().getResource("/Images/Valid.png"));
		required = new ImageIcon(getClass().getResource("/Images/Invalid.png"));

		emailImage = new JLabel(required);
		passwordImage = new JLabel(required);
		emailNotes = new JLabel("email confirmation required");
		passwordNotes = new JLabel("at least 6 characters");

		emailField = new JTextField();
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*'); // Allow masking of the written text.
		firstNameField = new JTextField();
		lastNameField = new JTextField();

		checkBox = new JCheckBox("I accept all your terms.");
		signUp = new JButton("Sign up");
		signUp.setEnabled(false);

		// FONTS AND LOOKS //
		Font cursiveFont = new Font("Arial", Font.ITALIC, 12);

		emailNotes.setForeground(Color.GRAY);
		emailNotes.setFont(cursiveFont);
		passwordNotes.setForeground(Color.GRAY);
		passwordNotes.setFont(cursiveFont);

		// Set the default size of our text fields. Using linkSize() later on we
		// set all text fields to this size.
		emailField.setPreferredSize(new Dimension(250, 20));

		// Remove unwanted default border of the check box.
		checkBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		// ADD LISTENERS //
		emailFieldListener(emailField);
		passwordFieldListener(passwordField);
		checkBoxListener(checkBox);
		signUpListener(signUp);

		// Create the layout.
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layoutManager(layout);
	}

	/**
	 * Set up the listener for the email input field.
	 * 
	 * @param emailField
	 */
	public void emailFieldListener(final JTextField emailField) {
		emailField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (ev.validate(emailField.getText())) {
					emailIsValid = true;
					emailImage.setIcon(accepted);
					if (rc.signUpEnabled())
						signUp.setEnabled(true);
					return;
				}
				emailIsValid = false;
				emailImage.setIcon(required);
				signUp.setEnabled(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	/**
	 * Set up the listener for the password input field.
	 * 
	 * @param passwordField
	 */
	public void passwordFieldListener(final JPasswordField passwordField) {
		passwordField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				if (passwordField.getText().length() >= 6) {
					// getPassword() will not work in this case since we want to
					// check the actual length of the password.
					passwordIsValid = true;
					passwordImage.setIcon(accepted);
					if (rc.signUpEnabled())
						signUp.setEnabled(true);
					return;
				}
				passwordIsValid = false;
				passwordImage.setIcon(required);
				signUp.setEnabled(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	/**
	 * Set up the listener to keep track of the check box.
	 * 
	 * @param checkBox
	 */
	public void checkBoxListener(final JCheckBox checkBox) {
		checkBox.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (checkBox.isSelected()) {
					checkBoxIsChecked = true;
					if (rc.signUpEnabled()) {
						signUp.setEnabled(true);
						return;
					}
					signUp.setEnabled(false);
					return;
				}
				checkBoxIsChecked = false;
				signUp.setEnabled(false);
			}
		});
	}

	/**
	 * Set up what happens when sign up button is clicked. Default is to close
	 * the program.
	 * 
	 * @param signUp
	 */
	public void signUpListener(final JButton signUp) {
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Thank you for signing up!");
				System.exit(0);
			}
		});
	}

	/**
	 * Set up the layout for the whole program.
	 * 
	 * @param layout
	 */
	public static void layoutManager(GroupLayout layout) {
		// Add some gaps between the components.
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Set the horizontal size for the given components to be equal to the
		// biggest component.
		layout.linkSize(SwingConstants.HORIZONTAL, email, password, firstName,
				lastName);
		layout.linkSize(SwingConstants.HORIZONTAL, emailField, passwordField,
				firstNameField, lastNameField, emailNotes, passwordNotes);
		layout.linkSize(SwingConstants.VERTICAL, emailField, passwordField,
				firstNameField, lastNameField);

		// Set the main groups first to make the code easier to read.
		GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout
				.createSequentialGroup()
				.addComponent(email)
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(emailField)
								.addComponent(emailNotes))
				.addComponent(emailImage));
		hGroup.addGroup(layout
				.createSequentialGroup()
				.addComponent(password)
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField)
								.addComponent(passwordNotes))
				.addComponent(passwordImage));
		hGroup.addGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(firstName).addComponent(lastName))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(firstNameField)
								.addComponent(lastNameField)
								.addComponent(checkBox).addComponent(signUp)));

		vGroup.addGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(email).addComponent(emailField)
								.addComponent(emailImage))
				.addGroup(

						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(emailNotes)));
		vGroup.addGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(password)
								.addComponent(passwordField)
								.addComponent(passwordImage))
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(passwordNotes)));
		vGroup.addGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(firstName)
								.addComponent(firstNameField))
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lastName)
								.addComponent(lastNameField))
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBox))
				.addGroup(
						layout.createParallelGroup(Alignment.CENTER)
								.addComponent(signUp)));

		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
	}
}