import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RangoliDesign extends JPanel {

    // Method to draw a circle with a specified outline only (no background fill)
    private void drawCircle(Graphics2D g2d, int x, int y, int radius) {
        // Draw the outline of the circle
        g2d.setColor(Color.BLACK);
        g2d.draw(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
    }

    // Method to draw the Om symbol in the center
    private void drawOmSymbol(Graphics2D g2d, int x, int y) {
        g2d.setFont(new Font("SansSerif", Font.BOLD, 50));
        g2d.drawString("ॐ", x - 20, y + 20);
    }

    // Method to draw triangles in a circle
    private void drawTriangles(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.RED);
        int triangleHeight = 20;
        for (int i = 0; i < 3; i++) {
            int xOffset = (int) (radius * Math.cos(i * 2 * Math.PI / 3));
            int yOffset = (int) (radius * Math.sin(i * 2 * Math.PI / 3));
            int[] xPoints = { x, x + xOffset - 10, x + xOffset + 10 };
            int[] yPoints = { y - triangleHeight, y + yOffset, y + yOffset };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }

    // Method to draw squares in a circle
    private void drawSquares(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.BLUE);
        int squareSize = 20;
        for (int i = 0; i < 4; i++) {
            double angle = i * Math.PI / 2; // 90 degrees apart
            int squareX = (int) (x + radius * Math.cos(angle) - squareSize / 2);
            int squareY = (int) (y + radius * Math.sin(angle) - squareSize / 2);
            g2d.fillRect(squareX, squareY, squareSize, squareSize);
        }
    }

    // Method to draw "श्री" symbols in a circular pattern
    private void drawShriSymbols(Graphics2D g2d, int x, int y, int radius) {
        g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
        g2d.setColor(Color.MAGENTA);

        int numSymbols = 6; // Number of "श्री" symbols to draw
        for (int i = 0; i < numSymbols; i++) {
            double angle = i * 2 * Math.PI / numSymbols; // Divide evenly around the circle
            int xOffset = (int) (radius * Math.cos(angle));
            int yOffset = (int) (radius * Math.sin(angle));
            g2d.drawString("श्री", x + xOffset - 15, y + yOffset + 10); // Adjust the position
        }
    }

    // Method to draw "जय" symbols in a circular pattern
    private void drawJaiSymbols(Graphics2D g2d, int x, int y, int radius) {
        g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
        g2d.setColor(Color.GREEN);

        int numSymbols = 8; // Number of "जय" symbols to draw
        for (int i = 0; i < numSymbols; i++) {
            double angle = i * 2 * Math.PI / numSymbols; // Divide evenly around the circle
            int xOffset = (int) (radius * Math.cos(angle));
            int yOffset = (int) (radius * Math.sin(angle));
            g2d.drawString("जय", x + xOffset - 15, y + yOffset + 10); // Adjust the position
        }
    }

    // Method to draw outer triangles in the fourth circle
    private void drawOuterTriangles(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.ORANGE);
        int triangleHeight = 30; // Height of the outer triangles
        int numTriangles = 12; // Number of triangles to draw around the circle

        for (int i = 0; i < numTriangles; i++) {
            double angle = i * 2 * Math.PI / numTriangles;
            int xOffset = (int) (radius * Math.cos(angle));
            int yOffset = (int) (radius * Math.sin(angle));
            int[] xPoints = { x + xOffset, x + xOffset - 10, x + xOffset + 10 };
            int[] yPoints = { y + yOffset - triangleHeight, y + yOffset, y + yOffset };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }

    // Override paintComponent to perform custom painting
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Anti-aliasing for smooth drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw the big outer circle outline without fill
        int outerCircleRadius = 200;
        drawCircle(g2d, centerX, centerY, outerCircleRadius);

        // Drawing smaller inner circles
        int numberOfSmallCircles = 4;
        int smallCircleRadius = 60;

        for (int i = 0; i < numberOfSmallCircles; i++) {
            int radius = smallCircleRadius * (i + 1);
            drawCircle(g2d, centerX, centerY, radius);

            // Draw shapes in the small circles
            if (i == 0) {
                drawOmSymbol(g2d, centerX, centerY);
            } else if (i == 1) {
                drawTriangles(g2d, centerX, centerY, radius - 20);
                drawShriSymbols(g2d, centerX, centerY, radius - 40); // Draw "श्री" symbols in the second circle
            } else if (i == 2) {
                drawSquares(g2d, centerX, centerY, radius - 20);
                drawJaiSymbols(g2d, centerX, centerY, radius - 40); // Draw "जय" symbols in the third circle
            } else if (i == 3) {
                drawOuterTriangles(g2d, centerX, centerY, radius - 20); // Draw outer triangles in the fourth circle
            }
        }
    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Rangoli Design with Circles and Symbols");
        RangoliDesign rangoli = new RangoliDesign();
        frame.add(rangoli);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
