package tictatoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import menuframe.MenuFrame;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private String currentPlayer;
    private JLabel currentPlayerLabel;
    private Random random;

    public TicTacToe() {
        super("Tic Tac Toe by BoySimbolon");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[3][3];
        currentPlayer="Anda";
        random = new Random();

        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        add(boardPanel, BorderLayout.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[row][col].addActionListener(this);
                boardPanel.add(buttons[row][col]);
            }
        }

        currentPlayerLabel = new JLabel("Anda", SwingConstants.CENTER);
        currentPlayerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(currentPlayerLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame();
                menuFrame.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(
            ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().isEmpty()) {
            button.setText(String.valueOf(currentPlayer));

            if (checkWin(currentPlayer)) {
                JOptionPane.showMessageDialog(this,  currentPlayer + " Menang");
                resetBoard();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "Permainan Gagal");
                resetBoard();
            } else {
                switchPlayer();
                performComputerMove();
            }
        }
    }

    private void performComputerMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    buttons[row][col].setText(String.valueOf(currentPlayer));

                    int score = minimax(0, false);

                    buttons[row][col].setText("");

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        buttons[bestRow][bestCol].setText(String.valueOf(currentPlayer));

        if (checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, currentPlayer + " Menang");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Permainan Gagal");
            resetBoard();
        } else {
            switchPlayer();
        }
    }

        private void switchPlayer() {
        currentPlayer = (currentPlayer == "Komputer") ? "Anda" : "Komputer";
        currentPlayerLabel.setText( currentPlayer);
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(String player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(player)) &&
                buttons[row][1].getText().equals(String.valueOf(player)) &&
                buttons[row][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(player)) &&
                buttons[1][col].getText().equals(String.valueOf(player)) &&
                buttons[2][col].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private int minimax(int depth, boolean isMaximizingPlayer) {
        if (checkWin("Komputer")) {
            return -1; // Komputer menang
        } else if (checkWin("Pemain")) {
            return 1; // Pemain menang
        } else if (isBoardFull()) {
            return 0; // Seri
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().isEmpty()) {
                        buttons[row][col].setText(String.valueOf("0"));
                        int score = minimax(depth + 1, false);
                        buttons[row][col].setText("");
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().isEmpty()) {
                        buttons[row][col].setText(String.valueOf("X"));
                        int score = minimax(depth + 1, true);
                        buttons[row][col].setText("");
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;  }
        }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe();
        });
    }
}


