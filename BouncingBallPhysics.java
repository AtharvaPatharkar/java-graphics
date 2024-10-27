import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class BouncingBallPhysics extends JPanel implements ActionListener {
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Ball> balls; // List to hold multiple balls
    private Random random;

    public BouncingBallPhysics() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(new Color(130, 14, 15)); 
        timer = new Timer(16, this); // Timer for animation (16ms for ~60 FPS)
        timer.start();

        random = new Random();
        balls = new ArrayList<>();

        // Create multiple bouncing balls with random properties, all starting at the center
        for (int i = 0; i < 15; i++) {
            int radius = random.nextInt(30) + 5; 
            int x = getWidth() + 200 ; // Start horizontally in the center
            int y = getHeight() + 100; // Start vertically in the center
            int dx = random.nextInt(5) + 1; // Random horizontal speed
            int dy = random.nextInt(5) + 1; // Random vertical speed
            balls.add(new Ball(x, y, dx, dy, radius));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g); // Draw the title at the top
        drawBalls(g); // Draw the bouncing balls
        drawFooterText(g); // Draw footer text at the bottom
    }

    private void drawTitle(Graphics g) {
        // Title text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Bouncing Ball Physics Simulation", getWidth() / 2 - 230, 50);
    }

    private void drawBalls(Graphics g) {
        // Draw and animate all the balls
        for (Ball ball : balls) {
            ball.draw(g); // Draw each ball
            ball.move(getWidth(), getHeight()); // Move the ball and handle collision
        }
    }

    private void drawFooterText(Graphics g) {
        // Footer text
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Continuously repaint the screen to animate
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball Physics Simulation");
        BouncingBallPhysics simulation = new BouncingBallPhysics();
        frame.add(simulation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
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

    // Inner class to represent each bouncing ball
    class Ball {
        private int x, y; // Position of the ball
        private int dx, dy; // Speed of the ball in the x and y directions
        private int radius; // Radius of the ball
        private final double gravity = 0.5; // Gravity to simulate falling

        public Ball(int x, int y, int dx, int dy, int radius) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.radius = radius;
        }

        public void draw(Graphics g) {
            // Draw the ball as a circle
            g.setColor(Color.YELLOW);
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

        public void move(int panelWidth, int panelHeight) {
            // Update ball position with gravity effect
            x += dx;
            y += dy;

            // Add gravity effect to the vertical speed
            dy += gravity;

            // Check for collisions with the walls
            if (x - radius < 0 || x + radius > panelWidth) {
                dx = -dx; // Reverse horizontal direction when hitting left or right wall
            }

            if (y + radius > panelHeight) {
                dy = (int) (-dy * 0.8); // Reverse vertical direction when hitting the ground, with damping
                y = panelHeight - radius; // Correct position to prevent sinking below the ground
            }
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }
    }
}
