import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String num1 = "", num2 = "", operator = "";

    public SwingCalculator() {
        setTitle("Swing Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            if (operator.isEmpty()) {
                num1 += command;
            } else {
                num2 += command;
            }
        } else if ("+-*/".contains(command)) {
            if (!num1.isEmpty()) {
                operator = command;
            }
        } else if (command.equals("=")) {
            if (!num1.isEmpty() && !num2.isEmpty()) {
                double result = calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
                display.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = "";
                operator = "";
            }
        } else if (command.equals("C")) {
            num1 = num2 = operator = "";
        }

        updateDisplay();
    }

    private double calculate(double n1, double n2, String op) {
        return switch (op) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "*" -> n1 * n2;
            case "/" -> n2 != 0 ? n1 / n2 : 0;
            default -> 0;
        };
    }

    private void updateDisplay() {
        display.setText(num1 + operator + num2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingCalculator::new);
    }
}
