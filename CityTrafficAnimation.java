import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class CityTrafficAnimation extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<int[]> carsRight;  // Cars moving to the right
    private ArrayList<int[]> carsLeft;   // Cars moving to the left
    private ArrayList<int[]> clouds;      // List of clouds with positions
    private int sunX;                    // Position of the sun
    private int carSpeedRight = 3;       // Speed of right-moving cars
    private int carSpeedLeft = 2;        // Speed of left-moving cars
    private int cloudSpeed = 1;          // Speed of clouds
    private Random random;

    // Constructor
    public CityTrafficAnimation() {
        random = new Random();
        carsRight = new ArrayList<>();
        carsLeft = new ArrayList<>();
        clouds = new ArrayList<>();
        sunX = 50; // Initial position of the sun (more left)

        // Create initial cars and clouds
        generateCarsRight();
        generateCarsLeft();
        generateClouds();

        // Timer for animation
        timer = new Timer(30, this);
        timer.start();
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("City Traffic Animation - Code with AP");
        CityTrafficAnimation panel = new CityTrafficAnimation();

        // Set up the frame
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.add(panel);
        frame.setVisible(true);
    }

    // Generate cars moving to the right (upper lane)
    private void generateCarsRight() {
        for (int i = 0; i < 20; i++) { // Increase number of cars
            int x = random.nextInt(900); // Random starting positions off-screen
            int y = 420; // Upper lane for cars moving right
            int width = 100;
            int height = 40;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); // Random color

            // Only add car if it doesn't overlap
            if (!isOverlap(carsRight, x, y, width, height)) {
                carsRight.add(new int[]{x, y, width, height, color.getRGB()});
            }
        }
    }

    // Generate cars moving to the left (lower lane)
    private void generateCarsLeft() {
        for (int i = 0; i < 18; i++) { // Increase number of cars
            int x = random.nextInt(900) + 200; // Cars start off-screen from the right
            int y = 500; // Lower lane for cars moving left
            int width = 100;
            int height = 40;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); // Random color

            // Only add car if it doesn't overlap
            if (!isOverlap(carsLeft, x, y, width, height)) {
                carsLeft.add(new int[]{x, y, width, height, color.getRGB()});
            }
        }
    }

    // Check for overlap between a new car and existing cars
    private boolean isOverlap(ArrayList<int[]> cars, int newX, int newY, int width, int height) {
        for (int[] car : cars) {
            // Calculate the boundaries of the existing car
            int carX = car[0];
            int carY = car[1];
            int carWidth = car[2];
            int carHeight = car[3];

            // Check for overlap
            if (newX < carX + carWidth && newX + width > carX &&
                newY < carY + carHeight && newY + height > carY) {
                return true; // Overlap detected
            }
        }
        return false; // No overlap
    }

    // Generate clouds with random positions
    private void generateClouds() {
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(800); // Random starting position for clouds
            int y = 50 + random.nextInt(100); // Clouds at different heights
            int width = 80 + random.nextInt(40);
            int height = 40;
            clouds.add(new int[]{x, y, width, height});
        }
    }

    // PaintComponent method to draw the scene
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background sky
        g2d.setColor(new Color(135, 206, 235)); // Sky blue color
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the title
        drawTitleText(g2d);

        // Draw the sun in the sky
        drawSun(g2d);

        // Draw clouds
        drawClouds(g2d);

        // Draw buildings in the background
        drawBuildings(g2d);

        // Draw the road
        drawRoad(g2d);

        // Draw moving cars
        drawCars(g2d);

        // Draw traffic lights
        drawTrafficLights(g2d);

        // Draw bottom text "Code with AP"
        drawBottomText(g2d);
    }

    // Draw the title text at the top
    private void drawTitleText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Mumbai City Traffic";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = 50;
        g2d.drawString(text, x, y);
    }

    // Draw the sun moving across the sky
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunX, 10, 100, 100); // Draw sun with changing X position
    }

    // Draw clouds floating in the sky
    private void drawClouds(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int[] cloud : clouds) {
            g2d.fillRoundRect(cloud[0], cloud[1], cloud[2], cloud[3], 200, 200);
        }
    }

    // Draw cars moving in both directions
    private void drawCars(Graphics2D g2d) {
        for (int[] car : carsRight) {
            g2d.setColor(new Color(car[4])); // Use stored color for each car
            g2d.fillRect(car[0], car[1], car[2], car[3]);
            g2d.setColor(Color.BLACK);
            g2d.fillOval(car[0] + 10, car[1] + 30, 20, 20); // Left wheel
            g2d.fillOval(car[0] + 70, car[1] + 30, 20, 20); // Right wheel
        }

        for (int[] car : carsLeft) {
            g2d.setColor(new Color(car[4])); // Use stored color for each car
            g2d.fillRect(car[0], car[1], car[2], car[3]);
            g2d.setColor(Color.BLACK);
            g2d.fillOval(car[0] + 10, car[1] + 30, 20, 20); // Left wheel
            g2d.fillOval(car[0] + 70, car[1] + 30, 20, 20); // Right wheel
        }
    }

    // Draw buildings in the background with random colors
    private void drawBuildings(Graphics2D g2d) {
        for (int i = 0; i < 5; i++) {
            int buildingX = 100 + i * 150;
            int buildingY = 200;
            int buildingWidth = 120;
            int buildingHeight = 250;

            // Generate a random color for each building
            Color buildingColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g2d.setColor(buildingColor);
            g2d.fillRect(buildingX, buildingY, buildingWidth, buildingHeight); // Building base

            // Draw windows
            g2d.setColor(Color.YELLOW);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    g2d.fillRect(buildingX + 20 + k * 30, buildingY + 20 + j * 40, 20, 20); // Windows
                }
            }
        }
    }

    // Draw the road
    private void drawRoad(Graphics2D g2d) {
        g2d.setColor(new Color(50, 50, 50)); // Dark gray road
        g2d.fillRect(0, 400, getWidth(), 150);
        g2d.setColor(Color.WHITE); // Road stripes
        for (int i = 0; i < 10; i++) {
            g2d.fillRect(i * 100, 475, 50, 10); // Draw road stripes
        }
    }

    // Draw traffic lights at the side of the road
    private void drawTrafficLights(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(750, 375, 15, 60); // Traffic light post
        g2d.setColor(Color.RED);
        g2d.fillOval(748, 380, 15, 15); // Red light
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(748, 395, 15, 15); // Yellow light
        g2d.setColor(Color.GREEN);
        g2d.fillOval(748, 410, 15, 15); // Green light
    }

    // Draw the bottom text at the bottom center
    private void drawBottomText(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        String text = "Code with AP";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = getHeight() - 50;
        g2d.drawString(text, x, y);
    }

    // Update animation state
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the sun slowly across the sky
        sunX += 1; // Change speed of the sun
        if (sunX > getWidth()) {
            sunX = -100; // Reset sun position after moving off-screen
        }

        // Move right-moving cars horizontally
        for (int[] car : carsRight) {
            car[0] += carSpeedRight; // Move car by its speed
            if (car[0] > getWidth()) {
                car[0] = -200; // Reset car position if it goes off-screen
            }
        }

        // Move left-moving cars horizontally
        for (int[] car : carsLeft) {
            car[0] -= carSpeedLeft; // Move car by its speed
            if (car[0] < -200) {
                car[0] = getWidth(); // Reset car position if it goes off-screen
            }
        }

        // Move clouds slowly across the sky
        for (int[] cloud : clouds) {
            cloud[0] += cloudSpeed;
            if (cloud[0] > getWidth()) {
                cloud[0] = -100; // Reset cloud position if it goes off-screen
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

    public ArrayList<int[]> getCarsRight() {
        return carsRight;
    }

    public void setCarsRight(ArrayList<int[]> carsRight) {
        this.carsRight = carsRight;
    }

    public ArrayList<int[]> getCarsLeft() {
        return carsLeft;
    }

    public void setCarsLeft(ArrayList<int[]> carsLeft) {
        this.carsLeft = carsLeft;
    }

    public ArrayList<int[]> getClouds() {
        return clouds;
    }

    public void setClouds(ArrayList<int[]> clouds) {
        this.clouds = clouds;
    }

    public int getCarSpeedRight() {
        return carSpeedRight;
    }

    public void setCarSpeedRight(int carSpeedRight) {
        this.carSpeedRight = carSpeedRight;
    }

    public int getCarSpeedLeft() {
        return carSpeedLeft;
    }

    public void setCarSpeedLeft(int carSpeedLeft) {
        this.carSpeedLeft = carSpeedLeft;
    }

    public int getCloudSpeed() {
        return cloudSpeed;
    }

    public void setCloudSpeed(int cloudSpeed) {
        this.cloudSpeed = cloudSpeed;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
