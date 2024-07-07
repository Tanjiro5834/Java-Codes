import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
	
	//objects
	static Scanner scanner = new Scanner(System.in);
	static List<Recipe> recipes = new ArrayList<>();
	static Recipe rec;
	
	//variables
	static int index;
	private static String categories[] = {"Appetizers", "Breakfast", "Beverages", "Salads", "Main Courses", "Snacks"};
	private static int category;
	private static String recipename, recipedescription, ingredients, instruction;

	public static void main(String[] args){
		String[] choices = {"Add recipe", "View recipes", "Edit recipes", "Delete recipe", "Search recipe"};
		for(int i = 0; i < choices.length; i++){
			System.out.println(i + 1 + ". " + choices[i]);
		}
		
		System.out.print("Enter desired option here: ");
		while(true){
			int option = scanner.nextInt();
			if(option == 0) break;
			switch(option){
			case 1 -> add();
			case 2 -> view();
			case 3 -> edit();
			case 4 -> delete();
			case 5 -> searchRecipes();
			default -> System.err.println("Invalid option!\n");
			}
		}
	}
	public static void add(){
		System.out.print("Recipe name: ");  recipename = scanner.nextLine(); scanner.nextLine();
		System.out.print("Description: "); recipedescription = scanner.nextLine();
		System.out.print("Ingredients: "); ingredients = scanner.nextLine();
		System.out.print("Instruction: "); instruction = scanner.nextLine();
		
		System.out.println();
		
		for(int c = 0; c < categories.length; c++){
			System.out.println((c + 1) + ". " + categories[c]); //print the category array
		}
		
		do{
			System.out.print("Choose a category: "); 
			if(scanner.hasNextInt()) {
				category = scanner.nextInt();
				scanner.nextLine();
				
				if(category < 1 || category > categories.length){
					System.out.println("Invalid category choice. Please choose again.");
				}else{
					String assign = categories[category - 1];
					Recipe recpe = new Recipe(recipename, recipedescription, ingredients, instruction, assign); //add to recipe 
					recipes.add(recpe);
					System.out.println("Recipe added successfully.");
				}
			}else{
				System.out.println("Invalid input. Please try again.");
				scanner.nextLine();
			}
		}while(category < 1 || category > categories.length);
	}
	
	public static void view(){
		if(recipes.isEmpty()) {
			 System.out.println("Recipe list is empty.");
		}else{
			System.out.println("Recipes:");
			for(int i = 0; i < recipes.size(); i++) System.out.println((i + 1) + ". " + recipes.get(i).toString() + "\n");
		}	
	}
	
	public static void edit() {
		System.out.println("Enter recipe index to edit: "); 
		index = scanner.nextInt();
		String toStrIndx = Integer.toString(index);
		for(int i = 0; i < recipes.size(); i++){
			rec = recipes.get(i);
			if(toStrIndx.equals(rec)){
				System.out.print("What to edit? 1. name, 2. description, 3. ingredients, 4. instructions: ");
				int query = scanner.nextInt();
				
				switch(query){
				case 1:
					System.out.print("Enter new name: "); String newName = scanner.nextLine();
					rec.setName(newName); System.out.print("New name updated!");
					break;
					
				case 2:
					System.out.print("Enter new description: "); String newDescription = scanner.nextLine();
					rec.setDesc(newDescription); System.out.println("Description updated!");
					break;
					
				case 3:
					System.out.print("Enter updated ingredients: "); String newIngredients = scanner.nextLine();
					rec.setIngr(newIngredients); System.out.println("Ingredients updated.");
					break;
					
				case 4:
					System.out.print("Enter new instructions: "); String newInstructions = scanner.nextLine();
					rec.setIns(newInstructions); System.out.println("Instructions updated.");
					break;
				
				default:
					System.out.println("Invalid option.");
					break;
				}
			}else{
				System.err.println("Invalid index.\n");
			}
		}
	}
	
	public static void delete(){
		boolean remove = false;
		System.out.println("Enter recipe name to remove: "); String recipetoDlt = scanner.nextLine();
		for(int j = 0; j < recipes.size(); j++){
			Recipe rcp = recipes.get(j);
			if(rcp.name.equalsIgnoreCase(recipetoDlt)) recipes.remove(j); remove = true;
			System.out.println("Recipe removed.");
			remove = true;
		}
	}
	
	public static void searchRecipes(){
		System.out.println("1. Search by title\n2. Search by matching ingredients\nEnter option: "); int desire = scanner.nextInt();
		if(desire == 1) {
			System.out.println("Enter recipe title: "); String recipeTitle = scanner.nextLine(); boolean found = false;
			
			for(int k = 0; k < recipes.size(); k++){
				if(recipeTitle.equalsIgnoreCase(recipes.get(k).name)){
					Recipe rcp = recipes.get(k);
					System.out.println("Recipe found. Details\n " + rcp.getName() + "\n" + rcp.getDesc()
					+ "\n" + rcp.getIngr() + "\n" + rcp.getIns());
					found = true;
					break;
				}else {
					System.err.println("Recipe not found.");
					return;
				}
			}
		}else if(desire == 2){
			System.out.println("Enter recipe ingredient: "); String ingredientFind = scanner.nextLine();
			
			for(int find = 0; find < recipes.size(); find++){
				if(ingredientFind.equals(recipes.get(find).ingr)){
					Recipe f = recipes.get(find);
					System.out.println("Recipe found with other similar recipes.");
					System.out.println("Details: " + "Title: " + f.getName() + "Description: " + f.getDesc() + "\n");
					System.out.println("Ingredients: " + f.getIngr() + "\n" + "Instructions: " + f.getIns());
				}else{
					System.out.println("Recipe not found,");
				}
			}
		}else {
			System.out.println("Invalid choice.\n");
			return;
		}
	}
}

class Recipe{
	String name, desc, ingr, ins, category;
	
	Recipe(String name, String desc, String ingr, String ins, String category){
		this.name = name;
		this.desc = desc;
		this.ingr = ingr;
		this.ins =  ins;
		this.category = category;
	}
	
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public String getIngr() {
		return ingr;
	}
	public String getIns() {
		return ins;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	public void setDesc(String newDesc) {
		this.desc = newDesc;
	}
	public void setIngr(String newIngr) {
		this.ingr = newIngr;
	}
	public void setIns(String newIns) {
		this.ins = newIns;
	}
	
	@Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Description: " + desc + "\n" +
               "Ingredients: " + ingr + "\n" +
               "Instruction: " + ins;
    }
}
