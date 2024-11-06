import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class SolarSystem extends JPanel implements ActionListener {
    private Timer timer;
    private int angleMercury = 0, angleVenus = 0, angleEarth = 0, angleMars = 0, angleJupiter = 0, angleSaturn = 0, angleUranus = 0, angleNeptune = 0;
    private int angleMoon = 0, angleComet = 0;
    private final int radiusMercury = 50, radiusVenus = 80, radiusEarth = 110, radiusMars = 150, radiusJupiter = 200, radiusSaturn = 250, radiusUranus = 300, radiusNeptune = 350;
    private final int radiusMoon = 20;
    private final int radiusComet = 400;
    private Random random = new Random();

    public SolarSystem() {
        timer = new Timer(100, this); // Timer for animation (100ms delay for slower rotation)
        timer.start();
        setPreferredSize(new Dimension(800, 800)); // Set window size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK); // Background color
        
        // Draw starfield
        drawStarfield(g);

        // Draw Title and Text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.drawString("Code with AP", 300, 30); // Title at the top

        // Get the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw the sun
        g.setColor(Color.YELLOW);
        g.fillOval(centerX - 40, centerY - 40, 80, 80); // Sun in the center

        // Draw orbits
        g.setColor(Color.GRAY);
        drawOrbit(g, centerX, centerY, radiusMercury); // Mercury orbit
        drawOrbit(g, centerX, centerY, radiusVenus);   // Venus orbit
        drawOrbit(g, centerX, centerY, radiusEarth);   // Earth orbit
        drawOrbit(g, centerX, centerY, radiusMars);    // Mars orbit
        drawOrbit(g, centerX, centerY, radiusJupiter); // Jupiter orbit
        drawOrbit(g, centerX, centerY, radiusSaturn);  // Saturn orbit
        drawOrbit(g, centerX, centerY, radiusUranus);  // Uranus orbit
        drawOrbit(g, centerX, centerY, radiusNeptune); // Neptune orbit
        drawOrbit(g, centerX, centerY, radiusComet);   // Comet orbit

        // Draw planets
        drawPlanet(g, centerX, centerY, radiusMercury, angleMercury, Color.LIGHT_GRAY, 10); // Mercury
        drawPlanet(g, centerX, centerY, radiusVenus, angleVenus, Color.ORANGE, 15);        // Venus
        drawPlanet(g, centerX, centerY, radiusEarth, angleEarth, Color.BLUE, 20);          // Earth
        drawPlanet(g, centerX, centerY, radiusMars, angleMars, Color.RED, 18);             // Mars
        drawPlanet(g, centerX, centerY, radiusJupiter, angleJupiter, new Color(184, 134, 11), 30); // Jupiter
        drawPlanet(g, centerX, centerY, radiusSaturn, angleSaturn, Color.YELLOW, 28);      // Saturn
        drawPlanet(g, centerX, centerY, radiusUranus, angleUranus, new Color(72, 61, 139), 25); // Uranus
        drawPlanet(g, centerX, centerY, radiusNeptune, angleNeptune, Color.CYAN, 22);      // Neptune

        // Draw Earth's Moon
        drawMoon(g, centerX, centerY, radiusEarth, angleEarth, radiusMoon, angleMoon, Color.LIGHT_GRAY, 6);

        // Draw Comet
        drawPlanet(g, centerX, centerY, radiusComet, angleComet, Color.WHITE, 8); // Comet
    }

    // Helper method to draw orbits
    private void drawOrbit(Graphics g, int centerX, int centerY, int radius) {
        g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    // Helper method to draw planets
    private void drawPlanet(Graphics g, int centerX, int centerY, int radius, int angle, Color color, int size) {
        double rad = Math.toRadians(angle); // Convert angle to radians
        int planetX = centerX + (int) (radius * Math.cos(rad)) - size / 2;
        int planetY = centerY + (int) (radius * Math.sin(rad)) - size / 2;
        g.setColor(color);
        g.fillOval(planetX, planetY, size, size); // Draw planet
    }

    // Helper method to draw Earth's Moon
    private void drawMoon(Graphics g, int centerX, int centerY, int earthRadius, int earthAngle, int moonRadius, int moonAngle, Color color, int size) {
        double radEarth = Math.toRadians(earthAngle);
        int earthX = centerX + (int) (earthRadius * Math.cos(radEarth));
        int earthY = centerY + (int) (earthRadius * Math.sin(radEarth));
        
        double radMoon = Math.toRadians(moonAngle);
        int moonX = earthX + (int) (moonRadius * Math.cos(radMoon)) - size / 2;
        int moonY = earthY + (int) (moonRadius * Math.sin(radMoon)) - size / 2;
        
        g.setColor(color);
        g.fillOval(moonX, moonY, size, size); // Draw moon
    }

    // Helper method to draw random stars in the background
    private void drawStarfield(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int starSize = random.nextInt(3) + 1; // Random size for stars
            g.fillOval(x, y, starSize, starSize); // Draw star
        }
    }
@Override
public void actionPerformed(ActionEvent e) {
    // Update angles to simulate orbital movement (rotating effect)
    angleMercury = (angleMercury + 10) % 360;  // Mercury moves fastest (88 days)
    angleVenus = (angleVenus + 8) % 360;       // Venus (225 days)
    angleEarth = (angleEarth + 7) % 360;       // Earth (365 days)
    angleMars = (angleMars + 6) % 360;         // Mars slower than Earth (687 days)
    angleJupiter = (angleJupiter + 4) % 360;   // Jupiter slow (12 years)
    angleSaturn = (angleSaturn + 3) % 360;     // Saturn even slower (29 years)
    angleUranus = (angleUranus + 2) % 360;     // Uranus (84 years)
    angleNeptune = (angleNeptune + 1) % 360;   // Neptune (165 years)

    angleMoon = (angleMoon + 15) % 360;        // Moon rotates quickly around Earth (27 days)
    angleComet = (angleComet + 12) % 360;       // Comet can have variable speed

    // Repaint the screen for animation
    repaint();
}



    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Model");
        SolarSystem solarSystem = new SolarSystem();
        frame.add(solarSystem);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center window on screen
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
}
