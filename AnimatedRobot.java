import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AnimatedRobot extends JPanel implements ActionListener {

    private int eyeOffset = 0;      // Blinking effect
    private int armOffset = 0;      // Arm movement
    private int legOffset = 0;      // Leg movement for walking
    private int bodyOffset = 0;     // Body bobbing for walking
    private int faceExpression = 0; // 0 - happy, 1 - neutral, 2 - sad
    public Timer timer;

    public AnimatedRobot() {
        timer = new Timer(100, this);
        timer.start();  // Start the animation
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);  // Background color

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw border
        drawBorder(g2d, width, height);

        // Center of screen
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw the robot in the center
        drawRobot(g2d, centerX, centerY + bodyOffset);

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.setColor(Color.BLUE);
        g2d.drawString("Robot Design", centerX - 170, 100);

        // Draw additional text
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Code with AP", centerX - 100, height - 50);
    }

    // Method to draw the robot
    private void drawRobot(Graphics2D g2d, int centerX, int centerY) {
        // Robot Head
        g2d.setColor(Color.GRAY);
        g2d.fillRect(centerX - 50, centerY - 150, 100, 100);  // Head

        // Robot Eyes (blinking effect)
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - 30, centerY - 120 + eyeOffset, 20, 20);  // Left eye
        g2d.fillOval(centerX + 10, centerY - 120 + eyeOffset, 20, 20);  // Right eye
        g2d.setColor(Color.BLACK);
        g2d.fillOval(centerX - 25, centerY - 115 + eyeOffset, 10, 10);  // Left pupil
        g2d.fillOval(centerX + 15, centerY - 115 + eyeOffset, 10, 10);  // Right pupil

        // Robot Facial Expression (mouth)
        g2d.setColor(Color.RED);
        switch (faceExpression) {
            case 0 -> // Happy expression
                g2d.drawArc(centerX - 20, centerY - 80, 40, 20, 0, -180);
            case 1 -> // Neutral expression
                g2d.drawLine(centerX - 20, centerY - 70, centerX + 20, centerY - 70);
            case 2 -> // Sad expression
                g2d.drawArc(centerX - 20, centerY - 65, 40, 20, 0, 180);
        }

        // Robot Body
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(centerX - 75, centerY - 50, 150, 150);  // Body

        // Robot Arms (animated movement)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(centerX - 125 - armOffset, centerY - 40, 50, 20);  // Left arm
        g2d.fillRect(centerX + 75 + armOffset, centerY - 40, 50, 20);   // Right arm

        // Robot Legs (walking movement)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(centerX - 50, centerY + 100 - legOffset, 40, 80);  // Left leg (walking up/down)
        g2d.fillRect(centerX + 10, centerY + 100 + legOffset, 40, 80);  // Right leg (walking up/down)

        // Draw bolts on body
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(centerX - 30, centerY + 20, 15, 15);  // Left bolt
        g2d.fillOval(centerX + 15, centerY + 20, 15, 15);  // Right bolt

        // Robot Antenna
        g2d.setColor(Color.RED);
        g2d.fillRect(centerX - 5, centerY - 180, 10, 30);  // Antenna stick
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(centerX - 10, centerY - 200, 20, 20);  // Antenna ball
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
        // Toggle eye blinking effect
        eyeOffset = (eyeOffset == 0) ? 5 : 0;

        // Animate arm movement (swinging arms)
        armOffset = (armOffset + 5) % 20;

        // Animate walking (moving legs up and down)
        legOffset = (legOffset + 5) % 20;

        // Body moves up and down slightly to simulate walking
        bodyOffset = (bodyOffset + 2) % 10;

        // Cycle facial expressions: Happy -> Neutral -> Sad -> Repeat
        faceExpression = (faceExpression + 1) % 3;

        repaint();  // Redraw the screen with updated positions
    }

    // Main method to launch the robot design
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Robot Design");
        AnimatedRobot robot = new AnimatedRobot();
        frame.add(robot);
        frame.setSize(800, 600);  // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setVisible(true);  // Make the window visible
    }
}
