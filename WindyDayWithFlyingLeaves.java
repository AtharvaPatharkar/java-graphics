import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class WindyDayWithFlyingLeaves extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Leaf> leaves;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Tree> trees;

    public WindyDayWithFlyingLeaves() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(135, 206, 235)); // Sky blue background

        timer = new Timer(20, this); // Approximately 50 FPS
        timer.start();

        leaves = new ArrayList<>();
        trees = new ArrayList<>();

        // Create trees on the scene
        for (int i = 0; i < 5; i++) {
            trees.add(new Tree(200 + (i * 300), screenHeight - 150));
        }

        // Create leaves
        for (int i = 0; i < 20; i++) {
            leaves.add(new Leaf((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g);
        drawTrees(g);
        drawLeaves(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Windy Day with Flying Leaves", screenWidth / 2 - 180, 50); // Title at top-center
    }

    // Draw trees on the scene
    private void drawTrees(Graphics g) {
        for (Tree tree : trees) {
            tree.draw(g);
        }
    }

    // Draw leaves on the scene
    private void drawLeaves(Graphics g) {
        for (Leaf leaf : leaves) {
            leaf.draw(g);
        }
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Leaf leaf : leaves) {
            leaf.move();
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Windy Day with Flying Leaves");
        WindyDayWithFlyingLeaves scene = new WindyDayWithFlyingLeaves();
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

// Leaf class representing the flying leaves
class Leaf {
    private int x, y;
    private int size;
    private int fallSpeed;
    private int horizontalSpeed;

    public Leaf(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = (int) (Math.random() * 15) + 10; // Random size between 10 and 25
        this.fallSpeed = (int) (Math.random() * 3) + 1; // Random fall speed between 1 and 3
        this.horizontalSpeed = (int) (Math.random() * 5) - 2; // Random horizontal speed between -2 and 2
    }

    public void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // Brown color for leaves
        g.fillOval(x, y, size, size); // Draw the leaf as an oval
    }

    public void move() {
        y += fallSpeed; // Move leaf downwards
        x += horizontalSpeed; // Move leaf sideways

        // Reset the leaf position when it goes off-screen
        if (y > 800) {
            y = 0;
            x = (int) (Math.random() * 1800); // Randomize horizontal position
        }

        // Keep the leaf within screen bounds
        if (x < 0) x = 0;
        if (x > 1800 - size) x = 1800 - size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }
}

// Tree class representing trees in the scene
class Tree {
    private final int x;
    private final int y;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw tree trunk
        g.setColor(new Color(139, 69, 19)); // Brown color for trunk
        g.fillRect(x, y + 60, 20, 50); // Trunk dimensions

        // Draw tree leaves
        g.setColor(Color.GREEN);
        g.fillOval(x - 30, y - 10, 80, 80); // Leaves as an oval
    }
}
