import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OrbitSimulation extends JPanel implements ActionListener {

    // Timer for animation
    private Timer timer;
    private int angleEarth = 0;
    private int angleMoon = 0;
    private int angleRocket = 0;
    private int angleLatitude = 0;  // For rotating Earth's lines of latitude

    // Constructor to set up the timer
    public OrbitSimulation() {
        timer = new Timer(50, this); // Every 50ms, repaint for animation
        timer.start();
    }

    // Main method to set up the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Orbit Simulation");
        OrbitSimulation orbitSimulation = new OrbitSimulation();

        frame.add(orbitSimulation);
        frame.setSize(800, 800);  // Window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Custom painting logic
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        this.setBackground(Color.BLACK);

        // Convert Graphics to Graphics2D for smoother animations
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the center of the screen
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw the title at the top center
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String title = "Orbit Simulation";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, centerX - titleWidth / 2, 40);

        // Draw the Sun at the center
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(centerX - 40, centerY - 40, 80, 80);

        // Earth's Orbit (around the Sun)
        g2d.setColor(Color.GRAY);
        int orbitRadiusEarth = 200;
        g2d.drawOval(centerX - orbitRadiusEarth, centerY - orbitRadiusEarth, 2 * orbitRadiusEarth, 2 * orbitRadiusEarth);

        // Earth's position around the Sun
        int earthX = centerX + (int) (orbitRadiusEarth * Math.cos(Math.toRadians(angleEarth)));
        int earthY = centerY + (int) (orbitRadiusEarth * Math.sin(Math.toRadians(angleEarth)));

        // Draw the Earth
        int earthRadius = 60;
        g2d.setColor(Color.BLUE);
        g2d.fillOval(earthX - earthRadius / 2, earthY - earthRadius / 2, earthRadius, earthRadius);

        // Draw Earth's Lines of Latitude
        g2d.setColor(Color.WHITE);
        int numLatitudes = 5;
        for (int i = 1; i <= numLatitudes; i++) {
            double latitudeAngle = i * (180.0 / (numLatitudes + 1)) - 90; // Latitude angles
            int latitudeRadius = (int) (earthRadius * Math.cos(Math.toRadians(latitudeAngle))); // Radius of each line
            g2d.drawArc(earthX - latitudeRadius / 2, earthY - earthRadius / 2, latitudeRadius, earthRadius, angleLatitude, 360); // Rotating latitude
        }

        // Draw the horizontal line across the Earth (rotating with the Earth)
        g2d.setColor(Color.WHITE);
        double horizontalLineAngle = Math.toRadians(angleLatitude); // Rotation of the horizontal line
        int halfEarthRadius = earthRadius / 2;

        // Calculate the endpoints of the rotating horizontal line
        int lineX1 = (int) (earthX - halfEarthRadius * Math.cos(horizontalLineAngle));
        int lineY1 = (int) (earthY - halfEarthRadius * Math.sin(horizontalLineAngle));
        int lineX2 = (int) (earthX + halfEarthRadius * Math.cos(horizontalLineAngle));
        int lineY2 = (int) (earthY + halfEarthRadius * Math.sin(horizontalLineAngle));

        // Draw the rotating horizontal line
        g2d.drawLine(lineX1, lineY1, lineX2, lineY2);

        // Moon's Orbit (around the Earth)
        g2d.setColor(Color.LIGHT_GRAY);
        int orbitRadiusMoon = 80;
        g2d.drawOval(earthX - orbitRadiusMoon, earthY - orbitRadiusMoon, 2 * orbitRadiusMoon, 2 * orbitRadiusMoon);

        // Moon's position around the Earth
        int moonX = earthX + (int) (orbitRadiusMoon * Math.cos(Math.toRadians(angleMoon)));
        int moonY = earthY + (int) (orbitRadiusMoon * Math.sin(Math.toRadians(angleMoon)));

        // Draw the Moon
        g2d.setColor(Color.WHITE);
        g2d.fillOval(moonX - 10, moonY - 10, 20, 20);

        // Rocket's Orbit (around the Earth)
        g2d.setColor(Color.RED);
        int orbitRadiusRocket = 120;
        int rocketX = earthX + (int) (orbitRadiusRocket * Math.cos(Math.toRadians(angleRocket)));
        int rocketY = earthY + (int) (orbitRadiusRocket * Math.sin(Math.toRadians(angleRocket)));

        // Draw the Rocket
        g2d.fillOval(rocketX - 15, rocketY - 15, 30, 30); // Simple rocket as a circle
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(rocketX - 5, rocketY + 15, 10, 20);  // Rocket exhaust

        // Draw the text "Code with AP" at the bottom center
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        String text = "Code with AP";
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text, centerX - textWidth / 2, getHeight() - 40); // Bottom center
    }

    // ActionListener method to handle animation updates
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update angles for animation
        angleEarth = (angleEarth + 2) % 360;   // Earth orbiting the sun
        angleMoon = (angleMoon + 5) % 360;     // Moon orbiting the Earth
        angleRocket = (angleRocket + 3) % 360; // Rocket orbiting the Earth
        angleLatitude = (angleLatitude + 1) % 360; // Rotating Earth's lines of latitude and horizontal line

        // Trigger repaint
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
