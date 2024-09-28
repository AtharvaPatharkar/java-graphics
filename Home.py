import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(new Color(135, 206, 250));  // Sky blue
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the sun
        g.setColor(Color.ORANGE);
        g.fillOval(50, 50, 80, 80);

        // Draw the road at the bottom
        g.setColor(Color.GRAY);
        g.fillRect(0, 500, getWidth(), 100);

        // Draw the garden above the road
        g.setColor(Color.GREEN);
        g.fillRect(0, 450, getWidth(), 50);

        // Draw mountains with different sizes
        drawMountain(g, new Color(160, 82, 45), 150, 300, 150, 100);  // Mountain 1
        drawMountain(g, new Color(139, 69, 19), 350, 270, 200, 130);  // Mountain 2 (Larger)
        drawMountain(g, new Color(110, 54, 42), 550, 320, 100, 80);   // Mountain 3 (Smaller)
        drawMountain(g, new Color(120, 75, 50), 750, 280, 180, 120);  // Mountain 4 (Medium)

        // Draw multiple houses with different colors and positions
        drawHouse(g, Color.YELLOW, 50, 250);    // House 1
        drawHouse(g, Color.PINK, 300, 250);     // House 2 (Color Changed)
        drawHouse(g, Color.MAGENTA, 550, 250);  // House 3
        drawHouse(g, Color.ORANGE, 800, 250);   // House 4

        // Draw title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("My Beautiful Homes", 250, 50);

        // Draw garden details (flowers) along the road
        g.setColor(Color.PINK);
        for (int i = 20; i < getWidth(); i += 50) {
            g.fillOval(i, 460, 10, 10);
        }
    }

    // Method to draw a mountain at a specific position, size, and color
    private void drawMountain(Graphics g, Color color, int x, int y, int width, int height) {
        g.setColor(color);
        int[] xPoints = {x, x + width / 2, x + width};
        int[] yPoints = {y + height, y - height, y + height};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    // Method to draw a house at a specific position and color
    private void drawHouse(Graphics g, Color baseColor, int x, int y) {
        // Draw the base of the house
        g.setColor(baseColor);
        g.fillRect(x, y, 200, 150);

        // Draw the roof of the house
        g.setColor(Color.RED);
        int[] xPoints = {x, x + 100, x + 200};
        int[] yPoints = {y, y - 80, y};
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw the door
        g.setColor(new Color(139, 69, 19)); // Brown color
        g.fillRect(x + 80, y + 80, 40, 70);

        // Draw windows
        g.setColor(Color.CYAN);
        g.fillRect(x + 20, y + 30, 40, 40);
        g.fillRect(x + 140, y + 30, 40, 40);

        // Draw the chimney
        g.setColor(Color.RED);
        g.fillRect(x + 150, y - 50, 30, 50);

        // Draw the door handle
        g.setColor(Color.BLACK);
        g.fillOval(x + 110, y + 115, 7, 7);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("House Drawing");
        Home panel = new Home();
        frame.add(panel);
        frame.setSize(1000, 700);  // Adjusted size for better viewing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
