public class TodoList{
	public static void main(String[]args) {
		Model m = new Model();
		View v = new View();
		Controller c = new Controller(m, v);
		
		c.start();
	}
}