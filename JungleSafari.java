import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JungleSafari extends JPanel implements ActionListener {

    // Timer for animation
    private Timer timer;
    private int elephantX = -200; // Starting position for elephant (off-screen left)
    private int birdX = 0;        // Starting position for bird (center of screen)
    private int birdY = 100;      // Vertical position of bird
    private boolean moveBirdRight = true; // Control bird movement
    private boolean moveElephantRight = true; // Control elephant movement

    // Constructor
    public JungleSafari() {
        // Setting up the timer for animation (refresh every 50ms)
        timer = new Timer(50, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jungle Safari Scene - Code with AP");
        JungleSafari panel = new JungleSafari();

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

        // Clear the background with a sky color
        g2d.setColor(new Color(135, 206, 235)); // Sky blue color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw ground (jungle floor)
        drawJungleGround(g2d);

        // Draw trees
        drawTrees(g2d);

        // Draw the animated elephant
        drawElephant(g2d);

        // Draw birds flying across the sky
        drawBird(g2d);

        // Draw the title at the top center
        drawTitleText(g2d);

        // Add bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Method to draw the jungle ground
    private void drawJungleGround(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34)); // Forest green color for jungle floor
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    // Method to draw trees
    private void drawTrees(Graphics2D g2d) {
        int treeX = getWidth() / 2 - 200;
        int treeY = getHeight() / 2 - 150;

        // Tree trunk
        g2d.setColor(new Color(139, 69, 19)); // Brown for tree trunk
        g2d.fillRect(treeX + 50, treeY, 30, 150);

        // Tree leaves (circle on top of trunk)
        g2d.setColor(new Color(0, 100, 0)); // Dark green leaves
        g2d.fillOval(treeX, treeY - 50, 130, 130);

        // Adding additional trees
        g2d.fillOval(treeX + 300, treeY - 50, 130, 130);  // Second tree
        g2d.fillRect(treeX + 350, treeY, 30, 150);        // Second tree trunk
    }

    // Method to draw animated elephant walking
    private void drawElephant(Graphics2D g2d) {
        // Elephant body
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(elephantX + getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 100, 40, 40);

        // Elephant head
        g2d.fillOval(elephantX + getWidth() / 2 + 50, getHeight() / 2 - 70, 60, 60);

        // Elephant trunk
        g2d.fillRect(elephantX + getWidth() / 2 + 100, getHeight() / 2 - 30, 10, 40);

        // Elephant legs
        g2d.fillRect(elephantX + getWidth() / 2 - 80, getHeight() / 2 + 50, 20, 40);  // Left front leg
        g2d.fillRect(elephantX + getWidth() / 2 - 20, getHeight() / 2 + 50, 20, 40);  // Right front leg
        g2d.fillRect(elephantX + getWidth() / 2 + 60, getHeight() / 2 + 50, 20, 40);  // Left back leg
        g2d.fillRect(elephantX + getWidth() / 2 + 120, getHeight() / 2 + 50, 20, 40); // Right back leg
    }

    // Method to draw animated bird flying across the screen
    private void drawBird(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        int birdWidth = 50;
        int birdHeight = 20;

        // Draw bird body
        g2d.fillOval(birdX + getWidth() / 2 - birdWidth / 2, birdY, birdWidth, birdHeight);

        // Draw bird wings (moving up and down slightly)
        int wingY = birdY + (moveBirdRight ? 10 : -10); // Simple wing movement up and down
        g2d.drawLine(birdX + getWidth() / 2 - birdWidth / 2, wingY, birdX + getWidth() / 2, birdY);
        g2d.drawLine(birdX + getWidth() / 2, birdY, birdX + getWidth() / 2 + birdWidth / 2, wingY);
    }

    // Method to draw the title text at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Jungle Safari Scene";
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

    // Method to update the animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move elephant from left to right
        if (moveElephantRight) {
            elephantX += 5;
            if (elephantX >= getWidth() / 2) {
                moveElephantRight = false;
            }
        } else {
            elephantX -= 5;
            if (elephantX <= -getWidth() / 2) {
                moveElephantRight = true;
            }
        }

        // Move bird back and forth across the screen
        if (moveBirdRight) {
            birdX += 10;
            if (birdX >= getWidth() / 2 - 100) {
                moveBirdRight = false;
            }
        } else {
            birdX -= 10;
            if (birdX <= -getWidth() / 2) {
                moveBirdRight = true;
            }
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

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }
}
