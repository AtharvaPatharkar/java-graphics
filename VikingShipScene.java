import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class VikingShipScene extends JPanel implements ActionListener {
    private Timer timer;
    private int shipX = 1600; // Initial position of the ship
    private int shipY = 350; // Y position of the ship
    private int screenWidth = 1800;
    private int screenHeight = 1000;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Wave> waves; // List of waves
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Seagull> seagulls; // List of seagulls

    public VikingShipScene() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 250)); // Sky blue background

        timer = new Timer(16, this); // 60 FPS
        timer.start();

        waves = new ArrayList<>();
        seagulls = new ArrayList<>();
        Random random = new Random();

        // Create initial waves
        for (int i = 0; i < 5; i++) {
            waves.add(new Wave(random.nextInt(screenWidth), random.nextInt(30) + 400));
        }

        // Create seagulls
        for (int i = 0; i < 3; i++) {
            seagulls.add(new Seagull(random.nextInt(screenWidth), random.nextInt(100) + 50));
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
        g.drawString("Viking Ship at Sea", getWidth() / 2 - 150, 50);
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLUE);
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    private void drawScene(Graphics g) {
        // Draw the sea
        g.setColor(new Color(0, 102, 204)); // Sea color
        g.fillRect(0, 400, screenWidth, 200); // Sea

        // Draw waves
        for (Wave wave : waves) {
            wave.draw(g);
        }

        // Draw the Viking ship
        drawShip(g, shipX, shipY);

        // Draw seagulls
        for (Seagull seagull : seagulls) {
            seagull.draw(g);
        }
    }

    private void drawShip(Graphics g, int x, int y) {
        // Draw ship body
        g.setColor(new Color(139, 69, 19)); // Brown color for ship
        g.fillRect(x, y, 60, 30); // Ship base
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 60, 30); // Ship outline

        // Draw sail
        g.setColor(Color.WHITE);
        int[] xPoints = {x + 20, x + 60, x + 20};
        int[] yPoints = {y, y - 40, y - 40};
        g.fillPolygon(xPoints, yPoints, 3); // Sail
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 3); // Sail outline

        // Draw mast
        g.setColor(Color.BLACK);
        g.fillRect(x + 30, y - 40, 5, 40); // Mast
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the ship left and right
        shipX += (Math.sin(System.currentTimeMillis() * 0.001) * 2); // Swaying motion

        // Update waves
        for (Wave wave : waves) {
            wave.move();
        }

        // Update seagulls
        for (Seagull seagull : seagulls) {
            seagull.move();
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Viking Ship at Sea");
        VikingShipScene animation = new VikingShipScene();
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

    public int getShipY() {
        return shipY;
    }

    public void setShipY(int shipY) {
        this.shipY = shipY;
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

// Wave class to represent ocean waves
class Wave {
    private int x, y;
    private int width, height;

    public Wave(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50 + new Random().nextInt(30); // Random width for waves
        this.height = 5 + new Random().nextInt(10); // Random height for waves
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillArc(x, y, width, height, 0, 180); // Draw wave
    }

    public void move() {
        y += 1; // Move wave down
        if (y > 400) { // Reset wave position when it goes off screen
            y = 350;
            x = new Random().nextInt(800); // Random x position
        }
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

// Seagull class to represent flying seagulls
class Seagull {
    private int x, y;
    private int speed;

    public Seagull(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 2 + new Random().nextInt(2); // Random speed
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillArc(x, y, 20, 10, 0, 180); // Draw seagull as a simple arc
    }

    public void move() {
        x += speed; // Move seagull to the right
        if (x > 800) { // Reset seagull position when it goes off screen
            x = -20; // Start from the left
            y = new Random().nextInt(100) + 50; // Random y position
        }
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
}
