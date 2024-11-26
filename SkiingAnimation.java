import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SkiingAnimation extends JPanel implements ActionListener {
    private int skiX = 200; // Initial x position of skier
    private int skiY = 0; // Initial y position of skier
    private Timer timer;

    // Constructor to set up the panel and timer
    public SkiingAnimation() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(Color.CYAN); // Set background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer
    }

    // Override paintComponent to draw the skiing scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Skiing Downhill Animation", getWidth() / 2 - 150, 50);


        // Draw the ground
        g.setColor(Color.GREEN);
        g.fillRect(0, getHeight() - 100, getWidth(), 100);

        // Draw the skier (a simple triangle for simplicity)
        g.setColor(Color.RED);
        int[] xPoints = {skiX, skiX - 20, skiX + 20}; // Skiing figure points
        int[] yPoints = {skiY, skiY + 50, skiY + 50};
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw some trees in the background
        g.setColor(Color.GREEN);
        g.fillRect(100, getHeight() - 140, 20, 40);
        g.fillRect(150, getHeight() - 160, 20, 60);
        g.fillRect(700, getHeight() - 140, 20, 40);
        g.fillRect(650, getHeight() - 160, 20, 60);


        
        // Draw the text
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("Code with AP", getWidth() / 2 - 50, 695);
    }

    // Action performed by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        skiY += 2; // Move skier downwards
        skiX += (int) (Math.sin(Math.toRadians(skiY / 5.0)) * 2); // Create a slight left-right movement
        if (skiY > getHeight()) {
            skiY = 0; // Reset the skier position when reaching the bottom
            skiX = 200; // Reset horizontal position
        }
        repaint(); // Request to repaint the panel
    }

    // Main method to set up the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Skiing Animation");
        SkiingAnimation skiingAnimation = new SkiingAnimation();
        frame.add(skiingAnimation);
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
