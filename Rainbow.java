import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Rainbow extends JPanel implements ActionListener {
    
    private int cloudOffset = 0;  // Cloud movement
    private int sunOffset = 0;    // Sun rising animation
    public Timer timer;          // Timer for animation

    public Rainbow() {
        timer = new Timer(100, this);
        timer.start();  // Start the animation
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(135, 206, 235));  // Sky-blue background

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw border
        drawBorder(g2d, width, height);

        // Center of screen
        int centerX = width / 2;

        // Draw rainbow
        drawRainbow(g2d, centerX, height / 2);

        // Draw hills
        drawHills(g2d, centerX, height);

        // Draw additional elements (sun, clouds)
        drawSun(g2d, centerX, height - sunOffset);
        drawClouds(g2d, width);

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.setColor(Color.BLUE);
        g2d.drawString("Rainbow Over Hills", centerX - 220, 100);

        // Draw additional text
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Code with AP", centerX - 100, height - 50);
    }

    // Method to draw the rainbow
    private void drawRainbow(Graphics2D g2d, int centerX, int centerY) {
        int[] rainbowColors = {
            Color.RED.getRGB(), Color.ORANGE.getRGB(), Color.YELLOW.getRGB(),
            Color.GREEN.getRGB(), Color.CYAN.getRGB(), Color.BLUE.getRGB(), new Color(75, 0, 130).getRGB()  // Indigo
        };

        int rainbowWidth = 400;
        int rainbowHeight = 200;

        for (int i = 0; i < rainbowColors.length; i++) {
            g2d.setColor(new Color(rainbowColors[i]));
            g2d.setStroke(new BasicStroke(10));  // Thickness of each arc
            g2d.drawArc(centerX - rainbowWidth / 2, centerY - rainbowHeight + 50, rainbowWidth, rainbowHeight, 0, 180);

            rainbowWidth -= 40;  // Reduce the width and height to make inner arcs smaller
            rainbowHeight -= 20;
        }
    }

    // Method to draw hills
    private void drawHills(Graphics2D g2d, int centerX, int height) {
        g2d.setColor(new Color(34, 139, 34));  // Forest green color

        // Left hill
        g2d.fillArc(centerX - 400, height - 200, 400, 200, 0, 180);

        // Right hill
        g2d.fillArc(centerX, height - 200, 400, 200, 0, 180);

        // Center hill
        g2d.setColor(new Color(0, 100, 0));  // Darker green
        g2d.fillArc(centerX - 200, height - 250, 400, 250, 0, 180);
    }

    // Method to draw the sun
    private void drawSun(Graphics2D g2d, int centerX, int height) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(centerX + 150, height - 450 + sunOffset, 100, 100);  // Sun rising/falling animation
    }

    // Method to draw moving clouds
    private void drawClouds(Graphics2D g2d, int width) {
        g2d.setColor(Color.WHITE);

        // Cloud 1 (left)
        g2d.fillOval(100 + cloudOffset, 80, 100, 50);
        g2d.fillOval(150 + cloudOffset, 70, 100, 60);
        g2d.fillOval(200 + cloudOffset, 80, 100, 50);

        // Cloud 2 (right)
        g2d.fillOval(width - 300 - cloudOffset, 100, 120, 60);
        g2d.fillOval(width - 250 - cloudOffset, 90, 120, 70);
        g2d.fillOval(width - 200 - cloudOffset, 100, 120, 60);
    }

    // Method to draw a decorative border
    private void drawBorder(Graphics2D g2d, int width, int height) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRect(5, 5, width - 10, height - 10);  // Draws a border inside the edges
    }

    // Animation update method (called every timer tick)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move clouds from left to right
        cloudOffset = (cloudOffset + 5) % getWidth();

        // Sun rising/falling animation
        sunOffset = (sunOffset + 1) % 100;

        repaint();  // Redraw the screen with updated positions
    }

    // Main method to launch the design
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rainbow Over Hills");
        Rainbow rainbow = new Rainbow();
        frame.add(rainbow);
        frame.setSize(800, 600);  // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setVisible(true);  // Make the window visible
    }
}
