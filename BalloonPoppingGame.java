import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class BalloonPoppingGame extends JPanel implements ActionListener, MouseListener {
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Balloon> balloons; // List of balloons
    private Random random;
    private int screenHeight, screenWidth;
    private int score; // Score variable to keep track of the popped balloons
    private JButton resetButton; // Reset button

    public BalloonPoppingGame() {
        setPreferredSize(new Dimension(800, 600)); // Screen size
        setBackground(new Color(135, 206, 250)); // Sky blue background
        screenHeight = 600;
        screenWidth = 1800;
        score = 0; // Initialize score

        balloons = new ArrayList<>();
        random = new Random();
        BalloonPoppingGame aThis = this;

        // Start the timer for animation
        timer = new Timer(16, aThis); // 60 FPS
        timer.start();

        addMouseListener(aThis); // Listen for mouse clicks to pop balloons

        // Add reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(1400, 10, 80, 30); // Position the reset button
        resetButton.addActionListener(e -> resetGame()); // Action listener for reset button
        setLayout(null); // Use null layout to place the button manually
        add(resetButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g); // Title on top
        drawBalloons(g); // Draw all the balloons
        drawFooterText(g); // Footer text at the bottom
        drawScore(g); // Draw the score
        drawRules(g); // Display game rules
    }

    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 35));
        g.setColor(Color.RED); // Title color
        g.drawString("Balloon Popping Game", getWidth() / 2 - 150, 50);
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLUE); // Footer text color
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    private void drawScore(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.BLACK); // Score text color
        g.drawString("Score: " + score, 20, 40); // Display the score
    }

    private void drawRules(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setColor(Color.DARK_GRAY); // Rules text color
        g.drawString("Pop balloons by clicking on them. Reset the game using the Reset button.", 100, 700);
    }

    private void drawBalloons(Graphics g) {
        for (Balloon balloon : balloons) {
            balloon.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the balloons upwards and check if they move off screen
        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            balloon.move();

            if (balloon.isOffScreen()) {
                balloons.remove(i); // Remove balloon when it goes off-screen
                i--;
            }
        }

        // Randomly add new balloons
        if (random.nextInt(20) == 0) { // Create a new balloon randomly
            int balloonX = random.nextInt(screenWidth - 100) + 50;
            balloons.add(new Balloon(balloonX, screenHeight, randomColor(), randomSize()));
        }

        repaint();
    }

    private Color randomColor() {
        // Generate a random color for balloons
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private int randomSize() {
        // Generate a random size for the balloons
        return random.nextInt(40) + 40; // Balloons between 40 and 80 pixels in size
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (int i = 0; i < balloons.size(); i++) {
            Balloon balloon = balloons.get(i);
            if (balloon.isPopped(mouseX, mouseY)) {
                balloons.remove(i); // Remove the popped balloon
                score++; // Increase score when a balloon is popped
                break; // Only one balloon can be popped at a time
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Balloon Popping Game");
        BalloonPoppingGame game = new BalloonPoppingGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    // Method to reset the game
    private void resetGame() {
        balloons.clear(); // Clear all the balloons
        score = 0; // Reset score to 0
        repaint(); // Repaint the screen
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }
}

// Balloon class to represent individual balloons
class Balloon {
    private int x, y;
    private int size;
    private Color color;
    private int speed;

    public Balloon(int x, int y, Color color, int size) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        this.speed = new Random().nextInt(3) + 2; // Random speed between 2 and 4
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size); // Draw the balloon
        g.setColor(Color.BLACK);
        g.drawLine(x + size / 2, y + size, x + size / 2, y + size + 20); // Draw the string
    }

    public void move() {
        y -= speed; // Move the balloon upwards
    }

    public boolean isOffScreen() {
        return y + size < 0; // Check if the balloon is off-screen
    }

    public boolean isPopped(int mouseX, int mouseY) {
        // Check if the balloon is clicked/popped
        int radius = size / 2;
        int centerX = x + radius;
        int centerY = y + radius;
        return Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2)) <= radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
