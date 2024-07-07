import java.util.Queue;
import java.util.LinkedList;
public class PotatoGame {

	public static void main(String[] args) {
		
		potatoGame();
	}
	
	static String potatoGame() {
		Queue<String>players = new LinkedList<String>();
		String currentPlayer = "";
		
		players.offer("James");
		players.offer("Harry");
		players.offer("Michael");
		players.offer("Jeffry");
		players.offer("Nathaniel");
		players.offer("Jack");
		
		int count = 1, passes = 10;
		while(!players.isEmpty()) {
			currentPlayer = players.poll();
			count++;
			if(count == passes) {
				System.out.println(currentPlayer+ " has been eliminated.");
				count = 1;
			}else {
				players.offer(currentPlayer);
				count++;
			}
		}
		System.out.println("The winner is "+currentPlayer + ".");
		return players.poll();
	}

}
