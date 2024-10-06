import java.awt.*;
import javax.swing.*;

public class CenteredDesignWithAngles extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        setBackground(Color.BLACK);

        // Convert Graphics to Graphics2D for more advanced features
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get panel dimensions
        int width = getWidth();
        int height = getHeight();

        // Calculate center of the panel
        int centerX = width / 2;
        int centerY = height / 2;

        // Example design: A combination of arcs, lines, circles with angles and colors
        int radius = 150; // General radius for drawing circles and arcs

        // Draw concentric arcs with different angles
        for (int i = 0; i < 360; i += 45) {
            g2d.setColor(Color.getHSBColor(i / 360f, 1.0f, 1.0f)); // Different colors for each arc
            g2d.setStroke(new BasicStroke(4)); // Thicker lines

            // Draw arcs starting from different angles
            g2d.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, i, 90);
        }

        // Draw radial lines at various angles
        g2d.setStroke(new BasicStroke(2));
        for (int angle = 0; angle < 360; angle += 15) {
            double rad = Math.toRadians(angle); // Convert angle to radians
            int x = centerX + (int) (radius * 1.5 * Math.cos(rad)); // Calculate x-coordinate
            int y = centerY + (int) (radius * 1.5 * Math.sin(rad)); // Calculate y-coordinate

            g2d.setColor(Color.getHSBColor(angle / 360f, 1.0f, 1.0f)); // Dynamic color
            g2d.drawLine(centerX, centerY, x, y); // Draw lines from center to calculated point
        }

        // Draw a central star shape
        g2d.setColor(Color.WHITE);
        drawStar(g2d, centerX, centerY, radius / 2, radius, 8);

        // Draw concentric circles
        for (int i = 1; i <= 5; i++) {
            g2d.setColor(Color.getHSBColor(i * 0.15f, 1.0f, 1.0f));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(centerX - i * 30, centerY - i * 30, i * 60, i * 60); // Concentric circles
        }

        // Draw diagonal lines crossing the design
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(centerX - radius * 2, centerY - radius * 2, centerX + radius * 2, centerY + radius * 2);
        g2d.drawLine(centerX - radius * 2, centerY + radius * 2, centerX + radius * 2, centerY - radius * 2);

        // Draw another star with a different color in the center
        g2d.setColor(Color.CYAN);
        drawStar(g2d, centerX, centerY, radius / 4, radius / 2, 8);
    }

    // Helper function to draw a star shape
    private void drawStar(Graphics2D g2d, int centerX, int centerY, int innerRadius, int outerRadius, int points) {
        double angleStep = Math.PI / points; // Half of the angle between points
        int[] xPoints = new int[points * 2];
        int[] yPoints = new int[points * 2];

        for (int i = 0; i < points * 2; i++) {
            double angle = i * angleStep;
            int radius = (i % 2 == 0) ? outerRadius : innerRadius; // Alternate between outer and inner radius
            xPoints[i] = centerX + (int) (Math.cos(angle) * radius);
            yPoints[i] = centerY + (int) (Math.sin(angle) * radius);
        }

        g2d.fillPolygon(xPoints, yPoints, points * 2); // Fill the star with current color
    }

    public static void main(String[] args) {
        // Create a JFrame window
        JFrame frame = new JFrame("Centered Design with Angles and Colors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Add the custom drawing panel
        frame.add(new CenteredDesignWithAngles());

        // Make the window visible
        frame.setVisible(true);
    }
}
