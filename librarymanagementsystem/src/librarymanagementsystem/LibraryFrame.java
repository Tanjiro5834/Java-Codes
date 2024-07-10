package librarymanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryFrame extends JFrame {
    private ArrayList<String> books = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> bookList;

    public LibraryFrame() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Library Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Mono", Font.ITALIC, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        bookList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton viewButton = new JButton("View all Books");
        JButton updateButton = new JButton("Update Books");
        
        Font buttonFont = new Font("Roboto Mono", Font.PLAIN, 14);
        
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String book = JOptionPane.showInputDialog("Enter book title:");
                if(book != null && !book.trim().isEmpty()){
                    books.add(book);
                    listModel.addElement(book);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(books.isEmpty()) {
            		JOptionPane.showMessageDialog(null, "No books available.");
            		return;
            	}
            	
                int selectedIndex = bookList.getSelectedIndex();
                if(selectedIndex != -1){
                    books.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(books.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "No books available.");
        			return;
        		}
        		
        		JTextArea textArea = new JTextArea(10,30);
        		for(int i = 0; i < books.size(); i++) {
        			textArea.append(i + 1 + ". " + books.get(i) + "\n");
        		}
        		
        		textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(null, scrollPane, "Book List", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        updateButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(books.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "No books available.");
            		return;
        		}
        		
        		int index = bookList.getSelectedIndex();
        		
        		if(index != -1) {
        			String updateBooks =JOptionPane.showInputDialog("Enter new title for this book: ");
        			books.set(index, updateBooks);
        			listModel.set(index, updateBooks);
        		}else {
        			JOptionPane.showMessageDialog(null, "Invalid index.");
        		}
        	}
        });

        setVisible(true);
    }
}