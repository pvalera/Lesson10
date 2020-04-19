package exercises;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;



public class ValidationTest {
	
	Validation testRegex = new Validation();
	
	@Test
	void testPhoneNumber() {
		assertTrue(testRegex.isValidNumber("1234567890"));
		assertTrue(testRegex.isValidNumber("18001234567"));
		assertTrue(testRegex.isValidNumber("123-456-7890"));
		assertTrue(testRegex.isValidNumber("1-800-456-7890"));
		assertTrue(testRegex.isValidNumber("123.456.7890"));
		
		//invalid phone number
		assertFalse(testRegex.isValidNumber("12x3456789"));
		assertFalse(testRegex.isValidNumber("123456789!"));
		assertFalse(testRegex.isValidNumber("123 456789"));
		assertFalse(testRegex.isValidNumber("12345"));
		assertFalse(testRegex.isValidNumber("1234567891011"));
	}
	
	@Test
	void testEmail() {
		assertTrue(testRegex.isValidEmail("johndoe@gmail.com"));
		assertTrue(testRegex.isValidEmail("john.doe@hotmail.com"));
		assertTrue(testRegex.isValidEmail("jane1doe@hotmail.com"));
		assertTrue(testRegex.isValidEmail("info@happy.com"));
		
		//invalid email
		assertFalse(testRegex.isValidEmail("jane@doe@gmailcom"));
		assertFalse(testRegex.isValidEmail("jane doe@gmailcom"));
		assertFalse(testRegex.isValidEmail("janedoe@gmailcom!"));
	}
}
