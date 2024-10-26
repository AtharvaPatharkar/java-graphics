import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class BirdNesting extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Bird> birds;
     @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Cloud> clouds;
     @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Flower> flowers;
     @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Butterfly> butterflies;
    private int nestX, nestY;

    public BirdNesting() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 235)); // Sky blue background

        timer = new Timer(16, this);
        timer.start();

        nestX = screenWidth / 2 - 50;
        nestY = screenHeight / 2 - 50;

        birds = new ArrayList<>();
        clouds = new ArrayList<>();
        flowers = new ArrayList<>();
        butterflies = new ArrayList<>();

        // Add birds
        for (int i = 0; i < 4; i++) {
            birds.add(new Bird(i * 400 + 200, 100 + (i * 100)));
        }

        // Add clouds
        for (int i = 0; i < 3; i++) {
            clouds.add(new Cloud(i * 500 + 100, 50 + (i * 80)));
        }

        // Add flowers to the garden
        for (int i = 0; i < 10; i++) {
            flowers.add(new Flower((int) (Math.random() * screenWidth), screenHeight - 200 + (int) (Math.random() * 100)));
        }

        // Add butterflies to the scene
        for (int i = 0; i < 5; i++) {
            butterflies.add(new Butterfly((int) (Math.random() * screenWidth), (int) (Math.random() * (screenHeight - 200))));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g);
        drawTree(g);
        drawNest(g);
        drawClouds(g);
        drawFlowers(g);
        drawButterflies(g);
        drawBirds(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Animated Bird Nesting", screenWidth / 2 - 150, 50); // Title at top-center
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    // Draw the tree in the scene
    private void drawTree(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Brown color for the trunk
        g.fillRect(screenWidth / 2 - 50, screenHeight / 2, 100, 300); // Tree trunk
        
        g.setColor(new Color(34, 139, 34)); // Green for leaves
        g.fillOval(screenWidth / 2 - 150, screenHeight / 2 - 100, 300, 200); // Tree canopy
    }

    // Draw the bird's nest on the tree
    private void drawNest(Graphics g) {
        g.setColor(new Color(205, 133, 63)); // Nest color
        g.fillOval(nestX, nestY, 100, 50); // Bird's nest in the tree
        // Add eggs to the nest
        g.setColor(Color.YELLOW);
        g.fillOval(nestX + 20, nestY + 10, 15, 10);
        g.fillOval(nestX + 40, nestY + 10, 15, 10);
        g.fillOval(nestX + 60, nestY + 10, 15, 10);
    }

    // Draw clouds
    private void drawClouds(Graphics g) {
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
    }

    // Draw flowers in the garden
    private void drawFlowers(Graphics g) {
        for (Flower flower : flowers) {
            flower.draw(g);
        }
    }

    // Draw butterflies in the scene
    private void drawButterflies(Graphics g) {
        for (Butterfly butterfly : butterflies) {
            butterfly.draw(g);
        }
    }

    // Draw birds flying towards the nest
    private void drawBirds(Graphics g) {
        for (Bird bird : birds) {
            bird.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move clouds, birds, and butterflies
        for (Cloud cloud : clouds) {
            cloud.move();
        }

        for (Bird bird : birds) {
            bird.moveTowards(nestX, nestY); // Birds move towards the nest
        }

        for (Butterfly butterfly : butterflies) {
            butterfly.move(); // Butterflies flutter around
        }

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Bird Nesting");
        BirdNesting scene = new BirdNesting();
        frame.add(scene);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getNestX() {
        return nestX;
    }

    public void setNestX(int nestX) {
        this.nestX = nestX;
    }

    public int getNestY() {
        return nestY;
    }

    public void setNestY(int nestY) {
        this.nestY = nestY;
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

// Bird class representing a moving bird flying towards the nest
class Bird {
    private int x, y;
    private int speed = 2;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw a simple representation of a bird
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 30, 20); // Bird body
        g.setColor(Color.GRAY);
        g.fillArc(x - 10, y - 10, 20, 20, 0, 180); // Left wing
        g.fillArc(x + 10, y - 10, 20, 20, 0, 180); // Right wing
    }

    public void moveTowards(int targetX, int targetY) {
        // Move horizontally
        if (x < targetX) {
            x += speed;
        } else if (x > targetX) {
            x -= speed;
        }

        // Move vertically
        if (y < targetY) {
            y += speed;
        } else if (y > targetY) {
            y -= speed;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

// Flower class representing a flower in the garden
class Flower {
    private int x, y;

    public Flower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval(x, y, 20, 20); // Flower petals
        g.setColor(Color.YELLOW);
        g.fillOval(x + 5, y + 5, 10, 10); // Flower center
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
}

// Butterfly class representing a butterfly flying around
class Butterfly {
    private int x, y;
    private int dx, dy;

    public Butterfly(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = (int) (Math.random() * 4 + 1); // Random speed
        this.dy = (int) (Math.random() * 4 + 1); // Random speed
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, 20, 10); // Butterfly body
        g.setColor(Color.BLACK);
        g.fillOval(x - 5, y - 5, 10, 10); // Left wing
        g.fillOval(x + 15, y - 5, 10, 10); // Right wing
    }

    public void move() {
        x += dx;
        y += dy;
        // Change direction when hitting the window edges
        if (x < 0 || x > 1800 - 20) dx = -dx;
        if (y < 0 || y > 800 - 10) dy = -dy;
    }
}
