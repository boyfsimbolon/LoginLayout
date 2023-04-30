package menuframe;

import calculator.Calculator;
import tictatoe.TicTacToe;
import snake.SnakeGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public MenuFrame() {
        super("Menu Frame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3, 1));

        button1 = new JButton("Kalkulator");
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("TicTacToe");
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("Snake");
        button3.addActionListener(this);
        add(button3);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            SwingUtilities.invokeLater(() -> {
                Calculator calculator = new Calculator();
                calculator.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        MenuFrame.this.setVisible(false);
                    }
                });
                MenuFrame.this.setVisible(true);
            });
        } else if (e.getSource() == button2) {
            SwingUtilities.invokeLater(() -> {
                TicTacToe ticTacToe = new TicTacToe();
                ticTacToe.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        MenuFrame.this.setVisible(false);
                    }
                });
                MenuFrame.this.setVisible(true);
            });
        } else if (e.getSource() == button3) {
            SwingUtilities.invokeLater(() -> {
                SnakeGameFrame snakeGameFrame = new SnakeGameFrame();
                snakeGameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        MenuFrame.this.setVisible(true);
                    }
                });
                MenuFrame.this.setVisible(false);
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuFrame();
        });
    }
}
