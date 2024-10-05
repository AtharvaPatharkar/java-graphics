import java.awt.*;
import javax.swing.*;

public class TVGraphics extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background gradient
        GradientPaint gradient = new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), Color.DARK_GRAY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        g2d.drawString("3D TV Design", 20, 50);  // Title on the left side

        // TV dimensions
        int tvWidth = 280;
        int tvHeight = 150;
        int tvX = (getWidth() - tvWidth) / 2;  // Center horizontally
        int tvY = 50;  // Position TV at the top

        // TV stand dimensions
        int standWidth = 300;
        int standHeight = 30;
        int standX = tvX - (standWidth - tvWidth) / 2;
        int standY = tvY + tvHeight;

        // TV frame with 3D effect
        g2d.setColor(Color.BLACK);
        g2d.fillRect(tvX, tvY, tvWidth, tvHeight);  // TV frame

        g2d.setColor(Color.DARK_GRAY); // Shadow for 3D effect
        g2d.fillRect(tvX + 10, tvY + 10, tvWidth, tvHeight);
        
        g2d.setColor(Color.BLACK); 
        g2d.fillRect(tvX, tvY, tvWidth, tvHeight);  // Redraw to correct overlapping
        
        // TV screen
        g2d.setColor(Color.BLUE);
        g2d.fillRect(tvX + 10, tvY + 10, tvWidth - 20, tvHeight - 20);  // Screen

        // TV stand
        g2d.setColor(new Color(102, 51, 0));  // Brown color
        g2d.fillRect(standX, standY, standWidth, standHeight);  // Stand base
        g2d.fillRect(tvX + tvWidth / 2 - 30, tvY + tvHeight - 60, 60, 60);  // Stand neck

        // Sofa dimensions
        int sofaWidth = 400;
        int sofaHeight = 50;
        int sofaX = (getWidth() - sofaWidth) / 2;  // Center horizontally
        int sofaY = getHeight() / 2 - sofaHeight / 2;  // Center vertically

        // Sofa
        g2d.setColor(new Color(139, 69, 19)); // Sofa color
        g2d.fillRect(sofaX, sofaY, sofaWidth, sofaHeight);  // Sofa base
        g2d.fillRect(sofaX - 50, sofaY - 10, 50, 40);   // Left armrest
        g2d.fillRect(sofaX + sofaWidth, sofaY - 10, 50, 40);  // Right armrest

        // Drink glass dimensions
        int glassWidth = 30;
        int glassHeight = 60;
        int glassX = sofaX + 30;
        int glassY = sofaY - glassHeight - 10;

        // Drink glass
        g2d.setColor(Color.RED);
        g2d.fillRect(glassX, glassY, glassWidth, glassHeight);  // Glass base
        g2d.setColor(Color.WHITE);
        g2d.fillRect(glassX + 5, glassY + 5, glassWidth - 10, glassHeight - 10);  // Glass content

        // Draw door on the right side
        int doorWidth = 100;
        int doorHeight = 200;
        int doorX = getWidth() - doorWidth - 50;
        int doorY = getHeight() / 2 - doorHeight / 2;

        // Door
        g2d.setColor(new Color(139, 69, 19)); // Door color
        g2d.fillRect(doorX, doorY, doorWidth, doorHeight);  // Door base
        g2d.setColor(Color.DARK_GRAY); // Door handle
        g2d.fillRect(doorX + doorWidth / 2 - 10, doorY + doorHeight / 2 - 5, 20, 10);  // Handle

        // Wires
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(tvX + tvWidth / 2, tvY + tvHeight, tvX + tvWidth / 2, standY);  // Wire from TV to stand
        g2d.drawLine(tvX + tvWidth / 2, standY + standHeight, tvX + tvWidth / 2, standY + standHeight + 30);  // Wire below stand
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TV Graphics Example");
        TVGraphics panel = new TVGraphics();
        frame.add(panel);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
