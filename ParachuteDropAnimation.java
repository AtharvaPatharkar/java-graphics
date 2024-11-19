import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class ParachuteDropAnimation extends JPanel implements ActionListener {

    private Timer timer;
    private int parachuteX;
    private int parachuteY;
    private int parachuteSpeed = 2; // Speed of parachute drop
    private int swayOffset = 0; // For swaying effect
    private ArrayList<int[]> clouds; // Cloud positions
    private ArrayList<int[]> birds;  // Bird positions
    private Random random;
    private int cloudFloatingOffset = 0; // For floating clouds up and down

    // Constructor
    public ParachuteDropAnimation() {
        random = new Random();
        parachuteX = getWidth() / 2 - 25; // Center the parachute horizontally
        parachuteY = -100; // Start off-screen
        clouds = new ArrayList<>();
        birds = new ArrayList<>();
        generateClouds();
        generateBirds();

        // Setting up the timer for animation (refresh every 30ms)
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Parachute Drop Animation - Code with AP");
        ParachuteDropAnimation panel = new ParachuteDropAnimation();

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

        // Draw sky gradient background
        drawSky(g2d);

        // Draw the sun in the top right corner
        drawSun(g2d);

        // Draw the clouds
        drawClouds(g2d);

        // Draw birds flying
        drawBirds(g2d);

        // Draw the parachute with the person
        drawParachute(g2d);

        // Draw the title at the top center
        drawTitleText(g2d);

        // Draw the bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Method to generate random clouds
    private void generateClouds() {
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(800);
            int y = random.nextInt(300);
            clouds.add(new int[]{x, y});
        }
    }

    // Method to generate random birds
    private void generateBirds() {
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(800);
            int y = random.nextInt(150) + 50; // Birds higher in the sky
            birds.add(new int[]{x, y});
        }
    }

    // Method to draw sky with gradient
    private void drawSky(Graphics2D g2d) {
        GradientPaint skyGradient = new GradientPaint(0, 0, new Color(135, 206, 235), 0, getHeight(), new Color(255, 255, 255));
        g2d.setPaint(skyGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Method to draw the sun
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(getWidth() - 120, 50, 100, 100); // Sun in top right corner
    }

    // Method to draw clouds with floating effect
    private void drawClouds(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int[] cloud : clouds) {
            g2d.fillOval(cloud[0], cloud[1] + cloudFloatingOffset, 100, 60); // Draw each cloud with floating effect
        }
    }

    // Method to draw birds flying
    private void drawBirds(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        for (int[] bird : birds) {
            g2d.drawArc(bird[0], bird[1], 20, 10, 0, -180); // Draw bird wings (simple arcs)
            g2d.drawArc(bird[0] + 20, bird[1], 20, 10, 0, 180); // Another wing
        }
    }

    // Method to draw the parachute and person with swaying effect
    private void drawParachute(Graphics2D g2d) {
        int swayAmplitude = 10; // Parachute sway amplitude
        int swayX = (int) (Math.sin(Math.toRadians(swayOffset)) * swayAmplitude);

        // Draw the parachute canopy
        g2d.setColor(Color.RED);
        g2d.fillArc(parachuteX + swayX + getWidth() / 2 - 50, parachuteY + 50, 100, 50, 0, 180);

        // Draw the strings of the parachute
        g2d.setColor(Color.BLACK);
        g2d.drawLine(parachuteX + swayX + getWidth() / 2 - 50, parachuteY + 75, parachuteX + swayX + getWidth() / 2 - 20, parachuteY + 100);
        g2d.drawLine(parachuteX + swayX + getWidth() / 2 + 50, parachuteY + 75, parachuteX + swayX + getWidth() / 2 + 20, parachuteY + 100);

        // Draw the person hanging from the parachute
        g2d.setColor(Color.BLUE);
        g2d.fillRect(parachuteX + swayX + getWidth() / 2 - 10, parachuteY + 100, 20, 40); // Body
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(parachuteX + swayX + getWidth() / 2 - 10, parachuteY + 80, 20, 20); // Head

        // Draw the legs
        g2d.setColor(Color.BLACK);
        g2d.drawLine(parachuteX + swayX + getWidth() / 2 - 5, parachuteY + 140, parachuteX + swayX + getWidth() / 2 - 10, parachuteY + 160); // Left leg
        g2d.drawLine(parachuteX + swayX + getWidth() / 2 + 5, parachuteY + 140, parachuteX + swayX + getWidth() / 2 + 10, parachuteY + 160); // Right leg
    }

    // Method to draw the title text at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Parachute Drop Animation";
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
        // Move the parachute down the screen
        parachuteY += parachuteSpeed;
        if (parachuteY > getHeight()) {
            parachuteY = -100; // Reset parachute to top of screen
        }

        // Move clouds across the screen
        for (int[] cloud : clouds) {
            cloud[0] += 1; // Move cloud horizontally
            if (cloud[0] > getWidth()) {
                cloud[0] = -100; // Reset cloud to the left
            }
        }

        // Make clouds float up and down
        cloudFloatingOffset = (int) (Math.sin(Math.toRadians(parachuteY)) * 5);

        // Move birds across the screen
        for (int[] bird : birds) {
            bird[0] += 2; // Birds fly faster
            if (bird[0] > getWidth()) {
                bird[0] = -50; // Reset bird to the left
            }
        }

        // Update sway offset for the parachute sway animation
        swayOffset += 2;
        if (swayOffset > 360) {
            swayOffset = 0; // Reset the sway cycle
        }

        // Repaint the screen to update the animation
        repaint();
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getParachuteX() {
        return parachuteX;
    }

    public void setParachuteX(int parachuteX) {
        this.parachuteX = parachuteX;
    }

    public void setParachuteSpeed(int parachuteSpeed) {
        this.parachuteSpeed = parachuteSpeed;
    }

    public void setClouds(ArrayList<int[]> clouds) {
        this.clouds = clouds;
    }

    public void setBirds(ArrayList<int[]> birds) {
        this.birds = birds;
    }
}
