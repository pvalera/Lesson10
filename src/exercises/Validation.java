package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	// validate email address
	public boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9]+(\\.)?)?[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z0-9]+$");
		Matcher matcher = pattern.matcher(email);
		return (matcher.find());
	}

	// validate phone number
	public boolean isValidNumber(String number) {
		Pattern pattern = Pattern.compile("^(\\d(\\.|-)?)?(\\d{3}(\\.|-)?)\\d{3}(\\.|-)?\\d{4}$");
		Matcher matcher = pattern.matcher(number);
		return (matcher.find());
	}
	
}
