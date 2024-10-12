import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StreetView extends JPanel implements ActionListener {

    private int cloudX = 0;
    private int carX1 = -200, carX2 = 100, carX3 = -350;  // Multiple cars
    private Timer timer;
    private final int DELAY = 10;  // Speed of animation

    public StreetView() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.CYAN);

        // Timer for animation
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStreet(g);
        drawGreenGarden(g);
        drawBottomText(g);
        drawClouds(g);
        drawCars(g);
        drawTrees(g);
        drawBuildings(g);
        drawBorder(g);
        drawTitle(g);
    }

    private void drawStreet(Graphics g) {
        // Draw the road
        g.setColor(Color.GRAY);
        g.fillRect(0, 400, getWidth(), 100);

        // Draw road lines
        g.setColor(Color.YELLOW);
        for (int i = 0; i < getWidth(); i += 50) {
            g.fillRect(i, 445, 30, 5);  // Road divider
        }
    }

    private void drawGreenGarden(Graphics g) {
        // Draw a green garden below the road
        g.setColor(new Color(34, 139, 34));  // Dark green color for grass
        g.fillRect(0, 500, getWidth(), getHeight() - 500);  // Green garden below road
    }

    private void drawTitle(Graphics g) {
        // Draw the title in the center of the screen
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Street View", getWidth() / 2 - 130, 100);  // Title
    }

    private void drawBottomText(Graphics g) {
        // Draw "Code with AP" at the bottom center
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Code with AP", getWidth() / 2 - 110, getHeight() - 50);  // Bottom center text
    }

    private void drawClouds(Graphics g) {
        // Draw moving clouds
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, 50, 100, 50);
        g.fillOval(cloudX + 60, 70, 100, 50);
        g.fillOval(cloudX - 60, 70, 100, 50);

        g.fillOval(cloudX + 300, 50, 120, 60);
        g.fillOval(cloudX + 360, 70, 120, 60);
        g.fillOval(cloudX + 240, 70, 120, 60);
    }

    private void drawCars(Graphics g) {
        // First Car (Red)
        g.setColor(Color.RED);
        g.fillRect(carX1, 420, 150, 50);  // Car body
        g.setColor(Color.BLACK);
        g.fillOval(carX1 + 10, 460, 30, 30);  // Left wheel
        g.fillOval(carX1 + 110, 460, 30, 30); // Right wheel

        // Second Car (Blue)
        g.setColor(Color.BLUE);
        g.fillRect(carX2, 430, 130, 40);  // Car body
        g.setColor(Color.BLACK);
        g.fillOval(carX2 + 10, 460, 30, 30);  // Left wheel
        g.fillOval(carX2 + 90, 460, 30, 30);  // Right wheel

        // Third Car (Green)
        g.setColor(Color.GREEN);
        g.fillRect(carX3, 440, 140, 40);  // Car body
        g.setColor(Color.BLACK);
        g.fillOval(carX3 + 10, 470, 30, 30);  // Left wheel
        g.fillOval(carX3 + 100, 470, 30, 30); // Right wheel
    }

    private void drawTrees(Graphics g) {
        // Draw trees in the green garden
        g.setColor(new Color(34, 139, 34));  // Dark green color for tree foliage
        g.fillOval(100, 450, 50, 100);  // Left tree foliage
        g.fillRect(120, 550, 10, 40);   // Left tree trunk

        g.fillOval(getWidth() - 150, 450, 50, 100);  // Right tree foliage
        g.fillRect(getWidth() - 130, 550, 10, 40);   // Right tree trunk
    }

    private void drawBuildings(Graphics g) {
        // Draw buildings in the background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 250, 100, 150);  // Left building
        g.fillRect(600, 250, 120, 150);  // Right building

        // Draw windows on the buildings
        g.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            g.fillRect(110, 260 + i * 30, 20, 20);  // Left building windows
            g.fillRect(670, 260 + i * 30, 20, 20);  // Right building windows
        }
    }

    private void drawBorder(Graphics g) {
        // Draw a border around the panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);  // Border
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update positions for the clouds and cars
        cloudX += 1;
        carX1 += 2;  // Speed of first car
        carX2 += 3;  // Speed of second car
        carX3 += 1;  // Speed of third car

        // Reset positions when they go off-screen
        if (cloudX > getWidth()) {
            cloudX = -200;
        }
        if (carX1 > getWidth()) {
            carX1 = -200;
        }
        if (carX2 > getWidth()) {
            carX2 = -200;
        }
        if (carX3 > getWidth()) {
            carX3 = -200;
        }

        // Repaint the panel to update the animation
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Enhanced Street View Design");
        StreetView streetView = new StreetView();

        frame.add(streetView);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setVisible(true);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
