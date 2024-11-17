import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MountainBikingTrailScene extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;

    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private Cyclist cyclist;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Mountain> mountains;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Tree> trees;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Cloud> clouds;
    private int groundY;

    public MountainBikingTrailScene() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 235)); // Sky blue background

        // Initialize ground level and timer for animation
        groundY = screenHeight - 100;
        timer = new Timer(16, this);
        timer.start();

        // Initialize cyclist, mountains, trees, and clouds
        cyclist = new Cyclist(300, groundY - 100);
        mountains = new ArrayList<>();
        trees = new ArrayList<>();
        clouds = new ArrayList<>();

        // Add some mountains, trees, and clouds to the scene
        for (int i = 0; i < 4; i++) {
            mountains.add(new Mountain(i * 500 + 200, groundY - 200 - (i * 30)));
        }
        for (int i = 0; i < 5; i++) {
            trees.add(new Tree(i * 300 + 150, groundY - 70));
        }
        for (int i = 0; i < 3; i++) {
            clouds.add(new Cloud(i * 500 + 100, 100 + (i * 50)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g);
        drawMountains(g);
        drawGround(g);
        drawTrees(g);
        drawClouds(g);
        cyclist.draw(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Mountain Biking Trail Scene", screenWidth / 2 - 200, 50); // Title in center-top
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Text at bottom
    }

    // Draw the ground (trail)
    private void drawGround(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Brown for the ground (trail)
        g.fillRect(0, groundY, screenWidth, 100);
    }

    // Draw mountains in the background
    private void drawMountains(Graphics g) {
        for (Mountain mountain : mountains) {
            mountain.draw(g);
        }
    }

    // Draw trees
    private void drawTrees(Graphics g) {
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }

    // Draw clouds
    private void drawClouds(Graphics g) {
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move clouds and cyclist
        for (Cloud cloud : clouds) {
            cloud.move();
        }
        cyclist.move();

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mountain Biking Trail Scene");
        MountainBikingTrailScene scene = new MountainBikingTrailScene();
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

    public int getGroundY() {
        return groundY;
    }

    public void setGroundY(int groundY) {
        this.groundY = groundY;
    }
}

// Cyclist class representing the biker
class Cyclist {
    private int x, y;
    private int speed = 2;
    private int bikeWidth = 100;
    private int bikeHeight = 50;

    public Cyclist(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw the cyclist body
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30); // Cyclist head

        // Draw the bike
        g.setColor(Color.BLUE);
        g.fillRect(x + 30, y + 30, bikeWidth, bikeHeight); // Bicycle body

        // Draw the bike wheels
        g.setColor(Color.BLACK);
        g.fillOval(x + 40, y + bikeHeight + 20, 30, 30); // Front wheel
        g.fillOval(x + 100, y + bikeHeight +20 , 30, 30); // Rear wheel
    }

    public void move() {
        x += speed;
        if (x > 1800) {
            x = -bikeWidth; // Reset the cyclist position to the left when it goes off screen
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

    public int getBikeWidth() {
        return bikeWidth;
    }

    public void setBikeWidth(int bikeWidth) {
        this.bikeWidth = bikeWidth;
    }

    public int getBikeHeight() {
        return bikeHeight;
    }

    public void setBikeHeight(int bikeHeight) {
        this.bikeHeight = bikeHeight;
    }
}

// Mountain class representing a mountain in the background
class Mountain {
    private int x, y;

    public Mountain(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(102, 51, 0)); // Brown for mountains
        int[] xPoints = {x, x + 200, x - 200}; // Coordinates for triangle-shaped mountains
        int[] yPoints = {y, y + 300, y + 300};
        g.fillPolygon(xPoints, yPoints, 3);
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

// Tree class representing a tree in the foreground
class Tree {
    private int x, y;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw the trunk
        g.setColor(new Color(139, 69, 19)); // Brown for the tree trunk
        g.fillRect(x , y + 20, 20, 50); // Tree trunk

        // Draw the leaves
        g.setColor(Color.GREEN);
        g.fillOval(x - 20, y - 40, 60, 60); // Tree leaves
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
        g.fillOval(x, y, 100, 50); // Cloud shape
    }

    public void move() {
        x += speed;
        if (x > 1800) {
            x = -100; // Reset cloud position when it moves off screen
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
