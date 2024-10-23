import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class AlienPlanetSurface extends JPanel implements ActionListener {
    private Timer timer;
    private int ufoX, ufoY; // UFO Position
    private int ufoSpeed = 2; // UFO movement speed
    private int alienX, alienY; // Alien creature position
    private int alienSpeed = 1; // Alien creature movement speed
    private int planetX, planetSpeed; // Alien planet position and speed
    private int cloudX1, cloudX2, cloudSpeed; // Cloud positions
    private Random random;

    public AlienPlanetSurface() {
        setPreferredSize(new Dimension(800, 600)); // Set the size of the panel
        setBackground(new Color(20, 30, 40)); // Dark background for alien atmosphere
        timer = new Timer(16, this); // Timer for animation (~60 FPS)
        timer.start();

        random = new Random();
        ufoX = getWidth() / 2; // UFO starts near the center
        ufoY = 100; // UFO's starting height

        alienX = 50; // Alien creature starts near the left
        alienY = getHeight() - 140; // Alien creature position above the surface

        planetX = -200; // Planet starts off the screen (left)
        planetSpeed = 1; // Speed of the alien planet

        // Initialize cloud positions
        cloudX1 = -100; 
        cloudX2 = getWidth();
        cloudSpeed = 2; // Cloud movement speed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g); // Draw the title at the top
        drawAlienPlanet(g); // Draw and animate the alien planet
        drawMountains(g); // Draw mountains
        drawUFO(g); // Draw and animate the UFO
        drawClouds(g); // Draw and animate the clouds
        drawAlienCreature(g); // Draw and animate an alien creature
        drawFooterText(g); // Draw footer text at the bottom
        drawAlienSurface(g); // Draw the alien planet's surface
    }

    private void drawTitle(Graphics g) {
        // Title text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Alien Planet Surface", getWidth() / 2 - 150, 50);
    }

    private void drawAlienPlanet(Graphics g) {
        // Draw a large alien planet in the sky
        g.setColor(new Color(160, 82, 45)); // Brownish alien planet
        g.fillOval(planetX, 50, 200, 200); // Planet size 200x200

        // Move the alien planet from left to right
        planetX += planetSpeed;
        if (planetX > getWidth()) {
            planetX = -200; // Reset position when off screen
        }
    }

    private void drawAlienSurface(Graphics g) {
        // Alien surface ground
        g.setColor(new Color(100, 50, 50)); // Dark reddish ground
        g.fillRect(0, getHeight() - 100, getWidth(), 100);
        
        // Draw alien plant-like structures
        g.setColor(Color.GREEN);
        for (int i = 50; i < getWidth(); i += 100) {
            g.fillRect(i, getHeight() - 120, 10, 30);
            g.fillOval(i - 10, getHeight() - 130, 30, 20);
        }
    }

    private void drawMountains(Graphics g) {
        // Alien mountains in the background
        g.setColor(new Color(80, 40, 60)); // Purplish mountains
        int[] xPoints1 = {0, 150, 300};
        int[] yPoints1 = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPoints1, yPoints1, 3);

        int[] xPoints2 = {200, 400, 600};
        int[] yPoints2 = {getHeight() - 100, getHeight() - 400, getHeight() - 100};
        g.fillPolygon(xPoints2, yPoints2, 3);

        int[] xPoints3 = {500, 650, 800};
        int[] yPoints3 = {getHeight() - 100, getHeight() - 300, getHeight() - 100};
        g.fillPolygon(xPoints3, yPoints3, 3);
    }

    private void drawUFO(Graphics g) {
        // Draw UFO
        g.setColor(new Color(200, 200, 200)); // UFO body color
        g.fillOval(ufoX - 40, ufoY, 80, 30); // UFO's oval body
        
        g.setColor(Color.RED); // UFO's light
        g.fillOval(ufoX - 10, ufoY + 5, 20, 10);
        
        // Simulate UFO hovering by oscillating it up and down
        if (ufoY < 110 || ufoY > 120) {
            ufoSpeed = -ufoSpeed; // Change direction when UFO reaches bounds
        }
        ufoY += ufoSpeed; // Move UFO vertically
    }

    private void drawClouds(Graphics g) {
        // Draw animated clouds
        g.setColor(new Color(220, 220, 220)); // Light gray clouds
        g.fillOval(cloudX1, 80, 150, 60); // First cloud
        g.fillOval(cloudX2, 120, 200, 80); // Second cloud
        
        // Move clouds across the screen
        cloudX1 += cloudSpeed;
        cloudX2 -= cloudSpeed;
        
        // Reset clouds when they move out of the screen
        if (cloudX1 > getWidth()) {
            cloudX1 = -150;
        }
        if (cloudX2 < -200) {
            cloudX2 = getWidth();
        }
    }

    private void drawAlienCreature(Graphics g) {
        // Draw an alien creature moving across the surface
        g.setColor(new Color(100, 250, 100)); // Green alien
        g.fillOval(alienX, alienY, 30, 30); // Alien's round head
        
        g.fillRect(alienX + 10, alienY + 30, 10, 20); // Alien's body
        g.fillRect(alienX + 5, alienY + 50, 5, 15); // Left leg
        g.fillRect(alienX + 20, alienY + 50, 5, 15); // Right leg
        g.fillRect(alienX - 10, alienY + 35, 10, 5); // Left arm
        g.fillRect(alienX + 30, alienY + 35, 10, 5); // Right arm
        
        // Move the alien creature from left to right
        alienX += alienSpeed;
        if (alienX > getWidth()) {
            alienX = -30; // Reset position when off screen
        }
    }

    private void drawFooterText(Graphics g) {
        // Footer text
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", getWidth() / 2 - 50, getHeight() - 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Continuously repaint the screen to animate
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Alien Planet Surface Design");
        AlienPlanetSurface simulation = new AlienPlanetSurface();
        frame.add(simulation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getCloudSpeed() {
        return cloudSpeed;
    }

    public void setCloudSpeed(int cloudSpeed) {
        this.cloudSpeed = cloudSpeed;
    }

    public int getPlanetSpeed() {
        return planetSpeed;
    }

    public void setPlanetSpeed(int planetSpeed) {
        this.planetSpeed = planetSpeed;
    }

    public int getAlienSpeed() {
        return alienSpeed;
    }

    public void setAlienSpeed(int alienSpeed) {
        this.alienSpeed = alienSpeed;
    }

    public int getAlienY() {
        return alienY;
    }

    public void setAlienY(int alienY) {
        this.alienY = alienY;
    }

    public int getUfoX() {
        return ufoX;
    }

    public void setUfoX(int ufoX) {
        this.ufoX = ufoX;
    }

    public int getUfoY() {
        return ufoY;
    }

    public void setUfoY(int ufoY) {
        this.ufoY = ufoY;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
