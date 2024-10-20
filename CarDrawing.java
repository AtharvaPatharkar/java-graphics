import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CarDrawing extends JPanel implements ActionListener {

    private int carX = 50;  // X position of the car
    private int carSpeed = 5;  // Speed of the car movement
    private int cloudX1 = 200, cloudX2 = 600;  // Cloud positions
    private int wheelAngle = 0;  // For animating wheel rotation

    // Timer to control the animation speed (repaint every 50ms)
    private Timer timer = new Timer(50, this);

    public CarDrawing() {
        timer.start();  // Start the timer for animation
    }

    // Override paintComponent to draw the car and animated elements
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Cast Graphics to Graphics2D to enable 2D drawing
        Graphics2D g2d = (Graphics2D) g;

        // Anti-aliasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color for the panel
        this.setBackground(Color.CYAN);

        // Draw background elements: clouds, mountains, and trees
        drawMountains(g2d);
        drawCloud(g2d, cloudX1, 100);
        drawCloud(g2d, cloudX2, 150);
        drawTrees(g2d);

        // Draw road lines to simulate motion
        drawRoad(g2d);

        // Calculate car Y position (on the road, near bottom of panel)
        int carY = getHeight() - 200;

        // Drawing the car
        drawCar(g2d, carX, carY);

        // Draw the car's text
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Designed by AP", (getWidth() - g2d.getFontMetrics().stringWidth("Designed by AP")) / 2, carY + 150);
    }

    // Method to draw the car
    private void drawCar(Graphics2D g2d, int carX, int carY) {
        int carWidth = 300;
        int carHeight = 100;

        // Drawing the car body
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(carX, carY - 50, carWidth, carHeight, 20, 20);  // Main body of the car
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(carX + 40, carY - 80, 220, 50, 20, 20);  // Top part of the car

        // Add windows
        g2d.setColor(Color.WHITE);
        g2d.fillRect(carX + 60, carY - 70, 80, 40);  // Front window
        g2d.fillRect(carX + 160, carY - 70, 80, 40);  // Rear window

        // Drawing wheels with new design and rotation
        drawDetailedWheel(g2d, carX + 30, carY + 20);  // Left wheel
        drawDetailedWheel(g2d, carX + 200, carY + 20);  // Right wheel
    }

    // Method to draw a new, detailed wheel design
    private void drawDetailedWheel(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 70, 70);  // Main wheel

        // Draw the rim with a smaller circle
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x + 10, y + 10, 50, 50);

        // Draw spokes (rotating)
        g2d.setColor(Color.DARK_GRAY);
        g2d.rotate(Math.toRadians(wheelAngle), x + 35, y + 35);  // Rotate around wheel center
        for (int i = 0; i < 5; i++) {
            g2d.drawLine(x + 35, y + 35, x + 35 + (int)(30 * Math.cos(i * 2 * Math.PI / 5)), y + 35 + (int)(30 * Math.sin(i * 2 * Math.PI / 5)));
        }
        g2d.rotate(-Math.toRadians(wheelAngle), x + 35, y + 35);  // Reset rotation
    }

    // Method to draw moving clouds
    private void drawCloud(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, 80, 50);  // Main cloud
        g2d.fillOval(x + 30, y - 20, 60, 40);  // Top part
        g2d.fillOval(x + 50, y + 10, 70, 50);  // Right part
    }

    // Method to draw mountains
    private void drawMountains(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34));  // Dark green color for mountains
        int[] xPoints = {50, 150, 250};  // Mountain 1
        int[] yPoints = {300, 100, 300};
        g2d.fillPolygon(xPoints, yPoints, 3);

        int[] xPoints2 = {200, 300, 400};  // Mountain 2
        int[] yPoints2 = {350, 150, 350};
        g2d.fillPolygon(xPoints2, yPoints2, 3);
    }

    // Method to draw trees on the side of the road
    private void drawTrees(Graphics2D g2d) {
        g2d.setColor(new Color(139, 69, 19));  // Tree trunks (brown)
        g2d.fillRect(100, getHeight() - 250, 20, 50);  // Trunk 1
        g2d.fillRect(500, getHeight() - 250, 20, 50);  // Trunk 2

        g2d.setColor(new Color(34, 139, 34));  // Tree leaves (green)
        g2d.fillOval(80, getHeight() - 300, 60, 60);  // Leaves 1
        g2d.fillOval(480, getHeight() - 300, 60, 60);  // Leaves 2
    }

    // Method to draw the road
    private void drawRoad(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, getHeight() - 100, getWidth(), 100);  // Road background

        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < getWidth(); i += 60) {
            g2d.fillRect(i, getHeight() - 60, 40, 10);  // Dashed road lines
        }
    }

    // Timer action to update positions and repaint
    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the car to the right, and reset when it reaches the end
        carX += carSpeed;
        if (carX > getWidth()) {
            carX = -300;  // Reset position to the left
        }

        // Move the clouds
        cloudX1 -= 2;
        cloudX2 -= 2;
        if (cloudX1 < -100) cloudX1 = getWidth();
        if (cloudX2 < -100) cloudX2 = getWidth() + 200;

        // Rotate the wheels (increment the angle)
        wheelAngle += 10;
        if (wheelAngle >= 360) {
            wheelAngle = 0;
        }

        // Trigger repaint to animate the movement
        repaint();
    }

    // Main function to set up the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Drawing Example with Animation and Elements");
        CarDrawing carPanel = new CarDrawing();
        
        frame.add(carPanel);
        frame.setSize(800, 600);  // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(int carSpeed) {
        this.carSpeed = carSpeed;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
