import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
public class Model {
	
	private LinkedList<String> inputList = new LinkedList<>();
	private HashMap<String, String> users = new HashMap<>();
	
	public void add(String title, String description) {
		inputList.add(title);
		inputList.add(description);
	}
	
	public boolean delete(String title) {
		if(inputList.contains(title)) {
			int index = inputList.indexOf(title);
			inputList.remove(index);
			inputList.remove(index);
			
			return true;
		}
		return false;
	}
	
	public void sortItems() {
		selectionSort(inputList);
	}
	
	public void selectionSort(LinkedList<String> inputList) {
		int n = inputList.size();
		for(int i = 0; i < n - 1; i++) {
			ListIterator<String> currentIterator = inputList.listIterator(i);
			String min = inputList.get(i);
			int mid = i;
			
			while(currentIterator.hasNext()) {
				String nxt = currentIterator.next();
				if(nxt.compareTo(min) < 0) {
					min = nxt;
					mid = currentIterator.nextIndex() - 1;
				}
			}
			if(mid != i) {
				String temp = inputList.get(i);
				inputList.set(i, min);
				inputList.set(mid, temp);
			}
		}
	}
	
	public LinkedList<String> getInputList(){
		return inputList;
	}
	
	public HashMap<String, String> getUsers(){
		return users;
	}
	
	public boolean validateUser(String userName, String password) {
		return users.containsKey(userName) && users.get(userName).equals(password);
	}
	
	public boolean registerUser(String userName, String password) {
		if(!users.containsKey(userName)) {
			users.put(userName, password);
			return true;
		}
		return false;
	}
	
	public boolean update(String oldTitle, String newTitle, String newDescription) {
		int index = inputList.indexOf(oldTitle);
		if(index != -1) {
			inputList.set(index, newTitle);
			inputList.set(index + 1, newDescription);
			return true;
		}
		return false;
	}
}
