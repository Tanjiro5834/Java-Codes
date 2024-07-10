package librarymanagementsystem;
import javax.swing.*;

public class LibraryManagementSystem {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryFrame();
            }
        });
	}

}
