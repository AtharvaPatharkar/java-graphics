import java.awt.*;
import javax.swing.*;

public class IndianFlag extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawIndianFlag(g);
    }

    private void drawIndianFlag(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        // Define flag dimensions
        int stripeHeight = height / 3;
        int stripeWidth = width;

        // Draw saffron rectangle
        g.setColor(new Color(255, 153, 51)); // Saffron color
        g.fillRect(0, 0, stripeWidth, stripeHeight);

        // Draw white rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, stripeHeight, stripeWidth, stripeHeight);

        // Draw green rectangle
        g.setColor(new Color(19, 136, 8)); // Green color
        g.fillRect(0, 2 * stripeHeight, stripeWidth, stripeHeight);

        // Draw the Ashoka Chakra
        drawAshokaChakra(g, stripeWidth / 2, stripeHeight + stripeHeight / 2, stripeHeight / 2);
    }

    private void drawAshokaChakra(Graphics g, int x, int y, int radius) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 0, 255)); // Blue color
        g2d.setStroke(new BasicStroke(3));

        // Draw the outer circle
        g2d.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

        // Draw the 24 spokes
        for (int i = 0; i < 24; i++) {
            double angle = 2 * Math.PI * i / 24;
            int x1 = (int) (x + radius * Math.cos(angle));
            int y1 = (int) (y - radius * Math.sin(angle));
            g2d.drawLine(x, y, x1, y1);
        }

        // Draw the inner circle
        g2d.drawOval(x - radius / 4, y - radius / 4, radius / 2, radius / 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Indian Flag");
        IndianFlag panel = new IndianFlag();
        frame.add(panel);
        frame.setSize(400, 300); // Size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
