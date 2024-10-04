import java.awt.*;
import javax.swing.*;

public class TempleDrawing extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for advanced features
        Graphics2D g2d = (Graphics2D) g;

        // Set background color
        setBackground(Color.LIGHT_GRAY);

        // Enable anti-aliasing for smoother drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the width and height of the panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Set temple drawing in the center of the panel
        int centerX = panelWidth / 2;
        int centerY = panelHeight / 2;

        // Temple base dimensions
        int templeWidth = 200;
        int templeHeight = 150;

        // Drawing the base of the temple (using orange color)
        g2d.setColor(Color.ORANGE);  // Orange color for the temple base
        g2d.fillRect(centerX - templeWidth / 2, centerY, templeWidth, templeHeight);

        // Drawing the roof (triangle)
        Polygon roof = new Polygon();
        roof.addPoint(centerX, centerY - templeHeight / 2);  // Top point
        roof.addPoint(centerX - templeWidth / 2, centerY);   // Bottom left
        roof.addPoint(centerX + templeWidth / 2, centerY);   // Bottom right

        // Fill the roof with a color
        g2d.setColor(new Color(160, 82, 45));  // Brown color for the roof
        g2d.fillPolygon(roof);

        // Drawing the door in the temple (centered rectangle)
        int doorWidth = 40;
        int doorHeight = 60;
        g2d.setColor(Color.DARK_GRAY);  // Dark gray for the door
        g2d.fillRect(centerX - doorWidth / 2, centerY + templeHeight - doorHeight, doorWidth, doorHeight);

        // Drawing windows (two small rectangles on the sides)
        int windowWidth = 30;
        int windowHeight = 30;
        g2d.setColor(Color.BLACK);  // Black color for the windows
        // Left window
        g2d.fillRect(centerX - templeWidth / 2 + 30, centerY + templeHeight / 3, windowWidth, windowHeight);
        // Right window
        g2d.fillRect(centerX + templeWidth / 2 - 30 - windowWidth, centerY + templeHeight / 3, windowWidth, windowHeight);

        // Drawing columns (simple rectangles)
        int columnWidth = 20;
        int columnHeight = 100;
        int columnSpacing = (templeWidth - 4 * columnWidth) / 3;  // Space between columns

        // Draw 4 columns
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 4; i++) {
            int x = centerX - templeWidth / 2 + i * (columnWidth + columnSpacing);
            g2d.fillRect(x, centerY, columnWidth, columnHeight);
        }

        // Drawing the border around the temple (3D border effect)
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRect(centerX - templeWidth / 2 - 5, centerY - 5, templeWidth + 10, templeHeight + 10);

        // Drawing the flag on top of the roof
        int flagPoleHeight = 50;
        int flagWidth = 30;

        // Draw flagpole
        g2d.setColor(Color.BLACK);
        g2d.fillRect(centerX - 2, centerY - templeHeight / 2 - flagPoleHeight, 4, flagPoleHeight);

        // Draw the flag as a triangle (orange)
        Polygon flag = new Polygon();
        flag.addPoint(centerX, centerY - templeHeight / 2 - flagPoleHeight + 5);  // Top-left of the triangle
        flag.addPoint(centerX + flagWidth, centerY - templeHeight / 2 - flagPoleHeight + 15);  // Bottom-right
        flag.addPoint(centerX, centerY - templeHeight / 2 - flagPoleHeight + 25);  // Bottom-left

        g2d.setColor(Color.ORANGE);
        g2d.fillPolygon(flag);

        // Draw the "ॐ" symbol on the flag
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2d.drawString("ॐ", centerX + 5, centerY - templeHeight / 2 - flagPoleHeight + 20);

        // Add title at the top
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 24));
        String title = "Temple Drawing";
        FontMetrics fm = g2d.getFontMetrics();
        int titleX = (panelWidth - fm.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, 50);

        // Add text below the temple
        String text = "A Temple Drawing with Door, Windows, and Flag";
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
        int textX = (panelWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
        g2d.drawString(text, textX, centerY + templeHeight + 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Temple Drawing with Border, Door, Windows, and Flag");
        TempleDrawing panel = new TempleDrawing();

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(panel);
        frame.setVisible(true);
    }
}
