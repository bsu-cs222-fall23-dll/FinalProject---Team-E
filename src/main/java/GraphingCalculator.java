/*
Authors: Sung Hoo Hong, David Chen, Alexander Bousman

Current iteration operates as a standard arithmetic calculator. Advanced features to be added later.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private final JTextField textDisplay;
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public GraphingCalculator() {
        JFrame calculator = new JFrame("Calculator");
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setSize(400,560);
        calculator.setLayout(new BorderLayout());

        textDisplay = new JTextField();
        textDisplay.setFont(new Font("default", Font.PLAIN,50));
        textDisplay.setHorizontalAlignment(JTextField.RIGHT);
        calculator.add(textDisplay, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        String[] buttonLabels = {
                "%", "CE", "C", "DEL",
                "1/x", "x^2", "sqrt(x)", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "(-)", "0", ".", "="
        };

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
            else if (command.equals("=")) {
                double secondOperand = Double.parseDouble(textDisplay.getText());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "x":
                        result = firstOperand * secondOperand;
                        break;
                    case "1/x":
                        result = 1/firstOperand;
                        break;
                    case "x^2":
                        result = (int) Math.pow(firstOperand, 2);
                        break;
                    case "sqrt(x)":
                        result = Math.sqrt(firstOperand);
                        break;
                    case "%":
                        result = firstOperand/100;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            textDisplay.setText("Error");
                        }
                        break;
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

    public static void main(String[] args) {
        new GraphingCalculator();
    }
}
