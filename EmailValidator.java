/**
 * Created on Dec 11, 2012, 4:49:12 AM
 * 
 * @author Jacob Bergvall, Oskar Fahlström
 */
public interface EmailValidator {

	/**
	 * Takes in a string as agrument and returns true if it is a valid email
	 * adress, false if it isn't.
	 * 
	 * @param s
	 * @return boolean
	 */
	public boolean validate(String s);
}
