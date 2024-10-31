import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class DesertWithCactusAndDunes extends JPanel implements ActionListener {

    private Timer timer;
    private int sunX;
    private int sunY;
    private int[] dunes;
    private Random random;

    // Constructor
    public DesertWithCactusAndDunes() {
        random = new Random();
        sunX = getWidth() / 2 - 50; // Start sun near the middle
        sunY = 50; // Sun position at the top
        generateDunes();

        // Timer to control the animation (repainting every 30ms)
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Desert with Cactus and Dunes - Code with AP");
        DesertWithCactusAndDunes panel = new DesertWithCactusAndDunes();

        // Setting up the frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Override paintComponent to create the custom design
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background color to simulate desert sky
        g2d.setColor(new Color(255, 204, 153)); // Light orange for desert sky
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the sun
        drawSun(g2d);

        // Draw sand dunes
        drawDunes(g2d);

        // Draw multiple cactus
        drawMultipleCacti(g2d);

        // Draw the title at the top center
        drawTitleText(g2d);

        // Draw the bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Method to generate dunes
    private void generateDunes() {
        dunes = new int[]{100, getHeight() - 100, 200, getHeight() - 150, 400, getHeight() - 120, 600, getHeight() - 180, 
                          700, getHeight() - 100, 800, getHeight() - 150}; // Added more dunes points
    }

    // Method to draw the sun
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunX + getWidth() / 2 - 50, sunY, 100, 100); // Sun at the center top
    }

    // Method to draw the dunes
    private void drawDunes(Graphics2D g2d) {
        g2d.setColor(new Color(210, 180, 140)); // Sand color
        g2d.fillPolygon(dunes, new int[]{getHeight(), getHeight(), getHeight(), getHeight(), getHeight(), getHeight()}, dunes.length / 2); // Draw dune shapes
    }

    // Method to draw multiple cactus
    private void drawMultipleCacti(Graphics2D g2d) {
        // Cactus locations
        int[][] cactusLocations = {
            {getWidth() / 2 - 25, getHeight() - 160}, // Center cactus
            {100, getHeight() - 160},                 // Left cactus
            {700, getHeight() - 160},                 // Right cactus
            {300, getHeight() - 160},                 // Another cactus
        };

        g2d.setColor(new Color(34, 139, 34)); // Green for the cactus

        for (int[] cactusLocation : cactusLocations) {
            drawCactus(g2d, cactusLocation[0], cactusLocation[1]);
        }
    }

    // Method to draw a single cactus
    private void drawCactus(Graphics2D g2d, int cactusX, int cactusY) {
        // Main cactus body
        g2d.fillRect(cactusX, cactusY, 30, 100);

        // Left cactus arm
        g2d.fillRect(cactusX - 20, cactusY + 30, 20, 10);
        g2d.fillRect(cactusX - 30, cactusY + 30, 10, 40);

        // Right cactus arm
        g2d.fillRect(cactusX + 30, cactusY + 30, 20, 10);
        g2d.fillRect(cactusX + 50, cactusY + 30, 10, 40);
    }

    // Method to draw the title at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Desert with Cactus and Dunes";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
        g2d.drawString(text, x, y);
    }

    // Method to draw the bottom text at the bottom center
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Update the animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the sun across the sky
        sunX += 1;
        if (sunX > getWidth()) {
            sunX = -100; // Reset sun position if it moves off the screen
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

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
