import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton decimalButton, equalButton, clearButton, deleteButton;

    private String currentInput = "";
    private String operator = "";
    private double result = 0;

    public Calculator() {

        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 

        // Display Field
        displayField = new JTextField();
        displayField.setPreferredSize(new Dimension(200, 70));
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24)); 
        add(displayField, BorderLayout.NORTH);

        // Number Buttons
        numberButtons = new JButton[10];
        JPanel numberPanel = new JPanel(new GridLayout(4, 3)); 

        for (int i = 9; i >= 0; i--) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
            numberPanel.add(numberButtons[i]);
        }
        add(numberPanel, BorderLayout.CENTER);
 
        // Operator Buttons
        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");

        JPanel operatorPanel = new JPanel(new GridLayout(4, 1)); 
        
        for (JButton button : operatorButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            operatorPanel.add(button);
        }
        add(operatorPanel, BorderLayout.EAST);

        // Buttons
        decimalButton = new JButton(".");
        decimalButton.setFont(new Font("Arial", Font.PLAIN, 18));
        decimalButton.addActionListener(this);
        equalButton = new JButton("=");
        equalButton.setFont(new Font("Arial", Font.PLAIN, 18));
        equalButton.addActionListener(this);
        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(this);
        deleteButton = new JButton("Del");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.addActionListener(this);

        JPanel otherPanel = new JPanel();
        otherPanel.add(decimalButton);
        otherPanel.add(equalButton);
        otherPanel.add(clearButton);
        otherPanel.add(deleteButton);
        add(otherPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0))) {
            currentInput += command;
            displayField.setText(currentInput);
        } else if (command.equals(".")) {
            if (!currentInput.contains(".")) {
                currentInput += command;
                displayField.setText(currentInput);
            }
        } else if ("+-*/".contains(command)) {
            if (!currentInput.isEmpty()) {
                operator = command;
                result = Double.parseDouble(currentInput);
                currentInput = "";
            }
        } else if (command.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double num2 = Double.parseDouble(currentInput);
                switch (operator) {
                    case "+":
                        result += num2;
                        break;
                    case "-":
                        result -= num2;
                        break;
                    case "*":
                        result *= num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            displayField.setText("Error");
                            return;
                        }
                        result /= num2;
                        break;
                }
                currentInput = String.valueOf(result);
                displayField.setText(currentInput);
                operator = "";
            }
        } else if (command.equals("C")) {
            currentInput = "";
            operator = "";
            result = 0;
            displayField.setText("");
        } else if (command.equals("Del")) {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                displayField.setText(currentInput);
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
