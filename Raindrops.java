import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Raindrops extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Raindrop> raindrops;
        @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Cloud> clouds;
    private int sunX, sunY;

    public Raindrops() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.LIGHT_GRAY); // Background color representing a cloudy sky

        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();

        raindrops = new ArrayList<>();
        clouds = new ArrayList<>();

        // Create a random number of raindrops
        for (int i = 0; i < 100; i++) {
            raindrops.add(new Raindrop((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight)));
        }

        // Create clouds
        for (int i = 0; i < 3; i++) {
            clouds.add(new Cloud((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 2)));
        }

        // Set the position of the sun
        sunX = 1500;
        sunY = 100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g);
        drawWindow(g);
        drawRaindrops(g);
        drawClouds(g);
        drawSun(g);
        drawFooterText(g);

    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("Raindrops on a Window", screenWidth / 2 - 200, 50); // Title at top-center
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    // Draw the window
    private void drawWindow(Graphics g) {
        g.setColor(new Color(0, 102, 204)); // Window color
        g.fillRect(screenWidth / 2 - 400, screenHeight / 2 - 300, 800, 600); // Window frame

        g.setColor(new Color(255, 255, 255, 150)); // Semi-transparent for glass effect
        g.fillRect(screenWidth / 2 - 395, screenHeight / 2 - 295, 790, 590); // Window glass
    }

    // Draw the raindrops
    private void drawRaindrops(Graphics g) {
        for (Raindrop raindrop : raindrops) {
            raindrop.draw(g);
        }
    }

    // Draw the clouds
    private void drawClouds(Graphics g) {
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
    }

    // Draw the sun
    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(sunX, sunY, 80, 80); // Draw the sun
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update raindrop positions
        for (Raindrop raindrop : raindrops) {
            raindrop.move();
            if (raindrop.getY() > screenHeight) {
                raindrop.reset(); // Reset raindrop position
            }
        }

        // Update cloud positions
        for (Cloud cloud : clouds) {
            cloud.move();
            if (cloud.getX() > screenWidth) {
                cloud.reset(); // Reset cloud position
            }
        }

        // Move the sun
        sunX -= 1;
        if (sunX < -80) {
            sunX = screenWidth; // Reset sun position
        }

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Raindrops");
        Raindrops scene = new Raindrops();
        frame.add(scene);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
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

// Raindrop class representing a falling raindrop
class Raindrop {
    private int x, y;
    private int speed;
    private int size;

    public Raindrop(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 2 + (int) (Math.random() * 5); // Random speed between 2 and 6
        this.size = 5 + (int) (Math.random() * 5); // Random size between 5 and 10
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, size, size * 2); // Raindrop shape
    }

    public void move() {
        y += speed; // Move raindrop down
    }

    public int getY() {
        return y;
    }

    public void reset() {
        this.y = 0; // Reset position to the top
        this.x = (int) (Math.random() * 1800); // Random x position
        this.speed = 2 + (int) (Math.random() * 5); // Random speed for the next fall
        this.size = 5 + (int) (Math.random() * 5); // Random size for the next fall
    }
}

// Cloud class representing a cloud
class Cloud {
    private int x, y;
    private int speed;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 1 + (int) (Math.random() * 3); // Random speed for cloud movement
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 100, 60); // Draw the main part of the cloud
        g.fillOval(x + 30, y - 20, 100, 60); // Draw the right part of the cloud
        g.fillOval(x - 30, y - 20, 100, 60); // Draw the left part of the cloud
    }

    public void move() {
        x += speed; // Move cloud to the right
    }

    public int getX() {
        return x;
    }

    public void reset() {
        this.x = -100; // Reset position to the left side
        this.y = (int) (Math.random() * 200); // Random y position for clouds
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
