import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class UnderwaterSubmarineScene extends JPanel implements ActionListener {
    private Timer timer;
    private int submarineX, submarineY; // Submarine position
    @SuppressWarnings("FieldMayBeFinal")
    private List<Fish> fishes; // List to hold multiple fish
    @SuppressWarnings("FieldMayBeFinal")
    private List<Bubble> bubbles; // List to hold bubbles
    private Random random; // Random for positioning fish and bubbles

    public UnderwaterSubmarineScene() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(new Color(0, 102, 204)); // Set underwater background color
        timer = new Timer(20, this); // Timer for animation
        timer.start(); // Start the timer

        random = new Random();
        // Submarine starts at the bottom-center of the screen
        submarineX = getWidth() / 2 - 100; // Center submarine horizontally
        submarineY = getHeight() - 150; // Position submarine near the bottom

        fishes = new ArrayList<>();
        bubbles = new ArrayList<>();

        // Initialize fish and bubble positions
        for (int i = 0; i < 5; i++) {
            int fishX = random.nextInt(800);
            int fishY = random.nextInt(600);
            fishes.add(new Fish(fishX, fishY));
        }

        for (int i = 0; i < 15; i++) {
            int bubbleX = random.nextInt(800);
            int bubbleY = random.nextInt(600);
            bubbles.add(new Bubble(bubbleX, bubbleY));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSea(g); // Draw sea background
        drawSubmarine(g); // Draw submarine
        drawFishes(g); // Draw moving fish
        drawBubbles(g); // Draw moving bubbles
        drawTitle(g); // Draw title
        drawFooterText(g); // Draw footer text
    }

    private void drawSea(Graphics g) {
        // Draw the sea with different blue shades to add depth
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint seaGradient = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 153));
        g2d.setPaint(seaGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSubmarine(Graphics g) {
        // Submarine is drawn near the bottom center of the screen
        submarineX = getWidth() / 2 - 100; // Adjust submarine X position to stay centered
        submarineY = getHeight() - 150; // Keep submarine near the bottom of the panel

        g.setColor(Color.GRAY);
        g.fillRoundRect(submarineX, submarineY, 200, 80, 20, 20); // Submarine body
        g.setColor(Color.DARK_GRAY);
        g.fillOval(submarineX + 150, submarineY + 20, 50, 40); // Submarine window
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(submarineX + 80, submarineY - 30, 40, 30); // Submarine periscope
        g.setColor(Color.BLACK);
        g.fillOval(submarineX + 160, submarineY + 30, 20, 20); // Window detail
    }

    private void drawFishes(Graphics g) {
        for (Fish fish : fishes) {
            fish.draw(g); // Draw each fish
            fish.move(); // Move fish
        }
    }

    private void drawBubbles(Graphics g) {
        for (Bubble bubble : bubbles) {
            bubble.draw(g); // Draw each bubble
            bubble.move(); // Move bubble
        }
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Underwater Submarine Scene", getWidth() / 2 - 200, 50); // Title at the top
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 50); // Footer text at the bottom
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Repaint to animate the scene
        submarineX += 1; // Move the submarine slowly to the right
        if (submarineX > getWidth()) {
            submarineX = -200; // Reset submarine position if it goes off-screen
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Underwater Submarine Scene");
        UnderwaterSubmarineScene scene = new UnderwaterSubmarineScene();
        frame.add(scene);
        frame.pack(); // Adjust frame to preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true); // Make the frame visible
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

    // Inner class to represent a Fish
    private class Fish {
        private int fishX, fishY; // Fish position
        private final int FISH_SIZE = 30; // Size of the fish

        public Fish(int x, int y) {
            fishX = x;
            fishY = y;
        }

        public void draw(Graphics g) {
            g.setColor(Color.ORANGE);
            g.fillOval(fishX, fishY, FISH_SIZE, FISH_SIZE / 2); // Draw fish body
            g.setColor(Color.BLACK);
            g.fillOval(fishX + FISH_SIZE - 5, fishY + 5, 5, 5); // Draw fish eye
        }

        public void move() {
            fishX -= 2; // Move fish to the left
            if (fishX < -FISH_SIZE) {
                fishX = getWidth(); // Reset fish position when it goes off-screen
            }
        }

        public int getFishY() {
            return fishY;
        }

        public void setFishY(int fishY) {
            this.fishY = fishY;
        }
    }

    // Inner class to represent a Bubble
    private class Bubble {
        private int bubbleX, bubbleY; // Bubble position
        private final int BUBBLE_SIZE = 10; // Size of the bubble

        public Bubble(int x, int y) {
            bubbleX = x;
            bubbleY = y;
        }

        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.drawOval(bubbleX, bubbleY, BUBBLE_SIZE, BUBBLE_SIZE); // Draw bubble
        }

        public void move() {
            bubbleY -= 1; // Move bubble upward
            if (bubbleY < -BUBBLE_SIZE) {
                bubbleY = getHeight(); // Reset bubble position when it goes off-screen
            }
        }

        public int getBubbleX() {
            return bubbleX;
        }

        public void setBubbleX(int bubbleX) {
            this.bubbleX = bubbleX;
        }

        public int getBubbleY() {
            return bubbleY;
        }

        public void setBubbleY(int bubbleY) {
            this.bubbleY = bubbleY;
        }
    }
}
