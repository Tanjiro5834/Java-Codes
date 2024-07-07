import java.util.LinkedList;
import java.util.Scanner;
public class View {
	private Scanner scan = new Scanner(System.in);
	
	public String getInput(String prompt) {
		System.out.print(prompt);
		return scan.next();
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void displayList(LinkedList<String> inputList) {
		for(int i = 0; i < inputList.size(); i++) {
			System.out.println((i + 1) + ". " + inputList.get(i));
		}
	}
}
