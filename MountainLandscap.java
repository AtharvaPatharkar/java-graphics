import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class MountainLandscape extends JPanel {
    private int moonX = 50;  // Initial position of the moon (animated)
    private int moonY = 50;
    private int cloudX = 200; // Initial position of the cloud
    private int cloudY = 50;
    private int[] birdX = {500, 450, 550};  // Multiple birds' positions
    private int[] birdY = {80, 100, 120};
    private int treeSwing = 0;  // Tree sway offset for animation
    private boolean treeSwingRight = true;

    // Shooting stars
    private int shootingStarX = -100;
    private int shootingStarY = -100;
    private boolean shootingStarVisible = false;
    private Random random = new Random();

    public MountainLandscape() {
        // Timer for handling animation
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Update positions of elements to create movement
                moonX += 1;
                if (moonX > getWidth()) moonX = -100;  // Loop the moon across the screen

                cloudX += 2;
                if (cloudX > getWidth()) cloudX = -150;

                // Move each bird to the left
                for (int i = 0; i < birdX.length; i++) {
                    birdX[i] -= 3 + i;  // Birds move at different speeds
                    if (birdX[i] < -100) birdX[i] = getWidth() + 100;
                }

                // Tree swaying effect
                if (treeSwingRight) {
                    treeSwing += 1;
                    if (treeSwing >= 10) treeSwingRight = false;
                } else {
                    treeSwing -= 1;
                    if (treeSwing <= -10) treeSwingRight = true;
                }

                // Move the shooting star if visible
                if (shootingStarVisible) {
                    shootingStarX += 10;
                    shootingStarY += 5;  // Move diagonally
                    if (shootingStarX > getWidth() || shootingStarY > getHeight()) {
                        shootingStarVisible = false;  // Hide the star after it goes off-screen
                    }
                } else {
                    // Randomly trigger a new shooting star
                    if (random.nextInt(100) < 2) {  // 2% chance of a new shooting star
                        shootingStarX = random.nextInt(getWidth() - 100);
                        shootingStarY = random.nextInt(100);  // Random starting Y in upper sky
                        shootingStarVisible = true;
                    }
                }

                repaint();  // Repaint the screen to reflect new positions
            }
        }, 0, 50);  // Schedule task to run every 50ms
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color to a night sky
        setBackground(new Color(25, 25, 112));  // Midnight blue for night sky

        // Draw a border around the panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw mountains centered at the bottom (more mountains)
        g.setColor(new Color(105, 105, 105));  // Grey mountain
        int[] xPoints1 = {50, 200, 350};
        int[] yPoints1 = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPoints1, yPoints1, 3);

        g.setColor(new Color(119, 136, 153));  // Light slate grey mountain
        int[] xPoints2 = {150, 300, 450};
        int[] yPoints2 = {getHeight() - 100, getHeight() - 250, getHeight() - 100};
        g.fillPolygon(xPoints2, yPoints2, 3);

        g.setColor(new Color(169, 169, 169));  // Dark grey mountain
        int[] xPoints3 = {350, 500, 650};
        int[] yPoints3 = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPoints3, yPoints3, 3);

        // More mountains on the left and right sides
        g.setColor(new Color(160, 160, 160));  // Light grey mountain (left side)
        int[] xPointsLeft = {0, 100, 200};
        int[] yPointsLeft = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPointsLeft, yPointsLeft, 3);

        g.setColor(new Color(140, 140, 140));  // Dark grey mountain (right side)
        int[] xPointsRight = {600, 700, 800};
        int[] yPointsRight = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPointsRight, yPointsRight, 3);

        // Draw the animated moon (moving across the sky)
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(moonX, moonY, 80, 80);  // Moon's size is 80x80

        // Draw more stars in the night sky
        g.setColor(Color.WHITE);
        for (int i = 0; i < 30; i++) {  // 30 stars randomly distributed
            int starX = (int) (Math.random() * getWidth());
            int starY = (int) (Math.random() * 150);
            drawStar(g, starX, starY);
        }

        // Draw shooting star if visible
        if (shootingStarVisible) {
            g.setColor(Color.YELLOW);
            g.drawLine(shootingStarX, shootingStarY, shootingStarX + 15, shootingStarY + 7);  // Shooting star effect
        }

        // Draw clouds
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, cloudY, 100, 50);
        g.fillOval(cloudX + 50, cloudY - 20, 80, 40);
        g.fillOval(cloudX - 40, cloudY - 10, 70, 40);

        // Draw river flowing at the bottom
        g.setColor(new Color(0, 191, 255));  // Light blue for water
        g.fillRect(0, getHeight() - 50, getWidth(), 50);
        g.setColor(Color.BLUE);
        for (int i = 0; i < getWidth(); i += 30) {
            g.fillArc(i, getHeight() - 50 + (i % 60), 30, 30, 0, 180);  // Water flow effect
        }

        // Draw trees on the ground (with a swinging effect)
        g.setColor(new Color(34, 139, 34));  // Forest green for tree leaves
        drawTree(g, 100, getHeight() - 100, treeSwing);
        drawTree(g, 600, getHeight() - 100, -treeSwing);

        // Draw more birds flying
        g.setColor(Color.BLACK);
        for (int i = 0; i < birdX.length; i++) {
            drawBird(g, birdX[i], birdY[i]);
        }

        // Draw text "Code with AP"
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 24));
        String message = "Code with AP";
        FontMetrics fm = g.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(message)) / 2;
        int textY = getHeight() - 50;
        g.drawString(message, textX, textY - 20);

        // Draw title "Mountain Landscape" at the top
        String title = "Mountain Landscape";
        int titleX = (getWidth() - fm.stringWidth(title)) / 2;
        g.drawString(title, titleX, 30);
    }

    // Method to draw a simple tree (with a swaying effect)
    private void drawTree(Graphics g, int x, int y, int sway) {
        // Draw trunk
        g.setColor(new Color(139, 69, 19));  // Brown for tree trunk
        g.fillRect(x + sway, y, 20, 50);
        
        // Draw leaves (circles on top of the trunk)
        g.setColor(new Color(34, 139, 34));
        g.fillOval(x - 20 + sway, y - 30, 60, 60);
    }

    // Method to draw a bird
    private void drawBird(Graphics g, int x, int y) {
        g.drawArc(x, y, 20, 10, 0, 180);  // Left wing
        g.drawArc(x + 10, y, 20, 10, 0, 180);  // Right wing
    }

    // Method to draw a simple star (as a small filled oval)
    private void drawStar(Graphics g, int x, int y) {
        g.fillOval(x, y, 5, 5);
    }

    public static void main(String[] args) {
        // Create a JFrame and add the MountainLandscape panel to it
        JFrame frame = new JFrame("Mountain Landscape");
        MountainLandscape landscape = new MountainLandscape();
        frame.add(landscape);
        frame.setSize(800, 600);  // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  // Make the window visible
    }

    public int getCloudY() {
        return cloudY;
    }

    public void setCloudY(int cloudY) {
        this.cloudY = cloudY;
    }

    public int[] getBirdY() {
        return birdY;
    }

    public void setBirdY(int[] birdY) {
        this.birdY = birdY;
    }

    public int getMoonY() {
        return moonY;
    }

    public void setMoonY(int moonY) {
        this.moonY = moonY;
    }
}
