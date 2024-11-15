import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LighthouseByTheSea extends JPanel implements ActionListener {

    // Timer for animation
    private Timer timer;
    private int waveOffset = 0;   // Movement for the water waves
    private int lighthouseLightAngle = 0;   // Movement for the lighthouse light

    // Constructor
    public LighthouseByTheSea() {
        // Setting up timer for animation (refresh every 50ms)
        timer = new Timer(50, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lighthouse by the Sea - Code with AP");
        LighthouseByTheSea panel = new LighthouseByTheSea();

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

        // Clear the background
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the ocean (animated waves)
        drawOcean(g2d);

        // Draw the lighthouse
        drawLighthouse(g2d);

        // Draw lighthouse light (animated rotation)
        drawLighthouseLight(g2d);

        // Draw some birds in the sky
        drawBirds(g2d);

        // Add title text at the bottom center
        drawTitleText(g2d);
    }

    // Method to draw the ocean with waves
    private void drawOcean(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        int waveHeight = 40;
        for (int i = 0; i < getWidth(); i += 100) {
            g2d.fillArc(i - waveOffset, getHeight() - 100, 100, waveHeight, 0, 180);
        }
    }

    // Method to draw the lighthouse
    private void drawLighthouse(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(getWidth() / 2 - 30, getHeight() / 2 - 200, 60, 200); // Lighthouse base
        g2d.setColor(Color.RED);
        g2d.fillRect(getWidth() / 2 - 35, getHeight() / 2 - 220, 70, 40); // Top of the lighthouse
    }

    // Method to draw the rotating lighthouse light
    private void drawLighthouseLight(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 0, 150)); // Yellow light with transparency
        int x = getWidth() / 2;
        int y = getHeight() / 2 - 220;
        g2d.fillArc(x - 60, y - 40, 120, 80, lighthouseLightAngle, 30); // Moving light
    }

    // Method to draw birds in the sky
    private void drawBirds(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawArc(100, 100, 40, 20, 0, 180); // Bird 1
        g2d.drawArc(150, 120, 50, 25, 0, 180); // Bird 2
        g2d.drawArc(600, 150, 40, 20, 0, 180); // Bird 3
    }

    // Method to draw title text at the bottom
    private void drawTitleText(Graphics2D g2d) {
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
        waveOffset = (waveOffset + 2) % 100; // Move waves to the left
        lighthouseLightAngle = (lighthouseLightAngle + 5) % 360; // Rotate the light
        repaint(); // Repaint to create animation effect
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
