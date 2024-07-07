public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void start() {
		while(true) {
			String[]options = {"1.) Add a Note", "2.) Delete a Note", "3.) Sort Notes", "4.) Update Note(s)", "5.) View all Notes"};
			for(String o : options) {
				view.displayMessage(o);
			}
			
			String choice = view.getInput("Choose an operation: ");
			switch(choice) {
			case "1" -> addNote();
			case "2" -> deleteNote();
			case "3" -> model.sortItems();
			case "4" -> updateNote();
			case "5" -> view.displayList(model.getInputList());
			case "6" -> System.exit(0);
			default -> view.displayMessage("Invalid input");
			}
		}
	}
	
	public void addNote() {
		String title = view.getInput("Enter list title: ");
		String description = view.getInput("Enter description: ");
		model.add(title, description);
		view.displayMessage("Note added.");
	}
	
	public void deleteNote() {
		String title = view.getInput("Enter item title to delete: ");
		if(model.delete(title)) {
			view.displayMessage("Item removed.");
		}else {
			view.displayMessage("Item not found.");
		}
	}
	
	public void updateNote() {
		String oldTitle = view.getInput("Enter current title of the note to update: ");
		if(model.getInputList().contains(oldTitle)) {
			String newTitle = view.getInput("Enter the new title: ");
			String newDescription = view.getInput("Enter new description: ");
			if(model.update(oldTitle, newTitle, newDescription)) {
				view.displayMessage("List updated.");
			}else {
				view.displayMessage("Error updating the note.");
			}
		}else {
			view.displayMessage("Note with the given title doesn't exist.");
		}
	}
}
