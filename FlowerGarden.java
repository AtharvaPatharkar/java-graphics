import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FlowerGarden extends JPanel implements ActionListener {

    private int petalOffset = 0;
    private int leafOffset = 0;
    private int sunOffset = 0;  // Animation for the sun
    public  Timer timer;  // Define Timer object here

    public FlowerGarden() {
        // Initialize the timer with delay (50 ms) and action listener
        timer = new Timer(50, this);  
        timer.start();  // Start the animation timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.CYAN); // Sky background color

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw border
        drawBorder(g2d, width, height);

        // Draw sun
        drawSun(g2d, width - 100, 100);

        // Draw ground
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, height - 150, width, 150);  // Green ground

        // Center the garden
        int gardenX = width / 2;
        int gardenY = height / 2;

        // Draw multiple flowers with different designs
        drawFlower(g2d, gardenX - 200, gardenY + 50);
        drawFlower(g2d, gardenX, gardenY + 50);
        drawTulip(g2d, gardenX + 200, gardenY + 100);

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("Flower Garden", gardenX - 160, 100);

        // Draw additional text
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("Code with AP", gardenX - 100, height - 50);
    }

    // Method to draw a border
    private void drawBorder(Graphics2D g2d, int width, int height) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRect(5, 5, width - 10, height - 10); // Draws a border 10px inside the edges
    }

    // Method to draw the sun
    private void drawSun(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 50 + sunOffset, y - 50, 100, 100); // Sun center

        g2d.setStroke(new BasicStroke(3));
        // Rays of the sun
        for (int i = 0; i < 360; i += 30) {
            int rayX = (int) (x + 70 * Math.cos(Math.toRadians(i)));
            int rayY = (int) (y + 70 * Math.sin(Math.toRadians(i)));
            g2d.drawLine(x, y, rayX, rayY);
        }
    }

    // Method to draw a simple flower
    private void drawFlower(Graphics2D g2d, int x, int y) {
        // Stem
        g2d.setColor(new Color(0, 150, 0));
        g2d.fillRect(x - 5, y, 10, 100);

        // Leaves
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x - 30 - leafOffset, y + 40, 30, 15); // Left leaf
        g2d.fillOval(x + 5 + leafOffset, y + 40, 30, 15);  // Right leaf

        // Petals (using animation offset)
        g2d.setColor(Color.PINK);
        g2d.fillOval(x - 25, y - 25 - petalOffset, 30, 30); // Top-left petal
        g2d.fillOval(x + 5, y - 25 + petalOffset, 30, 30);  // Top-right petal
        g2d.fillOval(x - 25 + petalOffset, y + 5, 30, 30);  // Bottom-left petal
        g2d.fillOval(x + 5 - petalOffset, y + 5, 30, 30);   // Bottom-right petal

        // Center of flower
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 10, y - 10, 20, 20);
    }

    // Method to draw a tulip-like flower
    private void drawTulip(Graphics2D g2d, int x, int y) {
        // Stem
        g2d.setColor(new Color(0, 150, 0));
        g2d.fillRect(x - 5, y, 10, 100);

        // Leaves
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x - 25 - leafOffset, y + 50, 20, 30); // Left leaf
        g2d.fillOval(x + 5 + leafOffset, y + 50, 20, 30);  // Right leaf

        // Tulip petals
        g2d.setColor(new Color(255, 0, 100));  // Dark pink color
        g2d.fillArc(x - 20, y - 30 - petalOffset, 40, 40, 0, 180); // Bottom half circle
        g2d.fillArc(x - 20, y - 40 + petalOffset, 40, 40, 0, 180); // Top half circle
    }

    // Method that is called for animation update
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move petals, leaves, and sun in a small oscillating pattern
        petalOffset = (petalOffset + 1) % 10;  // Oscillate petals
        leafOffset = (leafOffset + 1) % 5;     // Oscillate leaves
        sunOffset = (sunOffset + 1) % 5;       // Move the sun a little

        repaint();  // Redraw the screen with updated positions
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Flower Garden");
        FlowerGarden garden = new FlowerGarden();
        frame.add(garden);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setVisible(true);
    }
}
