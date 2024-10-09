import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AnimatedCartoonCharacters extends JPanel implements ActionListener {

    private final Timer timer;
    private int bounceOffset = 0; // Offset for the bouncing effect
    private int direction = 1; // 1 for down, -1 for up
    private int character1X = 300; // Initial X position for character 1
    private int character2X = 100; // Initial X position for character 2
    private int moveDirection1 = 1; // Movement direction for character 1
    private int moveDirection2 = 1; // Movement direction for character 2

    public AnimatedCartoonCharacters() {
        // Set up a timer for animation
        timer = new Timer(20, this);
        timer.start();
    }

    // Override paintComponent to draw the cartoon characters
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the border
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, getWidth() - 20, getHeight() - 20); // Border around the panel

        // Set background color
        this.setBackground(Color.CYAN); // Light blue for sky

        // Draw the first character (right side)
        drawCharacter(g, character1X, 250 + bounceOffset, "AP");

        // Draw the second character (left side)
        drawCharacter(g, character2X, 250 + bounceOffset, "AP");
    }

    // Method to draw a cartoon character at a specified position
    private void drawCharacter(Graphics g, int x, int y, String text) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the head
        g2d.setColor(Color.YELLOW); // Color of the head
        g2d.fillOval(x, y, 200, 200); // Head

        // Draw the eyes (looking left or right)
        g2d.setColor(Color.WHITE); // Eye whites
        g2d.fillOval(x + 50, y + 50, 50, 30); // Left eye
        g2d.fillOval(x + 100, y + 50, 50, 30); // Right eye

        // Draw pupils
        g2d.setColor(Color.BLACK); // Pupils
        g2d.fillOval(x + 65, y + 60, 20, 20); // Left pupil
        g2d.fillOval(x + 115, y + 60, 20, 20); // Right pupil

        // Draw the mouth
        g2d.setColor(Color.RED); // Mouth color
        g2d.drawArc(x + 50, y + 100, 100, 50, 0, -180); // Smiling mouth

        // Draw the body
        g2d.setColor(Color.BLUE); // Body color
        g2d.fillRect(x + 50, y + 200, 100, 150); // Body

        // Draw arms
        g2d.setColor(Color.YELLOW); // Arm color
        g2d.fillRect(x + 30, y + 200, 50, 20); // Left arm
        g2d.fillRect(x + 120, y + 200, 50, 20); // Right arm

        // Draw legs
        g2d.setColor(Color.YELLOW); // Leg color
        g2d.fillRect(x + 50, y + 350, 40, 100); // Left leg
        g2d.fillRect(x + 110, y + 350, 40, 100); // Right leg

        // Draw shoes
        g2d.setColor(Color.BLACK); // Shoe color
        g2d.fillRect(x + 50, y + 450, 40, 10); // Left shoe
        g2d.fillRect(x + 110, y + 450, 40, 10); // Right shoe

        // Draw the text "Designed by AP" below the character
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x + 50, y + 600); // Character label
    }

    // Method to handle the animation logic
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the bounce offset
        bounceOffset += direction * 2; // Move up or down by 2 pixels

        // Reverse direction if the character hits the limit
        if (bounceOffset > 20 || bounceOffset < -20) {
            direction *= -1; // Change direction
        }

        // Move characters left and right
        character1X += moveDirection1; // Move character 1
        character2X += moveDirection2; // Move character 2

        // Reverse direction if the characters hit the panel edges
        if (character1X < 50 || character1X > getWidth() - 250) {
            moveDirection1 *= -1; // Change direction for character 1
        }
        if (character2X < 50 || character2X > getWidth() - 250) {
            moveDirection2 *= -1; // Change direction for character 2
        }

        // Repaint the component
        repaint();
    }

    // Main function to set up the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Cartoon Characters");
        AnimatedCartoonCharacters cartoonPanel = new AnimatedCartoonCharacters();

        frame.add(cartoonPanel);
        frame.setSize(800, 700); // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
