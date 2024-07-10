import java.util.Scanner;
public class CheckerPalindrome {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("Enter a word: ");
			String input = scan.next().toLowerCase();
			System.out.println("Result: ");
			String result = validateWord(input);
			if(result.equals(result)) {
				display(input);
			}else {
				System.out.println(result);
			} 
			if(input.equals("e")) {
				break;
			}
		}
		scan.close();
	}	
	static String validateWord(String word) {
		if(word.length() < 3) {
			return "Please enter at least 3 characters.";
		}else if(word.length() % 2 == 0) {
			return "The word count is not an odd number.";
		}
		String reversed = new StringBuilder(word).reverse().toString();
		if(!word.equals(reversed)) {
			return word + " is not a palindrome.";
		}
		return "";
	}
	
	static void display(String word) {
		System.out.println();
		int wordCount = word.length();
		int mid = Math.round(wordCount / 2) + 1;
		int counter = wordCount - 1;
		for(int i = 0; i < wordCount; i++) {
			for(int j = 0;j < wordCount; j++)  {
				if(j == i || j == mid - 1 || counter == j) {
					if(j == mid - 1 || i == mid - 1) {
						System.out.printf("   %c   ",word.charAt(j));
					}else {
						System.out.printf("   %c   ",word.charAt(i));
					}
					System.out.printf("  %c  ",word.charAt(i));
				}else {
					System.out.print("     ");
				}
			}
			counter--;
			System.out.println();
		}
	}
}