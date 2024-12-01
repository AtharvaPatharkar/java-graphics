import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SunriseOverLakeScene extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    
    private Timer timer;
    private int sunY;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Cloud> clouds;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Bird> birds;
    private int lakeY;

    public SunriseOverLakeScene() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 250)); // Sky blue background
        
        sunY = screenHeight; // Sun starts from the bottom and rises up
        timer = new Timer(16, this);
        timer.start();

        lakeY = screenHeight / 2 + 100;
        clouds = new ArrayList<>();
        birds = new ArrayList<>();

        // Add some clouds and birds
        for (int i = 0; i < 4; i++) {
            clouds.add(new Cloud(i * 400 + 100, 100 + (i * 50)));
        }
        for (int i = 0; i < 3; i++) {
            birds.add(new Bird(i * 400 + 200, 150 + (i * 50)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g);
        drawSun(g);
        drawLake(g);
        drawClouds(g);
        drawBirds(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Sunrise Over a Lake", screenWidth / 2 - 150, 50); // Title in center-top
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Text at bottom
    }

    // Draw the sun rising over the lake
    private void drawSun(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(screenWidth / 2 - 100, sunY, 200, 200); // Sun in center, moving upward
    }

    // Draw the lake and its reflection
    private void drawLake(Graphics g) {
        g.setColor(new Color(70, 130, 180)); // Blue color for the lake
        g.fillRect(0, lakeY, screenWidth, screenHeight / 2); // Lake below the center
        
        // Reflection of the sun in the lake
        g.setColor(new Color(255, 165, 0, 100)); // Semi-transparent orange for reflection
        g.fillOval(screenWidth / 2 - 100, lakeY + (sunY - screenHeight / 2 + 200), 200, 100); // Reflection of the sun
    }

    // Draw clouds
    private void drawClouds(Graphics g) {
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
    }

    // Draw birds
    private void drawBirds(Graphics g) {
        for (Bird bird : birds) {
            bird.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move sun up
        if (sunY > screenHeight / 4) {
            sunY -= 1;
        }

        // Move clouds and birds
        for (Cloud cloud : clouds) {
            cloud.move();
        }
        for (Bird bird : birds) {
            bird.move();

        }

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sunrise Over a Lake Scene");
        SunriseOverLakeScene scene = new SunriseOverLakeScene();
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

    public int getLakeY() {
        return lakeY;
    }

    public void setLakeY(int lakeY) {
        this.lakeY = lakeY;
    }
}

// Cloud class representing a moving cloud
class Cloud {
    private int x, y;
    private int speed = 1;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 150, 60); // Cloud shape
    }

    public void move() {
        x += speed;
        if (x > 1800) {
            x = -150; // Reset cloud position when it moves off screen
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

// Bird class representing a moving bird
class Bird {
    private int x, y;
    private int speed = 2;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawArc(x, y, 40, 20, 0, 180); // Bird's wings (upper part of arc)
        g.drawArc(x + 20, y, 40, 20, 0, 180); // Second wing
    }

    public void move() {
        x += speed;
        if (x > 1800) {
            x = -80; // Reset bird position when it moves off screen
        }
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
}
