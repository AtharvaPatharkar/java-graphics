import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FerrisWheelAnimation extends JPanel implements ActionListener {
    private Timer timer;
    private double angle = 0; // Rotation angle for the Ferris wheel
    private final int wheelRadius = 150; // Radius of the Ferris wheel
    private final int gondolaRadius = 180; // Radius for gondola position
    private final int numGondolas = 8; // Number of gondolas
    private final Color[] gondolaColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK}; // Colors for gondolas
    private int sunY = 100; // Initial vertical position of the sun
    private int sunDirection = 1; // Direction of the sun's movement

    // Constructor to set up the panel and timer
    public FerrisWheelAnimation() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(Color.CYAN); // Set a base background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer
    }

    // Override paintComponent to draw the Ferris wheel and additional elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGradientBackground(g); // Draw gradient background

        // Draw the sun
        drawSun(g);

        // Draw clouds
        drawCloud(g, 100, 80);
        drawCloud(g, 300, 50);
        drawCloud(g, 500, 100);
        drawCloud(g, 700, 70);

        // Draw the title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Ferris Wheel Animation", getWidth() / 2 - 150, 50);

        // Draw the Ferris wheel
        drawFerrisWheel(g);

        // Draw the ground
        drawGround(g);

        // Draw the text at the bottom center
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 50);
    }

    // Method to draw gradient background
    private void drawGradientBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), Color.BLUE);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Method to draw the Ferris wheel with gondolas
    private void drawFerrisWheel(Graphics g) {
        int centerX = getWidth() / 2; // Center of the wheel (X)
        int centerY = getHeight() / 2; // Center of the wheel (Y)

        // Draw the Ferris wheel
        g.setColor(Color.GRAY);
        g.drawOval(centerX - wheelRadius, centerY - wheelRadius, wheelRadius * 2, wheelRadius * 2); // Draw wheel outline

        // Calculate and draw gondolas
        for (int i = 0; i < numGondolas; i++) {
            double gondolaAngle = angle + (i * 2 * Math.PI / numGondolas); // Calculate the angle for each gondola
            int gondolaX = (int) (centerX + gondolaRadius * Math.cos(gondolaAngle)); // X position of gondola
            int gondolaY = (int) (centerY + gondolaRadius * Math.sin(gondolaAngle)); // Y position of gondola
            g.setColor(gondolaColors[i % gondolaColors.length]); // Set gondola color
            g.fillRect(gondolaX - 15, gondolaY - 15, 30, 30); // Draw gondola as a rectangle
        }
    }

    // Method to draw the sun
    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW); // Sun color
        g.fillOval(getWidth() - 100, sunY, 80, 80); // Draw sun
    }

    // Method to draw clouds
    private void drawCloud(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 50, 30); // Cloud body
        g.fillOval(x + 20, y - 10, 50, 30); // Cloud body
        g.fillOval(x + 40, y, 50, 30); // Cloud body
    }

    // Method to draw ground
    private void drawGround(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, getHeight() - 100, getWidth(), 100); // Ground area
    }

    // Action performed by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.05; // Increment the angle for rotation
        sunY += sunDirection; // Move the sun up and down

        // Change sun direction when it reaches the top or bottom of the screen
        if (sunY >= 150) {
            sunDirection = -1; // Move up
        } else if (sunY <= 50) {
            sunDirection = 1; // Move down
        }

        repaint(); // Request to repaint the panel
    }

    // Main method to set up the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ferris Wheel Animation");
        FerrisWheelAnimation ferrisWheelAnimation = new FerrisWheelAnimation();
        frame.add(ferrisWheelAnimation);
        frame.pack(); // Adjust frame to preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true); // Make the frame visible
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
