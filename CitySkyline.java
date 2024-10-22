import java.awt.*;
import javax.swing.*;

public class CitySkyline extends JPanel {

    // Override paintComponent to draw the city skyline
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast Graphics to Graphics2D to enable 2D drawing
        Graphics2D g2d = (Graphics2D) g;

        // Set the night sky background color
        this.setBackground(new Color(25, 25, 112));  // Midnight blue for night sky

        // Enable anti-aliasing for smoother drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the moon
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(getWidth() - 150, 50, 80, 80);  // Moon in the top-right corner

        // Draw the city skyline
        drawBuildings(g2d);

        // Draw the title "City Skyline" in the center of the top
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        g2d.drawString("City Skyline", (getWidth() - g2d.getFontMetrics().stringWidth("City Skyline")) / 2, 40);

        // Add text below the skyline
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        g2d.drawString("Designed by AP", (getWidth() - g2d.getFontMetrics().stringWidth("Designed by AP")) / 2, getHeight() - 20);
    }

    // Helper method to draw the buildings
    private void drawBuildings(Graphics2D g2d) {
        int baseY = getHeight() - 150;  // The baseline for the buildings

        // Define the widths and heights of each building
        int[][] buildings = {
            {100, 300}, {80, 250}, {120, 320}, {60, 220}, {90, 270}, {130, 310}
        };

        // Start drawing buildings from the center, then move outwards
        int startX = (getWidth() - totalBuildingsWidth(buildings)) / 2;

        // Set colors for the buildings
        Color[] buildingColors = {new Color(70, 130, 180), new Color(169, 169, 169), new Color(47, 79, 79), new Color(105, 105, 105)};
        
        for (int i = 0; i < buildings.length; i++) {
            int buildingWidth = buildings[i][0];
            int buildingHeight = buildings[i][1];
            g2d.setColor(buildingColors[i % buildingColors.length]);

            // Draw the building
            g2d.fillRect(startX, baseY - buildingHeight, buildingWidth, buildingHeight);

            // Add windows to the building
            drawWindows(g2d, startX, baseY - buildingHeight, buildingWidth, buildingHeight);

            // Move to the next building's starting X
            startX += buildingWidth + 20;
        }
    }

    // Helper method to draw windows on a building
    private void drawWindows(Graphics2D g2d, int startX, int startY, int buildingWidth, int buildingHeight) {
        g2d.setColor(Color.YELLOW);  // Color of the windows

        // Set window size
        int windowWidth = 10;
        int windowHeight = 15;

        // Draw windows in a grid
        for (int y = startY + 20; y < startY + buildingHeight - 20; y += 30) {
            for (int x = startX + 10; x < startX + buildingWidth - 10; x += 25) {
                g2d.fillRect(x, y, windowWidth, windowHeight);
            }
        }
    }

    // Helper method to calculate the total width of all buildings combined
    private int totalBuildingsWidth(int[][] buildings) {
        int totalWidth = 0;
        for (int[] building : buildings) {
            totalWidth += building[0];
        }
        // Add spacing between buildings
        totalWidth += (buildings.length - 1) * 20;
        return totalWidth;
    }

    // Main function to set up the JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("City Skyline Drawing");
        CitySkyline cityPanel = new CitySkyline();

        frame.add(cityPanel);
        frame.setSize(800, 600);  // Set the window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
