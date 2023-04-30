package calculator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import menuframe.MenuFrame;

public class Calculator extends JFrame {

    private JTextField inputField;
    private double num1;
    private double num2;
    private String operator;

    public Calculator() {
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        // Input field
        inputField = new JTextField(20);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 20;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(inputField, c);

        // Tombol-tombol angka
        JPanel numberPanel = new JPanel(new GridBagLayout());
        c.gridwidth = 1;
        c.insets = new Insets(5, 5, 5, 5);
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new NumberButtonListener());
            c.gridx = (i - 1) % 3;
            c.gridy = 1 + (i - 1) / 3;
            numberPanel.add(button, c);
        }
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(new NumberButtonListener());
        c.gridx = 1;
        c.gridy = 5;
        numberPanel.add(zeroButton, c);
        JButton dotButton = new JButton(".");
        dotButton.addActionListener(new NumberButtonListener());
        c.gridx = 2;
        c.gridy = 5;
        numberPanel.add(dotButton, c);
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsButtonListener());
        c.gridx = 0;
        c.gridy = 5;
        numberPanel.add(equalsButton, c);
        panel.add(numberPanel, c);

        
        JPanel numberPanels = new JPanel(new GridBagLayout());

// Tombol Back to Menu
JButton backButton = new JButton("Back");
backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        MenuFrame menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }
});
c.gridx = 0;
c.gridy = 1;
numberPanels.add(backButton, c);

JButton clearButton = new JButton("Clear");
c.gridx = 1;
c.gridy = 1;
clearButton.addActionListener(new ClearButtonListener());
numberPanels.add(clearButton, c);

c.gridx = 0;
c.gridy = 2;
panel.add(numberPanels, c);


           // Tombol-tombol operator
        JPanel operatorPanel = new JPanel(new GridBagLayout());
        c.gridwidth = 1;
        String[] operators = {"+", "-", "*", "/", "%"};
        for (String op : operators) {
            JButton button = new JButton(op);
            button.addActionListener(new OperatorButtonListener());
            c.gridx = 0;
            c.gridy++;
            operatorPanel.add(button, c);
        }
        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = operators.length + 1;
        panel.add(operatorPanel, c);
        
      

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            inputField.setText(inputField.getText() + buttonText);
        }
    }

    private class OperatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            num1 = Double.parseDouble(inputField.getText());
            operator = buttonText;
            inputField.setText("");
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(inputField.getText());
            double result = 0;
            String equation = "";

            switch (operator) {
                case "+":
                    equation = num1 + "+" + num2;
                    result = num1 + num2;
                    break;
                case "-":
                    equation = num1 + "-" + num2;
                    result = num1 - num2;
                    break;
                case "*":
                    equation = num1 + "*" + num2;
                    result = num1 * num2;
                    break;
                case "/":
                    equation = num1 + "/" + num2;
                    result = num1 / num2;
                    break;
                case "%":
                    equation = num1 + "% * " + num2;
                    result = (num1 / 100) * num2;
                    break;
            }

            inputField.setText(equation + " = " + result);
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

   
