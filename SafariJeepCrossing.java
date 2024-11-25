import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class SafariJeepCrossing extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    private Timer timer;
    private int jeepXPosition;
    private int jeepSpeed = 5;
    private int roadYPosition;
    @SuppressWarnings("FieldMayBeFinal")
    private Color[] leafColors = {new Color(34, 139, 34), new Color(50, 205, 50), new Color(144, 238, 144)};
    private Random random = new Random();

    public SafariJeepCrossing() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 235)); // Sky color
        timer = new Timer(30, this); // Timer for animation
        timer.start();
        jeepXPosition = -200; // Start the jeep off-screen
        roadYPosition = screenHeight - 200; // Road position
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g);
        drawRoad(g);
        drawJeep(g);
        drawTrees(g);
        drawSun(g);
        drawFlowers(g);
        drawClouds(g);
        drawAnimals(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Safari Jeep Crossing", screenWidth / 2 - 150, 50); // Title at top-center
    }

    // Draw the road
    private void drawRoad(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, roadYPosition, screenWidth, 100); // Road
        g.setColor(Color.WHITE);
        for (int i = 0; i < screenWidth; i += 100) {
            g.fillRect(i + 40, roadYPosition + 30, 20, 40); // Road markings
        }
    }

    // Draw the jeep
    private void drawJeep(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(jeepXPosition, roadYPosition - 70, 100, 50); // Jeep body
        g.setColor(Color.BLACK);
        g.fillRect(jeepXPosition + 20, roadYPosition - 100, 60, 30); // Jeep roof
        g.setColor(Color.YELLOW);
        g.fillOval(jeepXPosition + 10, roadYPosition - 60, 30, 30); // Left wheel
        g.fillOval(jeepXPosition + 60, roadYPosition - 60, 30, 30); // Right wheel
    }

    // Draw trees
    private void drawTrees(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Brown for trunk
        g.fillRect(100, roadYPosition - 200, 30, 100); // Left tree trunk
        g.fillRect(400, roadYPosition - 200, 30, 100); // Right tree trunk

        g.setColor(Color.GREEN); // Green for leaves
        g.fillOval(70, roadYPosition - 250, 90, 90); // Left tree leaves
        g.fillOval(370, roadYPosition - 250, 90, 90); // Right tree leaves
    }

    // Draw the sun in the sky
    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(screenWidth - 150, 50, 100, 100); // Sun in the top-right corner
    }

    // Draw flowers in the garden
    private void drawFlowers(Graphics g) {
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(screenWidth - 100) + 50;
            int y = roadYPosition + 40 + random.nextInt(50);
            g.setColor(leafColors[random.nextInt(leafColors.length)]); // Random leaf color
            g.fillOval(x, y, 15, 15); // Flower
        }
    }

    // Draw clouds
    private void drawClouds(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(200, 100, 100, 60); // Cloud 1
        g.fillOval(250, 80, 100, 60);  // Cloud 1
        g.fillOval(160, 90, 100, 60);  // Cloud 1
        g.fillOval(500, 120, 120, 70); // Cloud 2
        g.fillOval(550, 100, 100, 60);  // Cloud 2
    }

    // Draw animals on the scene
    private void drawAnimals(Graphics g) {
        // Draw a simple animal (a circle for a quick representation)
        g.setColor(Color.ORANGE);
        g.fillOval(600, roadYPosition - 100, 40, 40); // Animal representation
        g.setColor(Color.BLACK);
        g.drawOval(600, roadYPosition - 100, 40, 40); // Animal outline
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the jeep across the screen
        if (jeepXPosition < screenWidth) {
            jeepXPosition += jeepSpeed; // Move jeep to the right
        } else {
            jeepXPosition = -200; // Reset jeep to start off-screen
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Safari Jeep Crossing");
        SafariJeepCrossing scene = new SafariJeepCrossing();
        frame.add(scene);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getJeepXPosition() {
        return jeepXPosition;
    }

    public void setJeepXPosition(int jeepXPosition) {
        this.jeepXPosition = jeepXPosition;
    }

    public int getJeepSpeed() {
        return jeepSpeed;
    }

    public void setJeepSpeed(int jeepSpeed) {
        this.jeepSpeed = jeepSpeed;
    }

    public int getRoadYPosition() {
        return roadYPosition;
    }

    public void setRoadYPosition(int roadYPosition) {
        this.roadYPosition = roadYPosition;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
