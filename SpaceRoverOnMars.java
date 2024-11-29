import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SpaceRoverOnMars extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private Rover rover;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Obstacle> obstacles;

    public SpaceRoverOnMars() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK); // Space background

        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();

        rover = new Rover(screenWidth / 2, screenHeight / 2 + 100); // Initial position of the rover
        obstacles = new ArrayList<>();

        // Create obstacles on the Mars surface
        for (int i = 0; i < 5; i++) {
            obstacles.add(new Obstacle((int) (Math.random() * screenWidth), (int) (Math.random() * (screenHeight / 2 + 100))));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStars(g);
        drawTitle(g);
        rover.draw(g);
        drawObstacles(g);
        drawFooterText(g);
    }

    // Draw the stars in the background
    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * screenWidth);
            int y = (int) (Math.random() * screenHeight / 2);
            g.fillOval(x, y, 2, 2); // Draw stars
        }
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Space Rover on Mars", screenWidth / 2 - 150, 50); // Title at top-center
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    // Draw obstacles on the Mars surface
    private void drawObstacles(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rover.move();
        for (Obstacle obstacle : obstacles) {
            obstacle.move();
        }

        // Check for collision with obstacles
        for (Obstacle obstacle : obstacles) {
            if (rover.getBounds().intersects(obstacle.getBounds())) {
                rover.stop(); // Stop rover if it hits an obstacle
            }
        }

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Rover on Mars");
        SpaceRoverOnMars scene = new SpaceRoverOnMars();
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


}

// Rover class representing the space rover
class Rover {
    private int x, y;
    private int speed = 2; // Speed of the rover
    private int width = 60;
    private int height = 30;

    public Rover(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height); // Draw the rover body
        g.setColor(Color.BLACK);
        g.fillOval(x + 5, y + height, 15, 15); // Draw left wheel
        g.fillOval(x + width - 20, y + height, 15, 15); // Draw right wheel
        g.setColor(Color.YELLOW);
        g.fillRect(x + 20, y - 10, 20, 10); // Draw rover antenna
    }

    public void move() {
        x += speed; // Move rover to the right
        if (x > 1800) {
            x = -width; // Reset position to the left if it goes off-screen
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Get the bounds for collision detection
    }

    public void stop() {
        speed = 0; // Stop the rover
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

// Obstacle class representing obstacles on the Mars surface
class Obstacle {
    private int x, y;
    private int width = 50;
    private int height = 20;
    private int speed = 1;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height); // Draw the obstacle
    }

    public void move() {
        y += speed; // Move obstacle down
        if (y > 800) {
            reset(); // Reset position if it goes off-screen
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Get the bounds for collision detection
    }

    public void reset() {
        this.y = (int) (Math.random() * (800 / 2 + 100)); // Randomize the y position for resetting
        this.x = (int) (Math.random() * 1800); // Randomize the x position for resetting
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
