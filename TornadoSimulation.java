import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class TornadoSimulation extends JPanel implements ActionListener {
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Debris> debrisList;
    private int centerX, centerY;
    private Random random;

    public TornadoSimulation() {
        setPreferredSize(new Dimension(800, 600)); // Size of the window
        setBackground(new Color(0, 0, 0)); // Dark background to simulate night or storm
        timer = new Timer(30, this); // Timer for the animation
        timer.start();

        random = new Random();
        debrisList = new ArrayList<>();

        // Add random debris to the tornado
        for (int i = 0; i < 50; i++) {
            int radius = random.nextInt(200) + 50;
            int angle = random.nextInt(360);
            int size = random.nextInt(10) + 5;
            debrisList.add(new Debris(centerX, centerY, radius, angle, size));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2 + 100; // Center position a bit lower to simulate the tornado base near the ground

        drawTornado(g);
        drawDebris(g);
        drawTitle(g);
        drawFooterText(g);
    }

    private void drawTornado(Graphics g) {
        // Draw the tornado funnel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(192, 192, 192, 100)); // Light gray for a tornado-like color
        int[] xPoints = {centerX - 50, centerX + 50, centerX};
        int[] yPoints = {centerY - 300, centerY - 300, centerY};
        g2d.fillPolygon(xPoints, yPoints, 3); // Funnel shape for the tornado

        // Add swirling effect
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(255, 255, 255, 80));
        for (int i = 0; i < 10; i++) {
            g2d.drawOval(centerX - i * 20, centerY - i * 30, 40 + i * 40, 60);
        }
    }

    private void drawDebris(Graphics g) {
        for (Debris debris : debrisList) {
            debris.draw(g); // Draw each debris particle
            debris.move(); // Move each debris particle
        }
    }

    private void drawTitle(Graphics g) {
        // Title text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Tornado Simulation", getWidth() / 2 - 150, 50); // Title at the top-center
    }

    private void drawFooterText(Graphics g) {
        // Footer text
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30); // Footer at the bottom-center
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Continuously repaint the scene
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tornado Simulation");
        TornadoSimulation tornado = new TornadoSimulation();
        frame.add(tornado);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    // Inner class to represent individual debris particles swirling in the tornado
    class Debris {
        private int x, y, radius, angle, size;
        private double angularSpeed;

        public Debris(int centerX, int centerY, int radius, int angle, int size) {
            this.radius = radius;
            this.angle = angle;
            this.size = size;
            this.angularSpeed = 0.05 + random.nextDouble() * 0.05; // Random speed for each debris
            updatePosition(centerX, centerY);
        }

        public void draw(Graphics g) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, size, size); // Drawing debris as small squares
        }

        public void move() {
            // Update angle to simulate circular motion
            angle += angularSpeed * 180 / Math.PI;
            if (angle >= 360) {
                angle -= 360;
            }
            updatePosition(centerX, centerY);
        }

        private void updatePosition(int centerX, int centerY) {
            x = centerX + (int) (radius * Math.cos(Math.toRadians(angle))) - size / 2;
            y = centerY + (int) (radius * Math.sin(Math.toRadians(angle))) - size / 2;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public double getAngularSpeed() {
            return angularSpeed;
        }

        public void setAngularSpeed(double angularSpeed) {
            this.angularSpeed = angularSpeed;
        }
    }
}
