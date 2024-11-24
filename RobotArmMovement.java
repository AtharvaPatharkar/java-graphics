import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RobotArmMovement extends JPanel implements ActionListener {
    private Timer timer;
    private double angle1 = 0; // Angle for the first joint
    private double angle2 = 0; // Angle for the second joint
    private int baseX = 1000; // Base X position of the robot arm (left side)
    private int baseY = 400; // Base Y position of the robot arm
    private int ballX = 1000; // Initial X position of the ball
    private int ballY = 450; // Initial Y position of the ball
    private int ballDirection = -2; // Ball movement direction (-2: left, 2: right)

    // Constructor to set up the panel and timer
    public RobotArmMovement() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(Color.LIGHT_GRAY); // Set background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer
    }

    // Override paintComponent to draw the robot arm and additional elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Robot Arm Movement", getWidth() / 2 - 150, 50);

        // Draw the robot arm
        drawRobotArm(g, baseX, baseY);

        // Draw the ball
        drawBall(g, ballX, ballY);

        // Draw the text at the bottom center
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 50);
    }

    // Method to draw the robot arm
    private void drawRobotArm(Graphics g, int baseX, int baseY) {
        // Calculate joint positions
        int length1 = 150; // Length of the first arm segment
        int length2 = 100; // Length of the second arm segment

        // Calculate the end of the first segment
        int jointX1 = (int) (baseX + length1 * Math.cos(angle1));
        int jointY1 = (int) (baseY - length1 * Math.sin(angle1));

        // Calculate the end of the second segment
        int jointX2 = (int) (jointX1 + length2 * Math.cos(angle1 + angle2));
        int jointY2 = (int) (jointY1 - length2 * Math.sin(angle1 + angle2));

        // Draw the segments
        g.setColor(Color.BLUE);
        g.drawLine(baseX, baseY, jointX1, jointY1); // Draw first segment
        g.drawLine(jointX1, jointY1, jointX2, jointY2); // Draw second segment

        // Draw joints
        g.setColor(Color.RED);
        g.fillOval(baseX - 5, baseY - 5, 10, 10); // Base joint
        g.fillOval(jointX1 - 5, jointY1 - 5, 10, 10); // First joint
        g.fillOval(jointX2 - 5, jointY2 - 5, 10, 10); // Second joint
    }

    // Method to draw the ball
    private void drawBall(Graphics g, int x, int y) {
        g.setColor(Color.YELLOW); // Set ball color
        g.fillOval(x - 15, y - 15, 30, 30); // Draw ball
    }

    // Action performed by the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update angles for joint movement
        angle1 += 0.02; // Increment angle1
        angle2 = Math.sin(angle1) / 2; // Oscillate angle2 for dynamic motion

        // Move the ball back and forth
        ballX += ballDirection * 2; // Move the ball based on direction
        if (ballX <= 300) { // If the ball hits the left limit
            ballDirection = 1; // Change direction to right
        } else if (ballX >= 600) { // If the ball hits the right limit
            ballDirection = -1; // Change direction to left
        }

        repaint(); // Request to repaint the panel
    }

    // Main method to set up the frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Arm Movement");
        RobotArmMovement robotArmMovement = new RobotArmMovement();
        frame.add(robotArmMovement);
        frame.pack(); // Adjust frame to preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true); // Make the frame visible
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getBaseY() {
        return baseY;
    }

    public void setBaseY(int baseY) {
        this.baseY = baseY;
    }

    public int getBaseX() {
        return baseX;
    }

    public void setBaseX(int baseX) {
        this.baseX = baseX;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
