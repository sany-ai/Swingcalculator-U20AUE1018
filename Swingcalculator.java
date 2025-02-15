import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingCalculatorV2 extends JFrame implements ActionListener {

    private JTextField displayField;
    private String currentInput = "";
    private String operator = "";
    private double result = 0;

    public SwingCalculatorV2() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display field
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 35));
        add(displayField, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/", "√",
            "4", "5", "6", "*", "%",
            "1", "2", "3", "-", "C",
            "0", ".", "+", "=", "DEL"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            currentInput += command;
            displayField.setText(currentInput);
        } else if ("+".equals(command) || "-".equals(command) || "*".equals(command) || "/".equals(command)) {
            operator = command;
            result = Double.parseDouble(currentInput);
            currentInput = "";
        } else if ("=".equals(command)) {
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
                    result /= num2;
                    break;
            }

            currentInput = Double.toString(result);
            displayField.setText(currentInput);
            operator = "";
        } else if ("DEL".equals(command)) {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                displayField.setText(currentInput);
            }
        } else if ("C".equals(command)) {
            currentInput = "";
            operator = "";
            result = 0;
            displayField.setText("");
        } else if ("√".equals(command)) {
            result = Math.sqrt(Double.parseDouble(currentInput));
            currentInput = Double.toString(result);
            displayField.setText(currentInput);
        } else if ("%".equals(command)) {
            result = Double.parseDouble(currentInput) / 100;
            currentInput = Double.toString(result);
            displayField.setText(currentInput);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingCalculatorV2::new);
    }
}
