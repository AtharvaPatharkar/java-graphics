import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrafficLightSimulation extends JPanel implements ActionListener {
    private Timer timer;
    private int lightState; // 0 = red, 1 = yellow, 2 = green
    private int[] carX; // Car positions
    private int carSpeed; // Speed of the cars
    private boolean isCarMoving; // Flag to check if cars are moving
    private int lightTimer; // Timer for changing lights
    private Color[] carColors; // Array of colors for multiple cars

    public TrafficLightSimulation() {
        setPreferredSize(new Dimension(800, 600)); // Set panel size
        setBackground(new Color(135, 206, 235)); // Sky blue background
        timer = new Timer(16, this); // 60 FPS timer
        timer.start();

        lightState = 0; // Start with red light
        carX = new int[] {50, -150, -250}; // Start positions for multiple cars
        carSpeed = 2; // Speed of the cars
        isCarMoving = false; // Cars start stationary when light is red
        lightTimer = 0; // Timer for traffic light changes

        carColors = new Color[] {Color.RED, Color.BLUE, Color.GREEN}; // Colors for multiple cars
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g); // Draw the title at the top
        drawRoad(g); // Draw the road
        drawTrees(g); // Draw trees beside the road
        drawTrafficLight(g); // Draw and animate the traffic light
        drawCars(g); // Draw and animate the cars
        drawClouds(g); // Draw moving clouds
        drawFooterText(g); // Draw footer text at the bottom
    }

    private void drawTitle(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Traffic Light with Car Stop/Go", getWidth() / 2 - 200, 50);
    }

    private void drawRoad(Graphics g) {
        // Draw the road at the bottom
        g.setColor(new Color(50, 50, 50)); // Dark grey road
        g.fillRect(0, getHeight() - 150, getWidth(), 150);

        // Draw lane dividers
        g.setColor(Color.WHITE);
        for (int i = 0; i < getWidth(); i += 40) {
            g.fillRect(i, getHeight() - 80, 20, 5); // Dashed lane dividers
        }
    }

    private void drawTrafficLight(Graphics g) {
        // Traffic light post
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getWidth() / 2 - 30, getHeight() - 300, 20, 150); // Light post

        // Traffic light box
        g.setColor(Color.BLACK);
        g.fillRect(getWidth() / 2 - 50, getHeight() - 350, 60, 150); // Light box

        // Red light
        g.setColor(lightState == 0 ? Color.RED : Color.DARK_GRAY); // Highlight red when active
        g.fillOval(getWidth() / 2 - 40, getHeight() - 340, 40, 40);

        // Yellow light
        g.setColor(lightState == 1 ? Color.YELLOW : Color.DARK_GRAY); // Highlight yellow when active
        g.fillOval(getWidth() / 2 - 40, getHeight() - 290, 40, 40);

        // Green light
        g.setColor(lightState == 2 ? Color.GREEN : Color.DARK_GRAY); // Highlight green when active
        g.fillOval(getWidth() / 2 - 40, getHeight() - 240, 40, 40);

        // Change light state based on timer
        lightTimer++;
        if (lightTimer % 200 == 0) {
            lightState = (lightState + 1) % 3; // Cycle between 0 (red), 1 (yellow), 2 (green)
            isCarMoving = lightState == 2; // Cars move only on green light
        }
    }

    private void drawCars(Graphics g) {
        for (int i = 0; i < carX.length; i++) {
            // Draw the car body with different colors
            g.setColor(carColors[i]);
            g.fillRect(carX[i], getHeight() - 180, 100, 40); // Car body

            // Draw the car windows
            g.setColor(Color.CYAN);
            g.fillRect(carX[i] + 20, getHeight() - 170, 30, 20); // Car window 1
            g.fillRect(carX[i] + 55, getHeight() - 170, 30, 20); // Car window 2

            // Draw the car wheels
            g.setColor(Color.BLACK);
            g.fillOval(carX[i] + 10, getHeight() - 140, 30, 30); // Front wheel
            g.fillOval(carX[i] + 60, getHeight() - 140, 30, 30); // Rear wheel

            // Move cars only when light is green
            if (isCarMoving) {
                carX[i] += carSpeed;
            }

            // Reset car position when it goes off the screen
            if (carX[i] > getWidth()) {
                carX[i] = -100;
            }
        }
    }

    private void drawTrees(Graphics g) {
        // Draw simple trees along the road

        for (int i = 100; i < getWidth(); i += 200) {
            g.setColor(new Color(139, 69, 19)); // Tree trunk color
            g.fillRect(i + 25, getHeight() - 210, 20, 60); // Tree trunk
            g.setColor(new Color(34, 139, 34)); // Reset tree foliage color for next tree
            g.fillOval(i, getHeight() - 300, 70, 100); // Tree foliage

            
        }
    }

    private void drawClouds(Graphics g) {
        // Draw clouds moving across the sky
        g.setColor(Color.WHITE);
        for (int i = 0; i < getWidth(); i += 200) {
            int offset = (lightTimer % getWidth()) / 2; // Animate clouds moving
            g.fillOval(i + offset, 100, 100, 50); // Main cloud part
            g.fillOval(i + 30 + offset, 80, 80, 40); // Upper cloud part
            g.fillOval(i + 60 + offset, 100, 90, 50); // Lower cloud part
        }
    }

    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Continuously repaint the screen for animation
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light with Car Stop/Go");
        TrafficLightSimulation simulation = new TrafficLightSimulation();
        frame.add(simulation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    public Color[] getCarColors() {
        return carColors;
    }

    public void setCarColors(Color[] carColors) {
        this.carColors = carColors;
    }

    public int getCarSpeed() {
        return carSpeed;
    }

    public void setCarSpeed(int carSpeed) {
        this.carSpeed = carSpeed;
    }

    public int[] getCarX() {
        return carX;
    }

    public void setCarX(int[] carX) {
        this.carX = carX;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
