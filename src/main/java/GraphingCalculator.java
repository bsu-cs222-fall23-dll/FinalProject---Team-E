/*
Authors: Sung Hoo Hong, David Chen, Alexander Bousman

Current iteration operates as a standard arithmetic calculator. Advanced features to be added later.
*/

// import necessary libraries for UI Design
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphingCalculator {
    private final JTextField textDisplay;
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    // Calculator UI Design
    public GraphingCalculator() {
        // Calculator Frame Design
        JFrame calculator = new JFrame("Calculator");
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setSize(400,560);
        calculator.setLayout(new BorderLayout());

        textDisplay = new JTextField();
        textDisplay.setFont(new Font("default", Font.PLAIN,50));
        textDisplay.setHorizontalAlignment(JTextField.RIGHT);
        calculator.add(textDisplay, BorderLayout.NORTH);

        // Buttons on the Calculator
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        // Buttons Layout
        String[] buttonLabels = {
                "%", "CE", "C", "DEL",
                "1/x", "x^2", "sqrt(x)", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "(-)", "0", ".", "="
        };

        // Buttons Design
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("default", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        calculator.add(buttonPanel, BorderLayout.CENTER);
        calculator.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                if (startNewInput) {
                    textDisplay.setText("");
                    startNewInput = false;
                }
                textDisplay.setText(textDisplay.getText() + command);
            } else if (command.equals("C")) {
                textDisplay.setText("");
                firstOperand = 0;
                operator = "";
            } else if (command.equals("CE")) {
                textDisplay.setText("");
                firstOperand = 0;
                operator = "";
            } else if (command.equals("(-)")) {
                textDisplay.setText("-" + textDisplay.getText());
            } else if (command.equals(".")) {
                textDisplay.setText(textDisplay.getText() + command);
            }
            // Operations
            else if (command.equals("=")) {
                double secondOperand = Double.parseDouble(textDisplay.getText());
                double result = 0;
                switch (operator) {
                    // addition
                    case "+" -> result = firstOperand + secondOperand;

                    // subtraction
                    case "-" -> result = firstOperand - secondOperand;

                    // multiplication
                    case "x" -> result = firstOperand * secondOperand;
                    case "1/x" -> {
                        if (firstOperand != 0) {
                            result = 1 / firstOperand;
                        } else {
                            textDisplay.setText("Error");
                        }
                    }
                    // power
                    case "x^2" -> result = (int) Math.pow(firstOperand, 2);

                    // square root
                    case "sqrt(x)" -> result = Math.sqrt(firstOperand);

                    // percentage
                    case "%" -> result = firstOperand / 100;

                    // division
                    case "/" -> {
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            textDisplay.setText("Error");
                        }
                    }
                }

                textDisplay.setText(String.valueOf(result));
                startNewInput = true;
            } else {
                if (!operator.isEmpty()) {
                    return;
                }
                firstOperand = Double.parseDouble(textDisplay.getText());
                operator = command;
                startNewInput = true;
            }
        }
    }

    // Display the calculator
    public static void main(String[] args) {
        new GraphingCalculator();
    }
}
