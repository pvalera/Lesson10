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
		boolean palindromeFlag = true;
		int j = word.length() - 1;

		// compare each character forward vs backward
		for (int i = 0; i <= j; i++) {
			if (word.charAt(i) != word.charAt(j-i)) {
				palindromeFlag = false;
			}
		}

		return palindromeFlag;
	}
}
