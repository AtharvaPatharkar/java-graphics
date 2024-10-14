import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class CoralReefDesign extends JPanel implements ActionListener {

    // Timer for animation
    public Timer timer;
    private final ArrayList<Fish> fishList;
    private final ArrayList<Bubble> bubbles;
    private final ArrayList<Starfish> starfishList;
    private final ArrayList<Jellyfish> jellyfishList; // New element: jellyfish
    private final Random rand = new Random();

    public CoralReefDesign() {
        setPreferredSize(new Dimension(1200, 800)); // Larger screen size
        setBackground(new Color(0, 105, 148)); // Deep blue for underwater feel
        timer = new Timer(50, this); // 20 fps (50ms delay)
        fishList = new ArrayList<>();
        bubbles = new ArrayList<>();
        starfishList = new ArrayList<>();
        jellyfishList = new ArrayList<>(); // Initialize jellyfish list
        generateFish(10); // More fish
        generateBubbles(20); // More bubbles
        generateStarfish(5); // More starfish
        generateJellyfish(6); // New jellyfish elements
        timer.start();
    }

    // Fish class for swimming in both directions
    class Fish {
        int x, y, speed, size, direction;

        public Fish(int x, int y, int speed, int size, int direction) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.size = size;
            this.direction = direction; // 1 for right, -1 for left
        }

        public void move() {
            x += speed * direction; // Move left or right based on direction
            if (x > getWidth()) x = -size; // Reset to left if it moves out of right
            if (x < -size) x = getWidth(); // Reset to right if it moves out of left
        }

        public void draw(Graphics g) {
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, size * direction, size / 2); // Fish body (flipped for left)
            g.setColor(Color.BLACK);
            g.fillOval(x + (size - 15 * direction), y + (size / 6), 5, 5); // Eye
            Polygon tail = new Polygon();
            if (direction == 1) { // Right direction tail
                tail.addPoint(x, y + size / 4);
                tail.addPoint(x - (size / 3), y);
                tail.addPoint(x - (size / 3), y + size / 2);
            } else { // Left direction tail
                tail.addPoint(x + size, y + size / 4);
                tail.addPoint(x + size + (size / 3), y);
                tail.addPoint(x + size + (size / 3), y + size / 2);
            }
            g.setColor(Color.RED);
            g.fillPolygon(tail);
        }
    }

    // Bubble class for rising bubbles
    class Bubble {
        int x, y, size;

        public Bubble(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void move() {
            y -= 2; // Move bubble upward
            if (y < -size) y = getHeight() + size; // Reset to bottom
        }

        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.drawOval(x, y, size, size);
        }
    }

    // Starfish class for decoration
    class Starfish {
        int x, y, size;

        public Starfish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void draw(Graphics g) {
            g.setColor(Color.PINK);
            Polygon star = new Polygon();
            for (int i = 0; i < 5; i++) {
                star.addPoint(
                        (int) (x + size * Math.cos(i * 2 * Math.PI / 5)),
                        (int) (y + size * Math.sin(i * 2 * Math.PI / 5))
                );
            }
            g.fillPolygon(star);
        }
    }

    // Jellyfish class for floating upwards
    class Jellyfish {
        int x, y, size;

        public Jellyfish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void move() {
            y -= 1; // Slow upward movement
            if (y < -size) y = getHeight() + size; // Reset to bottom
        }

        public void draw(Graphics g) {
            g.setColor(new Color(173, 216, 230)); // Light blue color
            g.fillArc(x, y, size, size, 0, 180); // Jellyfish head
            g.setColor(new Color(0, 105, 148));
            for (int i = 0; i < 5; i++) {
                g.drawLine(x + (i * size / 4), y + size / 2, x + (i * size / 4), y + size); // Jellyfish tentacles
            }
        }
    }

    // Generate fish at random positions and random directions
    private void generateFish(int count) {
        for (int i = 0; i < count; i++) {
            int direction = rand.nextBoolean() ? 1 : -1; // Random direction
            fishList.add(new Fish(rand.nextInt(1200), rand.nextInt(600) + 100, rand.nextInt(3) + 1, rand.nextInt(60) + 30, direction));
        }
    }

    // Generate bubbles at random positions
    private void generateBubbles(int count) {
        for (int i = 0; i < count; i++) {
            bubbles.add(new Bubble(rand.nextInt(1200), rand.nextInt(800), rand.nextInt(30) + 10));
        }
    }

    // Generate starfish at random positions
    private void generateStarfish(int count) {
        for (int i = 0; i < count; i++) {
            starfishList.add(new Starfish(rand.nextInt(800) + 200, rand.nextInt(100) + 650, rand.nextInt(40) + 20));
        }
    }

    // Generate jellyfish at random positions
    private void generateJellyfish(int count) {
        for (int i = 0; i < count; i++) {
            jellyfishList.add(new Jellyfish(rand.nextInt(1000) + 100, rand.nextInt(400) + 400, rand.nextInt(50) + 30));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw title
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Underwater Coral Reef", 380, 50); // Title centered

        // Draw coral reefs (multiple, spread out)
        g.setColor(new Color(255, 87, 34));
        g.fillArc(100, 650, 300, 150, 0, 180); // Coral on the left
        g.fillArc(800, 680, 300, 150, 0, 180); // Coral on the right

        // Draw seaweed
        g.setColor(new Color(0, 200, 0));
        for (int i = 0; i < 12; i++) {
            g.fillRect(100 + i * 80, 600, 10, 200);
        }

        // Draw fish
        for (Fish fish : fishList) {
            fish.draw(g);
        }

        // Draw bubbles
        for (Bubble bubble : bubbles) {
            bubble.draw(g);
        }

        // Draw starfish
        for (Starfish starfish : starfishList) {
            starfish.draw(g);
        }

        // Draw jellyfish
        for (Jellyfish jellyfish : jellyfishList) {
            jellyfish.draw(g);
        }

        // Draw bottom text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Code with AP", 500, 750); // Text at the bottom

        // Draw border
        g.setColor(Color.WHITE);
        g.drawRect(20, 20, getWidth() - 40, getHeight() - 40); // Border
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move fish
        for (Fish fish : fishList) {
            fish.move();
        }

        // Move bubbles
        for (Bubble bubble : bubbles) {
            bubble.move();
        }

        // Move jellyfish
        for (Jellyfish jellyfish : jellyfishList) {
            jellyfish.move();
        }

        // Repaint the panel to reflect new positions
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Underwater Coral Reef Animation");
        CoralReefDesign coralReef = new CoralReefDesign();
        frame.add(coralReef);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
