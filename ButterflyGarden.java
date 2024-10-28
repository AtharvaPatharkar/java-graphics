import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class ButterflyGarden extends JPanel implements ActionListener {

    // Timer for animation
    private Timer timer;
    private final ArrayList<Butterfly> butterflies;
    private int flowerCount = 7; // Number of flowers in the garden
    private int cloudX = 0;  // Cloud movement

    // Constructor
    public ButterflyGarden() {
        // Initialize butterflies
        butterflies = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // Create 5 butterflies
            butterflies.add(new Butterfly());
        }

        // Setting up timer for animation (refresh every 50ms)
        timer = new Timer(50, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Butterfly Garden - Code with AP");
        ButterflyGarden panel = new ButterflyGarden();

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

        // Clear the background with a light sky color
        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the garden ground
        drawGarden(g2d);

        // Draw flowers in the garden
        drawFlowers(g2d);

        // Draw butterflies
        drawButterflies(g2d);

        // Draw clouds in the sky (moving from left to right)
        drawClouds(g2d);

        // Draw the title at the top center
        drawTitleText(g2d);

        // Add the bottom title text
        drawBottomText(g2d);
    }

    // Method to draw the garden (green ground)
    private void drawGarden(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    // Method to draw flowers at random positions
    private void drawFlowers(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        int flowerRadius = 20;
        Random rand = new Random();
        for (int i = 0; i < flowerCount; i++) {
            int flowerX = rand.nextInt(getWidth() - 2 * flowerRadius);
            int flowerY = getHeight() / 2 + rand.nextInt(getHeight() / 2 - flowerRadius);
            g2d.fillOval(flowerX, flowerY, flowerRadius, flowerRadius);  // Flower head
            g2d.setColor(Color.GREEN); // Stem
            g2d.fillRect(flowerX + flowerRadius / 2 - 2, flowerY + flowerRadius, 4, 20); // Stem
            g2d.setColor(Color.PINK); // Reset to flower color for next flower
        }
    }

    // Method to draw animated butterflies
    private void drawButterflies(Graphics2D g2d) {
        for (Butterfly butterfly : butterflies) {
            butterfly.draw(g2d, getWidth(), getHeight());
        }
    }

    // Method to draw moving clouds
    private void drawClouds(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(cloudX, 100, 100, 50);    // First cloud
        g2d.fillOval(cloudX + 150, 80, 130, 60); // Second cloud
        g2d.fillOval(cloudX + 350, 120, 90, 40); // Third cloud
    }

    // Method to draw title text at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Butterfly Garden";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
        g2d.drawString(text, x, y);
    }

    // Method to draw bottom title text at the bottom
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Method to update animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the clouds
        cloudX += 2;
        if (cloudX > getWidth()) cloudX = -200; // Loop clouds from left to right

        // Move each butterfly
        for (Butterfly butterfly : butterflies) {
            butterfly.updatePosition(getWidth(), getHeight());
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

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }

    // Butterfly class to manage individual butterfly movement and drawing
    class Butterfly {
        private int x, y;
        private boolean moveRight, moveDown;
        private Random rand;

        public Butterfly() {
            rand = new Random();
            this.x = rand.nextInt(600) - 300; // Random start position
            this.y = rand.nextInt(200) - 100;
            this.moveRight = rand.nextBoolean();
            this.moveDown = rand.nextBoolean();
        }

        // Draw the butterfly
        public void draw(Graphics2D g2d, int width, int height) {
            // Butterfly body
            g2d.setColor(Color.BLACK);
            g2d.fillRect(width / 2 - 10 + x, height / 2 - 50 + y, 20, 40);

            // Butterfly wings
            g2d.setColor(Color.ORANGE);
            g2d.fillOval(width / 2 - 30 + x, height / 2 - 60 + y, 40, 40); // Left wing
            g2d.fillOval(width / 2 - 10 + x, height / 2 - 60 + y, 40, 40); // Right wing

            g2d.setColor(Color.YELLOW);
            g2d.fillOval(width / 2 - 25 + x, height / 2 - 55 + y, 30, 30); // Inner left wing
            g2d.fillOval(width / 2 - 5 + x, height / 2 - 55 + y, 30, 30); // Inner right wing
        }

        // Update butterfly position
        public void updatePosition(int width, int height) {
            if (moveRight) {
                x += 5;
                if (x >= width / 2 - 50) moveRight = false;
            } else {
                x -= 5;
                if (x <= -width / 2 + 50) moveRight = true;
            }

            if (moveDown) {
                y += 3;
                if (y >= height / 2 - 60) moveDown = false;
            } else {
                y -= 3;
                if (y <= -height / 2 + 60) moveDown = true;
            }
        }

        public Random getRand() {
            return rand;
        }

        public void setRand(Random rand) {
            this.rand = rand;
        }
    }
}
