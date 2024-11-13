import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class HelicopterFlyingAnimation extends JPanel implements ActionListener {

    private Timer timer;
    private int helicopterX, helicopterY;
    private int rotorAngle;
    private ArrayList<int[]> clouds;
    private int sunY;
    private Random random;

    // Constructor
    public HelicopterFlyingAnimation() {
        random = new Random();
        helicopterX = -100;  // Start helicopter off-screen
        helicopterY = getHeight() / 2 - 50;  // Vertically center the helicopter
        sunY = 50; // Position of the sun
        rotorAngle = 0;

        clouds = new ArrayList<>();
        generateClouds();

        // Timer for animation (30ms delay)
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Helicopter Flying Animation - Code with AP");
        HelicopterFlyingAnimation panel = new HelicopterFlyingAnimation();

        // Set up the frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Override paintComponent to create the custom design
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background color (sky)
        g2d.setColor(new Color(135, 206, 250));  // Sky blue
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the sun
        drawSun(g2d);

        // Draw the clouds
        drawClouds(g2d);

        // Draw the helicopter
        drawHelicopter(g2d);

        // Draw title at the top center
        drawTitleText(g2d);

        // Draw bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Generate random clouds
    private void generateClouds() {
        for (int i = 0; i < 4; i++) {
            int x = random.nextInt(800);
            int y = random.nextInt(100) + 50;
            clouds.add(new int[]{x, y});
        }
    }

    // Draw the sun
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(getWidth() / 2 - 50, sunY, 100, 100);  // Sun in the center top
    }

    // Draw the clouds
    private void drawClouds(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int[] cloud : clouds) {
            g2d.fillOval(cloud[0], cloud[1], 120, 60);  // Clouds
        }
    }

    // Draw the helicopter
    private void drawHelicopter(Graphics2D g2d) {
        // Helicopter body
        g2d.setColor(Color.DARK_GRAY);
        int bodyX = helicopterX + getWidth() / 2 - 60;
        int bodyY = helicopterY + getHeight() / 2 - 30;
        g2d.fillRect(bodyX, bodyY, 120, 30);  // Helicopter body

        // Helicopter tail
        g2d.fillRect(bodyX - 50, bodyY + 10, 50, 10);  // Helicopter tail

        // Helicopter cockpit
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(bodyX + 90, bodyY - 10, 30, 30);  // Cockpit

        // Helicopter rotor (blades)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(bodyX + 60, bodyY - 10, bodyX + 60 + (int)(50 * Math.cos(Math.toRadians(rotorAngle))), 
                     bodyY - 10 + (int)(50 * Math.sin(Math.toRadians(rotorAngle))));
        g2d.drawLine(bodyX + 60, bodyY - 10, bodyX + 60 - (int)(50 * Math.cos(Math.toRadians(rotorAngle))),
                     bodyY - 10 - (int)(50 * Math.sin(Math.toRadians(rotorAngle))));

        // Helicopter rotor tail
        g2d.setColor(Color.BLACK);
        g2d.drawLine(bodyX - 50, bodyY + 15, bodyX - 50 - (int)(30 * Math.cos(Math.toRadians(rotorAngle))),
                     bodyY + 15 + (int)(30 * Math.sin(Math.toRadians(rotorAngle))));
        g2d.drawLine(bodyX - 50, bodyY + 15, bodyX - 50 + (int)(30 * Math.cos(Math.toRadians(rotorAngle))),
                     bodyY + 15 - (int)(30 * Math.sin(Math.toRadians(rotorAngle))));
    }

    // Draw title at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Helicopter Flying Animation";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
        g2d.drawString(text, x, y);
    }

    // Draw bottom text at the bottom center
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Update animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move helicopter horizontally
        helicopterX += 2;
        if (helicopterX > getWidth()) {
            helicopterX = -100;  // Reset helicopter position when it flies off screen
        }

        // Move clouds horizontally
        for (int[] cloud : clouds) {
            cloud[0] += 1;
            if (cloud[0] > getWidth()) {
                cloud[0] = -120;  // Reset clouds if they go off screen
            }
        }

        // Rotate the rotor blades
        rotorAngle += 10;
        if (rotorAngle >= 360) {
            rotorAngle = 0;
        }

        // Repaint to update animation
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getHelicopterY() {
        return helicopterY;
    }

    public void setHelicopterY(int helicopterY) {
        this.helicopterY = helicopterY;
    }

    public ArrayList<int[]> getClouds() {
        return clouds;
    }

    public void setClouds(ArrayList<int[]> clouds) {
        this.clouds = clouds;
    }

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
