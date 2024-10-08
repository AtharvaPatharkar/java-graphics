import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class AnimatedBeachScene extends JPanel implements ActionListener {

    private final Timer timer;
    private int sunX = 50; // Initial x position of the sun
    private int sunY = 100; // Y position of the sun
    private int waveOffset = 0; // Offset for the wave animation
    private int waveDirection = 1; // 1 for up, -1 for down
    private final int palmTreeX1 = 200; // X position for the first palm tree
    private final int palmTreeX2 = 600; // X position for the second palm tree
    private final int palmTreeHeight = 200; // Height of palm trees
    private final int ballX = 400; // X position of beach ball
    private int ballY = 400; // Y position of beach ball
    private int ballDirection = 1; // 1 for down, -1 for up
    private int cloudX; // X position of clouds
    private final int cloudY = 50; // Final Y position of clouds
    private final Random rand = new Random(); // Random generator for clouds

    public AnimatedBeachScene() {
        // Set up a timer for animation
        timer = new Timer(20, this);
        timer.start();

        // Initialize cloud position
        initializeCloudPosition();
    }

    // Method to initialize the cloud position
    private void initializeCloudPosition() {
        // Ensure cloudX is initialized only if the width is positive
        if (getWidth() > 0) {
            cloudX = rand.nextInt(getWidth());
        }
    }

    // Override paintComponent to draw the beach scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color to light blue for the sky
        this.setBackground(new Color(135, 206, 235)); // Sky blue

        // Draw the clouds
        drawClouds(g, cloudX, cloudY);

        // Draw the sun
        drawSun(g, sunX, sunY);

        // Draw the beach
        drawBeach(g);

        // Draw the waves
        drawWaves(g);

        // Draw palm trees
        drawPalmTree(g, palmTreeX1, getHeight() - 100 - palmTreeHeight);
        drawPalmTree(g, palmTreeX2, getHeight() - 100 - palmTreeHeight);
        drawPalmTree(g, palmTreeX1 + 100, getHeight() - 100 - palmTreeHeight); // Additional tree
        drawPalmTree(g, palmTreeX2 - 100, getHeight() - 100 - palmTreeHeight); // Additional tree

        // Draw beach ball
        drawBeachBall(g, ballX, ballY);

        // Draw beach umbrella
        drawUmbrella(g, 500, getHeight() - 150);
        
        // Draw flip-flops
        drawFlipFlops(g, 350, getHeight() - 80);
    }

    // Method to draw the sun
    private void drawSun(Graphics g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 80, 80); // Draw the sun
    }

    // Method to draw the beach
    private void drawBeach(Graphics g) {
        g.setColor(new Color(194, 178, 128)); // Sand color
        g.fillRect(0, getHeight() - 100, getWidth(), 100); // Draw the beach
    }

    // Method to draw animated waves
    private void drawWaves(Graphics g) {
        g.setColor(Color.BLUE); // Wave color
        for (int i = 0; i < getWidth(); i += 20) {
            g.drawArc(i, getHeight() - 100 + waveOffset, 20, 20, 0, 180); // Draw arcs for waves
        }
    }

    // Method to draw a palm tree
    private void drawPalmTree(Graphics g, int x, int y) {
        g.setColor(new Color(139, 69, 19)); // Brown for trunk
        g.fillRect(x, y, 20, 100); // Draw trunk

        // Draw leaves
        g.setColor(Color.GREEN);
        int[] leafX = {x - 40, x + 10, x + 60}; // Leaf x coordinates
        int[] leafY = {y, y - 60, y}; // Leaf y coordinates
        g.fillPolygon(leafX, leafY, 3); // Left leaf

        leafX = new int[]{x - 40, x + 10, x + 60}; // Leaf x coordinates for the other side
        leafY = new int[]{y, y - 60, y}; // Leaf y coordinates
        g.fillPolygon(leafX, leafY, 3); // Right leaf
    }

    // Method to draw a beach ball
    private void drawBeachBall(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 40, 40); // Draw beach ball
        g.setColor(Color.BLUE);
        g.fillArc(x, y, 40, 40, 0, 180); // Half blue color
        g.setColor(Color.YELLOW);
        g.fillArc(x, y, 40, 40, 180, 180); // Half yellow color
    }

    // Method to draw clouds
    private void drawClouds(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 80, 50); // Draw cloud base
        g.fillOval(x + 20, y - 20, 70, 50); // Draw left puff
        g.fillOval(x + 40, y - 20, 70, 50); // Draw right puff
    }

    // Method to draw beach umbrella
    private void drawUmbrella(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillArc(x, y, 100, 100, 0, 180); // Draw umbrella top
        g.setColor(new Color(139, 69, 19)); // Brown for pole
        g.fillRect(x + 40, y + 20, 10, 60); // Draw umbrella pole
    }

    // Method to draw flip-flops
    private void drawFlipFlops(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 40, 20); // Draw left flip-flop
        g.fillOval(x + 50, y, 40, 20); // Draw right flip-flop
    }

    // Method to handle the animation logic
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the sun horizontally and vertically (slower movement)
        sunX += 1; // Move the sun to the right more slowly
        if (sunY < 150) {
            sunY += 1; // Move sun down while it's rising
        } else {
            sunY = 100; // Reset sun position after reaching the peak
        }

        // Reset sun position to create a looping effect
        if (sunX > getWidth()) {
            sunX = -80; // Reset sun position to start from the left
        }

        // Update the wave offset
        waveOffset += waveDirection; // Move waves up or down

        // Reverse wave direction at limits
        if (waveOffset > 10 || waveOffset < -10) {
            waveDirection *= -1; // Change direction
        }

        // Animate beach ball up and down
        ballY += ballDirection; // Move beach ball up or down
        if (ballY > 420 || ballY < 380) {
            ballDirection *= -1; // Change direction for the ball
        }

        // Animate clouds
        cloudX += 1; // Move clouds to the right
        if (cloudX > getWidth()) {
            cloudX = -100; // Reset cloud position to start from the left
        }

        // Repaint the component
        repaint();
    }

    // Main function to set up the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Beach Scene");
        AnimatedBeachScene beachPanel = new AnimatedBeachScene();

        frame.add(beachPanel);
        frame.setSize(800, 600); // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Set cloud position after the frame is visible
        beachPanel.initializeCloudPosition();
    }
}
