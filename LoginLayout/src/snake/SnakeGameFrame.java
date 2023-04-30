package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGameFrame extends JFrame implements KeyListener {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (FRAME_WIDTH * FRAME_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 120;

    private final List<Integer> snakeX;
    private final List<Integer> snakeY;
    private int foodX;
    private int foodY;
    private Direction direction;
    private boolean isRunning;

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public SnakeGameFrame() {
        setTitle("Snake Game");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        snakeX = new ArrayList<>();
        snakeY = new ArrayList<>();
        direction = Direction.RIGHT;
        isRunning = true;

        addKeyListener(this);
        startGame();
    }

    public void startGame() {
        snakeX.clear();
        snakeY.clear();
        snakeX.add(FRAME_WIDTH / 2);
        snakeY.add(FRAME_HEIGHT / 2);

        generateFood();

        Timer timer = new Timer(DELAY, e -> gameLoop());
        timer.start();
    }

    public void gameLoop() {
        if (isRunning) {
            move();
            checkCollision();
            repaint();
        }
    }

    public void move() {
        for (int i = snakeX.size() - 1; i > 0; i--) {
            snakeX.set(i, snakeX.get(i - 1));
            snakeY.set(i, snakeY.get(i - 1));
        }

        switch (direction) {
            case UP:
                snakeY.set(0, snakeY.get(0) - UNIT_SIZE);
                break;
            case DOWN:
                snakeY.set(0, snakeY.get(0) + UNIT_SIZE);
                break;
            case LEFT:
                snakeX.set(0, snakeX.get(0) - UNIT_SIZE);
                break;
            case RIGHT:
                snakeX.set(0, snakeX.get(0) + UNIT_SIZE);
                break;
        }
    }

    public void checkCollision() {
        // Check collision with food
        if (snakeX.get(0) == foodX && snakeY.get(0) == foodY) {
            snakeX.add(foodX);
            snakeY.add(foodY);
            generateFood();
        }

        // Check collision with borders
        if (snakeX.get(0) < 0 || snakeX.get(0) >= FRAME_WIDTH+1 || snakeY.get(0) < 0 || snakeY.get(0) >= FRAME_HEIGHT+1) {
            isRunning = false;
        }

        // Check collision with itself
        for (int i = 1; i < snakeX.size(); i++) {
            if (snakeX.get(i) == snakeX.get(0) && snakeY.get(i) == snakeY.get(0)) {
                isRunning = false;
                break;
            }
        }
    }

    public void generateFood() {
        Random random = new Random();
        foodX = random.nextInt((FRAME_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((FRAME_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

       @Override
   public void paint(Graphics g) {
    super.paint(g); // Panggil super.paint(g) untuk membersihkan area gambar sebelum menggambar

    Graphics2D g2d = (Graphics2D) g; // Ubah objek Graphics menjadi Graphics2D

    // Draw game area
    g2d.setColor(Color.GREEN);
    g2d.fillOval(snakeX.get(0), snakeY.get(0), UNIT_SIZE, UNIT_SIZE);

    // Gambar kepala ular dengan bentuk oval dan mata
    int headSize = UNIT_SIZE;
    int eyeSize = UNIT_SIZE / 4;
    int eyeOffsetX = UNIT_SIZE / 4;
    int eyeOffsetY = UNIT_SIZE / 4;

    g2d.setColor(Color.WHITE);
    g2d.fillOval(snakeX.get(0) + eyeOffsetX, snakeY.get(0) + eyeOffsetY, headSize - eyeOffsetX, headSize - eyeOffsetY);

    g2d.setColor(Color.BLACK);
    g2d.drawOval(snakeX.get(0) + eyeOffsetX, snakeY.get(0) + eyeOffsetY, headSize - eyeOffsetX, headSize - eyeOffsetY);

    // Gambar mata pada kepala ular
    g2d.setColor(Color.WHITE);
    g2d.fillOval(snakeX.get(0) + eyeOffsetX, snakeY.get(0) + eyeOffsetY, eyeSize, eyeSize);
    g2d.fillOval(snakeX.get(0) + eyeOffsetX * 3, snakeY.get(0) + eyeOffsetY, eyeSize, eyeSize);

    g2d.setColor(Color.BLACK);
    g2d.drawOval(snakeX.get(0) + eyeOffsetX, snakeY.get(0) + eyeOffsetY, eyeSize, eyeSize);
    g2d.drawOval(snakeX.get(0) + eyeOffsetX * 3, snakeY.get(0) + eyeOffsetY, eyeSize, eyeSize);

    // Draw body
    for (int i = 1; i < snakeX.size(); i++) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(snakeX.get(i), snakeY.get(i), UNIT_SIZE, UNIT_SIZE);
    }

    // Draw food
    g2d.setColor(Color.RED);
    g2d.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

    // Game over message
    if (!isRunning) {
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = g2d.getFontMetrics();
        String gameOverMsg = "Game Over";
        int x = (FRAME_WIDTH - metrics.stringWidth(gameOverMsg)) / 2;
        int y = FRAME_HEIGHT / 2;
        g2d.drawString(gameOverMsg, x, y);
    }
}




    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGameFrame::new);
    }
}
  
