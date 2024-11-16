import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class MeteorShowerAnimation extends JPanel implements ActionListener {
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Meteor> meteors;
    private int screenWidth = 1800;
    private int screenHeight = 800;

    public MeteorShowerAnimation() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK); // Background is black for the night sky

        // Set up animation timer (60 FPS)
        timer = new Timer(16, this);
        timer.start();

        // Initialize the meteor list
        meteors = new ArrayList<>();

        // Generate random meteors
        for (int i = 0; i < 20; i++) {
            meteors.add(new Meteor(new Random().nextInt(screenWidth), new Random().nextInt(screenHeight / 2)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g);
        drawScene(g);
        drawFooterText(g);
    }

    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.YELLOW);
        g.drawString("Meteor Shower", getWidth() / 2 - 120, 50); // Title in center-top
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 30); // Text at bottom
    }

    private void drawScene(Graphics g) {
        // Draw stars (random dots on the background)
        drawStars(g);

        // Draw and animate meteors
        for (Meteor meteor : meteors) {
            meteor.draw(g);
            meteor.move();
        }
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(screenWidth);
            int y = random.nextInt(screenHeight / 2); // Stars only appear in the upper half
            g.fillOval(x, y, 2, 2); // Small white dots as stars
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Repaint the scene on each timer tick
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Meteor Shower Animation");
        MeteorShowerAnimation animation = new MeteorShowerAnimation();
        frame.add(animation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
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
}

// Meteor class to represent falling meteors
class Meteor {
    private int x, y;
    private int speedX, speedY;
    private int size;
    private Color color;

    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 10 + new Random().nextInt(15); // Random size for each meteor
        this.speedX = 5 + new Random().nextInt(10); // Speed along the X-axis
        this.speedY = 3 + new Random().nextInt(7); // Speed along the Y-axis
        this.color = new Color(255, 165, 0); // Orange color for the meteor
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size); // Draw the meteor as an oval
        g.setColor(Color.YELLOW);
        g.drawLine(x + size / 2, y + size / 2, x - 10, y - 10); // Draw tail of the meteor
    }

    public void move() {
        x += speedX; // Move right
        y += speedY; // Move down

        // If the meteor goes out of bounds, reset its position to the top
        if (x > 1800 || y > 800) {
            x = new Random().nextInt(1800);
            y = new Random().nextInt(400); // Reset Y to a random upper position
        }
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
