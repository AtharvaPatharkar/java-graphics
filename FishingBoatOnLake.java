import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FishingBoatOnLake extends JPanel implements ActionListener {

    private Timer timer;
    private int boatX;
    private int boatY;
    private int waveOffset = 0;
    private int sunY;
    private ArrayList<int[]> clouds;
    private Random random;

    // Constructor
    public FishingBoatOnLake() {
        random = new Random();
        boatX = getWidth() / 2 - 50; // Boat centered horizontally
        boatY = getHeight() / 2 + 50; // Boat on the lake
        sunY = 50; // Sun initial position

        clouds = new ArrayList<>();
        generateClouds();

        // Timer for animation
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fishing Boat on a Lake - Code with AP");
        FishingBoatOnLake panel = new FishingBoatOnLake();

        // Set up the frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Override paintComponent for custom design
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background color for sky and water
        g2d.setColor(new Color(135, 206, 235)); // Sky blue
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the sun
        drawSun(g2d);

        // Draw clouds
        drawClouds(g2d);

        // Draw the lake
        drawLake(g2d);

        // Draw the fishing boat
        drawBoat(g2d);

        // Draw fishing line and fish
        drawFishingLineAndFish(g2d);

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
        g2d.fillOval(getWidth() / 2 - 50, sunY, 100, 100); // Sun centered at the top
    }

    // Draw the clouds
    private void drawClouds(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int[] cloud : clouds) {
            g2d.fillOval(cloud[0], cloud[1], 120, 60); // Draw cloud
        }
    }

    // Draw the lake (water with waves)
    private void drawLake(Graphics2D g2d) {
        g2d.setColor(new Color(0, 119, 190)); // Lake blue
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2); // Water occupies the bottom half of the screen

        // Draw waves on the lake
        g2d.setColor(new Color(173, 216, 230)); // Lighter blue for waves
        for (int i = 0; i < getWidth(); i += 30) {
            g2d.drawArc(i + waveOffset, getHeight() / 2 + 20, 30, 10, 0, 180);
        }
    }

    // Draw the fishing boat
    private void drawBoat(Graphics2D g2d) {
        g2d.setColor(new Color(139, 69, 19)); // Brown boat
        int boatCenterX = getWidth() / 2 - 60;
        boatY = getHeight() / 2 + 50;
        g2d.fillRect(boatCenterX, boatY, 120, 20); // Boat body
        g2d.setColor(Color.BLACK);
        g2d.drawRect(boatCenterX, boatY, 120, 20); // Boat outline

        // Draw the mast
        g2d.setColor(Color.BLACK);
        g2d.drawLine(boatCenterX + 60, boatY, boatCenterX + 60, boatY - 40); // Mast
    }

    // Draw fishing line and fish
    private void drawFishingLineAndFish(Graphics2D g2d) {
        // Fishing line
        g2d.setColor(Color.BLACK);
        int startX = getWidth() / 2;
        int startY = getHeight() / 2 + 10;
        g2d.drawLine(startX, startY, startX, startY + 50); // Line from mast to water

        // Draw fish near the hook
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(startX - 10, startY + 60, 20, 10); // Fish near hook
    }

    // Draw the title at the top center
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        String text = "Fishing Boat on a Lake";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
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

    // Update the animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move sun vertically
        sunY += 1;
        if (sunY > getHeight() / 2) {
            sunY = -100; // Reset sun if it goes too low
        }

        // Move clouds horizontally
        for (int[] cloud : clouds) {
            cloud[0] += 1;
            if (cloud[0] > getWidth()) {
                cloud[0] = -120; // Reset clouds if they move off the screen
            }
        }

        // Animate waves on the lake
        waveOffset += 1;
        if (waveOffset > 30) {
            waveOffset = 0;
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

    public int getBoatX() {
        return boatX;
    }

    public void setBoatX(int boatX) {
        this.boatX = boatX;
    }

    public int getBoatY() {
        return boatY;
    }

    public void setBoatY(int boatY) {
        this.boatY = boatY;
    }

    public ArrayList<int[]> getClouds() {
        return clouds;
    }

    public void setClouds(ArrayList<int[]> clouds) {
        this.clouds = clouds;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
