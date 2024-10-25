import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BirdNestTree extends JPanel implements ActionListener {
    private Timer timer;
    private int sunY = 50; // Initial vertical position of the sun
    @SuppressWarnings("FieldMayBeFinal")
    private List<Bird> birds; // List of birds

    // Constructor to set up the panel and timer
    public BirdNestTree() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(Color.CYAN); // Set a base background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer
        birds = new ArrayList<>(); // Initialize bird list
        // Create multiple birds with random initial positions
        for (int i = 0; i < 3; i++) {
            birds.add(new Bird(100 + (i * 150), 300, 1)); // Create birds with different starting positions
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSky(g); // Draw sky background
        drawSun(g); // Draw sun
        drawCloud(g, 100, 80); // Draw clouds
        drawCloud(g, 500, 50); // Draw more clouds
        drawTree(g); // Draw tree
        drawNest(g); // Draw nest
        drawBirds(g); // Draw birds
        drawTitle(g); // Draw title
        drawFooterText(g); // Draw footer text
    }

    private void drawSky(Graphics g) {
        // Draw a gradient background for the sky
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, 0, getHeight(), Color.BLUE);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW); // Sun color
        g.fillOval(getWidth() - 100, 50, 80, 80); // Draw sun
    }

    private void drawCloud(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 50, 30); // Cloud body
        g.fillOval(x + 20, y - 10, 50, 30); // Cloud body
        g.fillOval(x + 40, y, 50, 30); // Cloud body
    }

    private void drawTree(Graphics g) {
        // Draw tree trunk
        g.setColor(new Color(139, 69, 19)); // Brown color for trunk
        g.fillRect(getWidth() / 2 - 40, getHeight() / 2 + 90 , 80, 150); // Trunk
        // Draw tree leaves
        g.setColor(Color.GREEN); // Green color for leaves
        g.fillOval(getWidth() / 2 - 120, getHeight() / 2 - 100, 240, 200); // Leafy part
        g.fillOval(getWidth() / 2 - 80, getHeight() / 2 - 140, 160, 160); // Top leaves
    }

    private void drawNest(Graphics g) {
        // Draw nest
        g.setColor(new Color(139, 69, 19)); // Brown color for nest
        g.fillOval(getWidth() / 2 - 20, getHeight() / 2 + 40, 40, 20); // Nest
        g.setColor(new Color(210, 180, 140)); // Light brown for nest lining
        g.fillOval(getWidth() / 2 - 15, getHeight() / 2 + 42, 30, 10); // Nest lining
    }

    private void drawBirds(Graphics g) {
        for (Bird bird : birds) {
            bird.draw(g); // Draw each bird
            bird.move(); // Move the bird
        }
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Bird Nest and Tree", getWidth() / 2 - 170, 50);
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 50);
    }

    // Action performed by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Request to repaint the panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bird Nest and Tree");
        BirdNestTree birdNestTreeDesign = new BirdNestTree();
        frame.add(birdNestTreeDesign);
        frame.pack(); // Adjust frame to preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true); // Make the frame visible
    }

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    // Inner class to represent a Bird
    private class Bird {
        private int x; // Bird's X position
        private int y; // Bird's Y position
        private int direction; // Direction of movement (1 = right, -1 = left)

        // Constructor to set up bird's initial position and direction
        public Bird(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        // Method to draw the bird
        public void draw(Graphics g) {
            g.setColor(Color.RED); // Color of the bird
            g.fillOval(x, y, 20, 20); // Bird body
            // Draw wings
            g.setColor(Color.YELLOW); // Color of the wings
            g.fillOval(x - 10, y, 15, 10); // Left wing
            g.fillOval(x + 20, y, 15, 10); // Right wing
        }

        // Method to move the bird
        public void move() {
            if (direction == 1) {
                x += 2; // Move right
                if (x >= getWidth()) {
                    x = -20; // Reset position to the left side
                }
            } else {
                x -= 2; // Move left
                if (x < -20) {
                    x = getWidth(); // Reset position to the right side
                }
            }
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
