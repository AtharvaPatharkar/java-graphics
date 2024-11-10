import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class FloatingIslandAnimation extends JPanel implements ActionListener {
    private Timer timer;
    private List<Point> clouds; // List to hold cloud positions
    private List<Point> birds; // List to hold bird positions
    private Random random; // Random object for generating positions
    private int islandY = 400; // Initial vertical position of the island (lower on the screen)
    private int islandSwing = 2; // Swing speed of the island
    private int islandWidth = 300; // Width of the island
    private int islandHeight = 80; // Height of the island

    // Constructor to set up the panel and timer
    public FloatingIslandAnimation() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(Color.CYAN); // Set background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer

        clouds = new ArrayList<>(); // Initialize clouds list
        birds = new ArrayList<>(); // Initialize birds list
        random = new Random();

        // Create initial cloud positions
        for (int i = 0; i < 5; i++) {
            clouds.add(new Point(random.nextInt(800), random.nextInt(200) + 50)); // Random cloud positions
        }

        // Create initial bird positions
        for (int i = 0; i < 3; i++) {
            birds.add(new Point(random.nextInt(800), random.nextInt(100) + 20)); // Random bird positions
        }
    }

    // Override paintComponent to draw the floating island scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Floating Island in the Sky", getWidth() / 2 - 200, 50);

        // Draw the clouds
        g.setColor(Color.WHITE);
        for (Point cloud : clouds) {
            g.fillOval(cloud.x, cloud.y, 80, 40);
            g.fillOval(cloud.x + 20, cloud.y - 20, 80, 40);
            g.fillOval(cloud.x + 40, cloud.y, 80, 40);
        }

        // Draw the floating island
        g.setColor(new Color(139, 69, 19)); // Brown color for the island
        g.fillRoundRect(getWidth() / 2 - islandWidth / 2, islandY, islandWidth, islandHeight, 20, 20); // Island base

        // Draw the grass on the island
        g.setColor(Color.GREEN);
        g.fillRect(getWidth() / 2 - islandWidth / 2, islandY + islandHeight - 10, islandWidth, 10); // Grass on the island

        // Draw the birds
        g.setColor(Color.BLACK);
        for (Point bird : birds) {
            g.drawLine(bird.x, bird.y, bird.x + 15, bird.y - 5); // Bird wings
            g.drawLine(bird.x + 15, bird.y - 5, bird.x + 30, bird.y); // Bird wings
        }

        // Draw the text at the bottom center
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 50);
    }

    // Action performed by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the island up and down
        if (islandY <= 390 || islandY >= 410) { // Adjusted for larger island
            islandSwing = -islandSwing; // Change direction
        }
        islandY += islandSwing; // Update island position

        // Update cloud positions
        for (int i = 0; i < clouds.size(); i++) {
            Point cloud = clouds.get(i);
            cloud.x -= 1; // Move clouds to the left
            if (cloud.x < -80) { // Reset cloud position
                cloud.x = getWidth();
                cloud.y = random.nextInt(200) + 50; // Random y position
            }
        }

        // Update bird positions
        for (int i = 0; i < birds.size(); i++) {
            Point bird = birds.get(i);
            bird.x += 3; // Move birds to the right
            if (bird.x > getWidth()) { // Reset bird position
                bird.x = -30; // Off screen to the left
                bird.y = random.nextInt(100) + 20; // Random y position
            }
        }

        repaint(); // Request to repaint the panel
    }

    // Main method to set up the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Floating Island Animation");
        FloatingIslandAnimation floatingIslandAnimation = new FloatingIslandAnimation();
        frame.add(floatingIslandAnimation);
        frame.pack(); // Adjust frame to preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true); // Make the frame visible
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public void setIslandHeight(int islandHeight) {
        this.islandHeight = islandHeight;
    }

    public int getIslandWidth() {
        return islandWidth;
    }

    public void setIslandWidth(int islandWidth) {
        this.islandWidth = islandWidth;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public List<Point> getBirds() {
        return birds;
    }

    public void setBirds(List<Point> birds) {
        this.birds = birds;
    }

    public List<Point> getClouds() {
        return clouds;
    }

    public void setClouds(List<Point> clouds) {
        this.clouds = clouds;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
