import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String num1 = "", num2 = "", operator = "";
    
    public SwingCalculator() {
        setTitle("Swing Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Text field for displaying numbers and results
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Panel for buttons
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
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) { // If a number is clicked
            if (operator.isEmpty()) {
                num1 += command;
            } else {
                num2 += command;
            }
            textField.setText(num1 + operator + num2);
        } else if (command.matches("[/*\\-+]")) { // If an operator is clicked
            if (!num1.isEmpty()) {
                operator = command;
            }
            textField.setText(num1 + operator);
        } else if (command.equals("=")) { // If "=" is clicked, perform calculation
            if (!num1.isEmpty() && !num2.isEmpty()) {
                double result = calculate(Double.parseDouble(num1), Double.parseDouble(num2), operator);
                textField.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                num2 = "";
                operator = "";
            }
        } else if (command.equals("C")) { // If "C" is clicked, clear everything
            num1 = num2 = operator = "";
            textField.setText("");
        }
    }

    private double calculate(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return (n2 != 0) ? n1 / n2 : 0; // Prevent division by zero
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingCalculator::new);
    }
}
