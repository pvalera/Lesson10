package exercises;

import java.util.List;

import examples.FileHelper;

public class Palindrome {

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}
	
	public boolean wordExists(String word) {
		if (loadWords().contains(word)) {
			return true;
		}
		return false;
	}
	
	public boolean isPalindrome(String word) {
		String reversedString = "";
		for (int i = word.length() - 1; i>= 0; i--) {
			reversedString = reversedString + word.charAt(i);
		}
		
		if (word.equals(reversedString)) {
			return true;
		}
		return false;
	}
}
