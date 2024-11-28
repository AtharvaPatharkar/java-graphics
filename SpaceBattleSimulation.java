import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SpaceBattleSimulation extends JPanel implements ActionListener {

    // Timer for animation
    private Timer timer;
    private int spaceshipX = 0; // Position for player spaceship
    private int spaceshipY = 0;
    private int enemyX = 300; // Position for enemy spaceship
    private int enemyY = -100;
    private ArrayList<int[]> stars; // List of stars (x, y)
    private ArrayList<int[]> lasers; // List of lasers (x, y)
    private ArrayList<int[]> enemyLasers; // Enemy lasers (x, y)
    private Random random;

    // Constructor
    public SpaceBattleSimulation() {
        random = new Random();
        spaceshipX = getWidth() / 2 - 25;
        spaceshipY = getHeight() - 100;
        stars = new ArrayList<>();
        lasers = new ArrayList<>();
        enemyLasers = new ArrayList<>();
        generateStars();

        // Setting up the timer for animation (refresh every 30ms)
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Battle Simulation - Code with AP");
        SpaceBattleSimulation panel = new SpaceBattleSimulation();

        // Setting up the frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Override paintComponent to draw custom elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Clear the background with a space-like color
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw stars
        drawStars(g2d);

        // Draw the player's spaceship
        drawSpaceship(g2d);

        // Draw the enemy spaceship
        drawEnemySpaceship(g2d);

        // Draw lasers fired by the player
        drawLasers(g2d);

        // Draw enemy lasers
        drawEnemyLasers(g2d);

        // Draw the title at the top center
        drawTitleText(g2d);

        // Draw the bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Method to generate stars at random positions
    private void generateStars() {
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(800);
            int y = random.nextInt(600);
            stars.add(new int[]{x, y});
        }
    }

    // Method to draw stars in the background
    private void drawStars(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int[] star : stars) {
            g2d.fillOval(star[0], star[1], 2, 2); // Draw a small circle for each star
        }
    }

    // Method to draw the player's spaceship
    private void drawSpaceship(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(spaceshipX + getWidth() / 2 - 25, spaceshipY + getHeight() - 100, 50, 30); // Body
        g2d.setColor(Color.CYAN);
        g2d.fillRect(spaceshipX + getWidth() / 2 - 15, spaceshipY + getHeight() - 90, 30, 20); // Cockpit
    }

    // Method to draw the enemy spaceship
    private void drawEnemySpaceship(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(enemyX + getWidth() / 2 - 25, enemyY + 100, 50, 30); // Body
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(enemyX + getWidth() / 2 - 15, enemyY + 110, 30, 20); // Cockpit
    }

    // Method to draw the player's lasers
    private void drawLasers(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        for (int[] laser : lasers) {
            g2d.fillRect(laser[0], laser[1], 5, 20); // Draw each laser
        }
    }

    // Method to draw the enemy lasers
    private void drawEnemyLasers(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        for (int[] laser : enemyLasers) {
            g2d.fillRect(laser[0], laser[1], 5, 20); // Draw each enemy laser
        }
    }

    // Method to draw the title text at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.WHITE);
        String text = "Space Battle Simulation";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
        g2d.drawString(text, x, y);
    }

    // Method to draw the bottom text at the bottom center
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.WHITE);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Method to update the animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move stars (scrolling effect)
        for (int[] star : stars) {
            star[1] += 2;
            if (star[1] > getHeight()) {
                star[1] = 0; // Reset star to the top of the screen
            }
        }

        // Move the enemy spaceship up and down
        enemyY += 2;
        if (enemyY > getHeight() - 200) {
            enemyY = -100;
        }

        // Move lasers upwards (player's lasers)
        for (int i = lasers.size() - 1; i >= 0; i--) {
            lasers.get(i)[1] -= 5;
            if (lasers.get(i)[1] < 0) {
                lasers.remove(i); // Remove laser if it goes off-screen
            }
        }

        // Move enemy lasers downwards
        for (int i = enemyLasers.size() - 1; i >= 0; i--) {
            enemyLasers.get(i)[1] += 5;
            if (enemyLasers.get(i)[1] > getHeight()) {
                enemyLasers.remove(i); // Remove enemy laser if it goes off-screen
            }
        }

        // Fire enemy lasers randomly
        if (random.nextInt(100) < 5) {
            enemyLasers.add(new int[]{enemyX + getWidth() / 2, enemyY + 130});
        }

        // Repaint the screen to update the animation
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public ArrayList<int[]> getStars() {
        return stars;
    }

    public void setStars(ArrayList<int[]> stars) {
        this.stars = stars;
    }

    public ArrayList<int[]> getLasers() {
        return lasers;
    }

    public void setLasers(ArrayList<int[]> lasers) {
        this.lasers = lasers;
    }

    public ArrayList<int[]> getEnemyLasers() {
        return enemyLasers;
    }

    public void setEnemyLasers(ArrayList<int[]> enemyLasers) {
        this.enemyLasers = enemyLasers;
    }
}
