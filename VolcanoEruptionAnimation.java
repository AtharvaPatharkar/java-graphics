import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class VolcanoEruptionAnimation extends JPanel implements ActionListener {

    private Timer timer;
    private int volcanoBaseX, volcanoBaseY;
    private ArrayList<int[]> lavaParticles;
    private ArrayList<int[]> smokeClouds;
    private ArrayList<int[]> birds;
    private Random random;
    private int groundShakeOffset = 0;
    private int sunY; // Position of the sun

    // Constructor
    public VolcanoEruptionAnimation() {
        random = new Random();
        lavaParticles = new ArrayList<>();
        smokeClouds = new ArrayList<>();
        birds = new ArrayList<>();
        volcanoBaseX = 750; // Center the volcano horizontally
        volcanoBaseY = 750; // Center the volcano vertically

        sunY = 100; // Initial Y position of the sun

        // Generate initial particles, clouds, and birds
        generateLavaParticles();
        generateSmokeClouds();
        generateBirds();

        // Timer for animation
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Volcano Eruption Animation - Code with AP");
        VolcanoEruptionAnimation panel = new VolcanoEruptionAnimation();

        // Set up the frame
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Override paintComponent for custom design
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background color (sky)
        g2d.setColor(new Color(135, 206, 235)); // Sky blue
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the title at the top
        drawTitleText(g2d);

        // Draw the sun in the background
        drawSun(g2d);

        // Ground shake offset
        int offsetX = groundShakeOffset;

        // Draw the volcano
        drawVolcano(g2d, offsetX);

        // Draw erupting lava particles
        drawLavaParticles(g2d, offsetX);

        // Draw flowing lava down the volcano sides
        drawLavaFlow(g2d, offsetX);

        // Draw the smoke clouds
        drawSmokeClouds(g2d, offsetX);

        // Draw flying birds
        drawBirds(g2d);

        // Draw the bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Draw the title text at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 45));
        g2d.setColor(Color.BLACK);
        String text = "Volcano Eruption Animation";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 70;
        g2d.drawString(text, x, y);
    }

    // Draw the bottom text at the bottom center
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Draw the sun in the background
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(getWidth() / 2 - 50, sunY, 100, 100); // Centralized sun with changing Y position
    }

    // Draw the volcano
    private void drawVolcano(Graphics2D g2d, int offsetX) {
        g2d.setColor(new Color(139, 69, 19)); // Brown for the volcano
        int[] xPoints = {volcanoBaseX - 150 + offsetX, volcanoBaseX + offsetX, volcanoBaseX + 150 + offsetX};
        int[] yPoints = {volcanoBaseY, volcanoBaseY - 200, volcanoBaseY};
        g2d.fillPolygon(xPoints, yPoints, 3); // Volcano body

        // Draw lava pool at the top of the volcano
        g2d.setColor(Color.RED);
        g2d.fillOval(volcanoBaseX - 40 + offsetX, volcanoBaseY - 205, 80, 30); // Lava pool
    }

    // Generate random lava particles
    private void generateLavaParticles() {
        for (int i = 0; i < 15; i++) {
            int x = volcanoBaseX + random.nextInt(50) - 25;
            int y = volcanoBaseY - 200 + random.nextInt(20);
            int dx = random.nextInt(10) - 5;
            int dy = -random.nextInt(10) - 5;
            lavaParticles.add(new int[]{x, y, dx, dy});
        }
    }

    // Draw lava particles flying out of the volcano
    private void drawLavaParticles(Graphics2D g2d, int offsetX) {
        g2d.setColor(Color.RED);
        for (int[] particle : lavaParticles) {
            g2d.fillOval(particle[0] + offsetX, particle[1], 12, 12); // Larger lava particles
        }
    }

    // Draw lava flowing down the volcano
    private void drawLavaFlow(Graphics2D g2d, int offsetX) {
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(volcanoBaseX - 30 + offsetX, volcanoBaseY - 100, 60, 150); // Main lava stream
        g2d.fillRect(volcanoBaseX - 60 + offsetX, volcanoBaseY - 50, 20, 100);  // Left side lava flow
        g2d.fillRect(volcanoBaseX + 40 + offsetX, volcanoBaseY - 50, 20, 100);  // Right side lava flow
    }

    // Generate random smoke clouds
    private void generateSmokeClouds() {
        for (int i = 0; i < 5; i++) {
            int x = volcanoBaseX + random.nextInt(100) - 50;
            int y = volcanoBaseY - 250 + random.nextInt(50);
            smokeClouds.add(new int[]{x, y, 80, 40});
        }
    }

    // Draw smoke clouds expanding and rising
    private void drawSmokeClouds(Graphics2D g2d, int offsetX) {
        g2d.setColor(Color.GRAY);
        for (int[] cloud : smokeClouds) {
            g2d.fillOval(cloud[0] + offsetX, cloud[1], cloud[2], cloud[3]); // Smoke cloud
        }
    }

    // Generate flying birds
    private void generateBirds() {
        for (int i = 0; i < 3; i++) {
            int x = -random.nextInt(300); // Birds start off-screen
            int y = random.nextInt(200) + 50;
            birds.add(new int[]{x, y, random.nextInt(3) + 1}); // X, Y, Speed
        }
    }

    // Draw flying birds across the screen
    private void drawBirds(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        for (int[] bird : birds) {
            // Drawing birds as simple flying shapes
            int[] xPoints = {bird[0], bird[0] + 20, bird[0] + 10};
            int[] yPoints = {bird[1], bird[1], bird[1] - 10};
            g2d.fillPolygon(xPoints, yPoints, 3); // Draw a small triangle representing a bird
        }
    }

    // Update animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Animate the lava particles
        for (int[] particle : lavaParticles) {
            particle[0] += particle[2]; // Update X position
            particle[1] += particle[3]; // Update Y position

            // Reset particle if it falls off the screen
            if (particle[1] > getHeight() || particle[0] < 0 || particle[0] > getWidth()) {
                particle[0] = volcanoBaseX + random.nextInt(50) - 25;
                particle[1] = volcanoBaseY - 200 + random.nextInt(20);
                particle[2] = random.nextInt(10) - 5;
                particle[3] = -random.nextInt(10) - 5;
            }
        }

        // Animate the smoke clouds
        for (int[] cloud : smokeClouds) {
            cloud[1] -= 1; // Move the cloud upwards
            cloud[2] += 1; // Expand the cloud width
            cloud[3] += 1; // Expand the cloud height

            // Reset the cloud if it moves off the screen
            if (cloud[1] < -50) {
                cloud[1] = volcanoBaseY - 250 + random.nextInt(50); // Reset the cloud position if it moves off the top
                cloud[0] = volcanoBaseX + random.nextInt(100) - 50;
                cloud[2] = 80;
                cloud[3] = 40;
            }
        }

        // Animate the birds
        for (int[] bird : birds) {
            bird[0] += bird[2]; // Move bird to the right

            // Reset bird if it flies off the screen
            if (bird[0] > getWidth()) {
                bird[0] = -random.nextInt(300); // Reset bird to fly again from left
                bird[1] = random.nextInt(200) + 50; // Randomize Y position
                bird[2] = random.nextInt(3) + 1; // Randomize speed
            }
        }

        // Simulate ground shake by adjusting the X-offset
        groundShakeOffset = (random.nextInt(7) - 3);

        // Sun movement (slow upward)
        sunY -= 1;
        if (sunY < -100) {
            sunY = 100; // Reset sun after it moves off the screen
        }

        // Repaint the screen to update animation
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getVolcanoBaseX() {
        return volcanoBaseX;
    }

    public void setVolcanoBaseX(int volcanoBaseX) {
        this.volcanoBaseX = volcanoBaseX;
    }

    public int getVolcanoBaseY() {
        return volcanoBaseY;
    }

    public void setVolcanoBaseY(int volcanoBaseY) {
        this.volcanoBaseY = volcanoBaseY;
    }

    public ArrayList<int[]> getLavaParticles() {
        return lavaParticles;
    }

    public void setLavaParticles(ArrayList<int[]> lavaParticles) {
        this.lavaParticles = lavaParticles;
    }

    public ArrayList<int[]> getSmokeClouds() {
        return smokeClouds;
    }

    public void setSmokeClouds(ArrayList<int[]> smokeClouds) {
        this.smokeClouds = smokeClouds;
    }

    public ArrayList<int[]> getBirds() {
        return birds;
    }

    public void setBirds(ArrayList<int[]> birds) {
        this.birds = birds;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
