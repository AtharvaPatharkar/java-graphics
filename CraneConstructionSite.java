import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class CraneConstructionSite extends JPanel implements ActionListener {

    private Timer timer;
    private int craneBaseX, craneBaseY; // Base position of the crane
    private int craneArmLength = 150; // Length of the crane arm
    private int blockX, blockY; // Position of the block
    private int blockSize = 30; // Size of the block
    private boolean lifting; // To control lifting movement
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Truck> trucks; // List of trucks
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Worker> workers; // List of workers
    private int sunX, sunY; // Sun position
    private Random random;

    // Constructor
    public CraneConstructionSite() {
        random = new Random();
        craneBaseX = 400; // Centered on screen
        craneBaseY = 300;
        blockX = craneBaseX + craneArmLength; // Initial block position
        blockY = craneBaseY - blockSize; // Initial block position
        lifting = false;

        // Initialize trucks
        trucks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            trucks.add(new Truck(random.nextInt(800), 350)); // Random starting positions for trucks
        }

        // Initialize workers
        workers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            workers.add(new Worker(random.nextInt(800), 450)); // Random starting positions for workers
        }

        // Initialize sun position
        sunX = 50; // Initial sun position
        sunY = 50; // Fixed height for the sun

        // Set up timer for animation
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method to run the program
    public static void main(String[] args) {
        JFrame frame = new JFrame("Crane Construction Site");
        CraneConstructionSite panel = new CraneConstructionSite();

        // Set up the frame
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // PaintComponent method to draw the scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background
        g2d.setColor(new Color(135, 206, 235)); // Sky blue color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw Sun
        drawSun(g2d);

        // Draw Title
        drawTitleText(g2d);

        // Draw Crane
        drawCrane(g2d);

        // Draw Block
        drawBlock(g2d);

        // Draw Trucks
        drawTrucks(g2d);

        // Draw Workers
        drawWorkers(g2d);

        // Draw footer text
        drawFooterText(g2d);
    }

    // Method to draw the sun
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunX, sunY, 50, 50); // Draw sun
    }

    // Method to draw the title text
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String title = "Crane and Construction Site";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(title)) / 2;
        int y = 50;
        g2d.drawString(title, x, y);
    }

    // Method to draw the crane
    private void drawCrane(Graphics2D g2d) {
        // Crane base
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(craneBaseX - 10, craneBaseY, 20, 40); // Crane post

        // Crane arm
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(craneBaseX, craneBaseY + 10, craneArmLength, 10); // Crane arm
    }

    // Method to draw the block
    private void drawBlock(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(blockX, blockY, blockSize, blockSize);
    }

    // Method to draw trucks
    private void drawTrucks(Graphics2D g2d) {
        for (Truck truck : trucks) {
            g2d.setColor(truck.color);
            g2d.fillRect(truck.x, truck.y, truck.width, truck.height); // Draw truck body
            g2d.setColor(Color.BLACK);
            g2d.fillOval(truck.x + 10, truck.y + truck.height, 15, 15); // Left wheel
            g2d.fillOval(truck.x + truck.width - 25, truck.y + truck.height, 15, 15); // Right wheel
        }
    }

    // Method to draw workers
    private void drawWorkers(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        for (Worker worker : workers) {
            g2d.fillRect(worker.x, worker.y, worker.width, worker.height); // Draw worker
        }
    }

    // Method to draw footer text
    private void drawFooterText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);
        String footerText = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(footerText)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(footerText, x, y);
    }

    // Update animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the sun across the sky
        sunX += 1;
        if (sunX > getWidth()) {
            sunX = -50; // Reset sun position if it goes off-screen
        }

        // Move block up and down
        if (lifting) {
            if (blockY > craneBaseY - 100) { // Move up to a certain limit
                blockY -= 1;
                // Simulate swinging
                blockX += Math.sin(Math.toRadians((craneArmLength - blockY) * 2)) * 2;
            } else {
                lifting = false;
            }
        } else {
            if (blockY < craneBaseY - blockSize) { // Move down to the original position
                blockY += 1;
                // Simulate swinging
                blockX -= Math.sin(Math.toRadians((craneArmLength - blockY) * 2)) * 2;
            } else {
                lifting = true; // Start lifting again
            }
        }

        // Move trucks
        for (Truck truck : trucks) {
            truck.x += truck.speed;
            if (truck.x > getWidth()) {
                truck.x = -truck.width; // Reset position if off-screen
            }
        }

        // Move workers
        for (Worker worker : workers) {
            worker.x += worker.speed;
            if (worker.x > getWidth()) {
                worker.x = -worker.width; // Reset position if off-screen
            }
            // Change direction if reaching the edge of the panel
            if (worker.x < 0 || worker.x > getWidth()) {
                worker.speed = -worker.speed; // Reverse direction
            }
        }

        // Repaint the scene for smooth animation
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getCraneBaseX() {
        return craneBaseX;
    }

    public void setCraneBaseX(int craneBaseX) {
        this.craneBaseX = craneBaseX;
    }

    public int getCraneBaseY() {
        return craneBaseY;
    }

    public void setCraneBaseY(int craneBaseY) {
        this.craneBaseY = craneBaseY;
    }

    public int getCraneArmLength() {
        return craneArmLength;
    }

    public void setCraneArmLength(int craneArmLength) {
        this.craneArmLength = craneArmLength;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    // Truck class to represent each truck
    class Truck {
        int x, y, width, height, speed;
        Color color;

        public Truck(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 60;
            this.height = 30;
            this.speed = 2 + random.nextInt(3); // Random speed between 2 and 4
            this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); // Random color
        }
    }

    // Worker class to represent each worker
    class Worker {
        int x, y, width, height, speed;

        public Worker(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 20;
            this.height = 30;
            this.speed = 1 + random.nextInt(2); // Random speed between 1 and 2
        }
    }
}
