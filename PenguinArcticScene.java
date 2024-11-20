import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class PenguinArcticScene extends JPanel implements ActionListener {
    private Timer timer;
    private int penguinX = 400; // Initial position of the penguin
    private int penguinY = 800; // Y position of the penguin
    private int screenWidth = 1600;
    private int screenHeight = 800;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Snowflake> snowflakes; // List of snowflakes

    public PenguinArcticScene() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 250)); // Sky blue background

        timer = new Timer(16, this); // 60 FPS
        timer.start();

        snowflakes = new ArrayList<>();
        Random random = new Random();
        
        // Create initial snowflakes
        for (int i = 0; i < 100; i++) {
            snowflakes.add(new Snowflake(random.nextInt(screenWidth), random.nextInt(screenHeight)));
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
        g.setColor(Color.RED);
        g.drawString("Penguin in the Arctic", getWidth() / 2 - 150, 50);
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLUE);
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    private void drawScene(Graphics g) {
        // Draw the ice surface
        g.setColor(Color.WHITE);
        g.fillRect(0, 450, screenWidth, 150); // Ice surface

        // Draw the snowman
        drawSnowman(g, 600, 380);

        // Draw the penguin
        drawPenguin(g, penguinX, penguinY);

        // Draw snowflakes
        for (Snowflake snowflake : snowflakes) {
            snowflake.draw(g);
        }
    }

    private void drawSnowman(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 50, 50); // Head
        g.fillOval(x, y + 50, 70, 70); // Body
        g.fillOval(x - 10, y + 120, 90, 90); // Base
        g.setColor(Color.BLACK);
        g.fillOval(x + 15, y + 10, 5, 5); // Left eye
        g.fillOval(x + 30, y + 10, 5, 5); // Right eye
        g.drawLine(x + 25, y + 20, x + 25, y + 35); // Nose
    }

    private void drawPenguin(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 50, 70); // Penguin body
        g.setColor(Color.WHITE);
        g.fillOval(x + 10, y + 10, 30, 50); // Penguin belly
        g.setColor(Color.ORANGE);
        g.fillPolygon(new int[]{x + 25, x + 30, x + 20}, new int[]{y + 60, y + 70, y + 70}, 3); // Beak
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the penguin side to side
        if (penguinX < screenWidth - 50) {
            penguinX += 2; // Move right
        } else {
            penguinX = 0; // Reset position
        }

        // Update snowflakes
        for (Snowflake snowflake : snowflakes) {
            snowflake.move();
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Penguin in the Arctic Scene");
        PenguinArcticScene animation = new PenguinArcticScene();
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

    public int getPenguinX() {
        return penguinX;
    }

    public void setPenguinX(int penguinX) {
        this.penguinX = penguinX;
    }

    public int getPenguinY() {
        return penguinY;
    }

    public void setPenguinY(int penguinY) {
        this.penguinY = penguinY;
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

// Snowflake class to represent individual snowflakes
class Snowflake {
    private int x, y;
    private int size;
    private int speed;

    public Snowflake(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = new Random().nextInt(10) + 5; // Random size between 5 and 15
        this.speed = new Random().nextInt(2) + 1; // Random speed for falling
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size); // Draw snowflake
    }

    public void move() {
        y += speed; // Move down
        if (y > 600) { // Reset position when it goes off screen
            y = 0;
            x = new Random().nextInt(800); // Random x position
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
