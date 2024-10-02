import java.awt.*;
import javax.swing.*;

public class MobilePhoneDrawing extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable 2D Graphics
        Graphics2D g2d = (Graphics2D) g;

        // Set background color (different background)
        setBackground(new Color(173, 216, 230)); // Light Blue background

        // Get the panel width and height to center the phone
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Mobile phone dimensions
        int phoneWidth = 200;
        int phoneHeight = 400;

        // Calculate the position to center the mobile phone
        int phoneX = (panelWidth - phoneWidth) / 2;
        int phoneY = (panelHeight - phoneHeight) / 2;

        // Draw the outline of the mobile phone with a new color for the body
        g2d.setColor(new Color(169, 169, 169));  // Light Gray color for phone body
        g2d.fillRoundRect(phoneX, phoneY, phoneWidth, phoneHeight, 30, 30);  // Filled rounded rectangle for the phone body

        // Add a border around the mobile phone
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));  // Border width
        g2d.drawRoundRect(phoneX, phoneY, phoneWidth, phoneHeight, 30, 30);  // Phone outline

        // Fill the screen with a color (dark gray screen)
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(phoneX + 10, phoneY + 20, phoneWidth - 20, phoneHeight - 150, 20, 20);  // Mobile screen

        // Draw the speaker slot (small rounded rectangle at the top)
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(phoneX + 80, phoneY + 30, 40, 10, 5, 5);  // Speaker

        // Draw the home button (circle at the bottom)
        g2d.setColor(Color.BLACK);
        g2d.fillOval(phoneX + 80, phoneY + 360, 40, 40);

        // Side buttons
        g2d.setColor(Color.GRAY);
        g2d.fillRect(phoneX - 5, phoneY + 100, 10, 50);  // Left side button
        g2d.fillRect(phoneX + phoneWidth - 5, phoneY + 100, 10, 50); // Right side button

        // Draw two rectangular buttons (Apps) on the screen with different colors
        g2d.setColor(Color.CYAN);  // First button color
        g2d.fillRoundRect(phoneX + 50, phoneY + 100, 60, 40, 10, 10);  // First button

        g2d.setColor(Color.MAGENTA);  // Second button color
        g2d.fillRoundRect(phoneX + 120, phoneY + 100, 60, 40, 10, 10);  // Second button

        // Text on buttons
        g2d.setColor(Color.WHITE);  // Text color
        g2d.drawString("App1", phoneX + 65, phoneY + 125);  // App1 text
        g2d.drawString("App2", phoneX + 135, phoneY + 125);  // App2 text
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mobile Phone Drawing");
        MobilePhoneDrawing drawing = new MobilePhoneDrawing();
        frame.add(drawing);
        frame.setSize(400, 600);  // Window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
