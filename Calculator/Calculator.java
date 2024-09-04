import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;
    private double num1 = 0, num2 = 0, result = 0;
    private char op;
    private String inputText;

    private Font myFont = new Font("Comic Mono", Font.PLAIN, 30);

    public Calculator() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 550);
        this.setLayout(null);

        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(myFont);
        display.setEditable(false);
        this.add(display);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        functionButtons = new JButton[8];

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for(int i = 0; i < 8; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        this.add(delButton);
        this.add(clrButton);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String inputText = display.getText();

        if(e.getSource() == equButton){
            if (inputText.contains(" ")) {
                String[] parts = inputText.split("\\s+");
                num2 = Double.parseDouble(parts[2]);

                switch(op){
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if(num2 != 0){
                            result = num1 / num2;
                        }else{
                            display.setText("Error: Division by zero.");
                            return;
                        }
                        break;
                    default:
                        break;
                }
                display.setText(formatResult(result)); 
            }
        }else if(e.getSource() == delButton){
            if (!inputText.isEmpty()) {
                display.setText(inputText.substring(0, inputText.length() - 1));
            }
        }else if(e.getSource() == clrButton){
            display.setText("");
        }else if(e.getSource() == decButton){
            if(!inputText.contains(".")){
                display.setText(inputText + ".");
            }
        }else{
            for(int i = 0; i < 10; i++){
                if(e.getSource() == numberButtons[i]){
                    display.setText(inputText + i);
                    return; 
                }
            }
            //handle operators
            if(e.getSource() == addButton){
                handleOperator('+');
            }else if(e.getSource() == subButton){
                handleOperator('-');
            }else if(e.getSource() == mulButton){
                handleOperator('*');
            }else if(e.getSource() == divButton){
                handleOperator('/');
            }
        }
    }

    private void handleOperator(char operator) {
        if(!display.getText().isEmpty()){
            String inputText = display.getText();
            if(!inputText.endsWith(" ")){
                num1 = Double.parseDouble(inputText);
                op = operator;
                display.setText(inputText + " " + op + " ");
            }
        }
    }

    private String formatResult(double result) {
        if(result == (long) result){
            return String.format("%d", (long) result); 
        }else{
            return String.format("%s", result); 
        }
    }
}
 