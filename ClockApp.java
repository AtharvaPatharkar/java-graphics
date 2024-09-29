import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalTime;
import javax.swing.*;


public class ClockApp extends JPanel {

    private static final int CLOCK_DIAMETER = 400;
    private static final int CLOCK_RADIUS = CLOCK_DIAMETER / 2;
    private static final int CENTER_X = CLOCK_RADIUS;
    private static final int CENTER_Y = CLOCK_RADIUS;

    public ClockApp() {
        Timer timer = new Timer(1000, e -> repaint()); // Using lambda expression
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Enable antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Get the panel dimensions
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        // Calculate the clock position to center it
        int clockX = (panelWidth - CLOCK_DIAMETER) / 2;
        int clockY = (panelHeight - CLOCK_DIAMETER) / 2;

        // Draw background
        g2.setColor(Color.PINK);
        g2.fillRect(0, 0, panelWidth, panelHeight);
        
        // Draw title
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Serif", Font.BOLD, 24));
        g2.drawString("Analog Clock", 20, 30);
        
        // Draw clock body with 3D effects
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(new RoundRectangle2D.Double(clockX, clockY, CLOCK_DIAMETER, CLOCK_DIAMETER, 50, 50));
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new RoundRectangle2D.Double(clockX, clockY, CLOCK_DIAMETER, CLOCK_DIAMETER, 50, 50));
        
        // Draw clock face
        g2.setColor(Color.WHITE);
        g2.fillOval(clockX, clockY, CLOCK_DIAMETER, CLOCK_DIAMETER);
        g2.setColor(Color.BLACK);
        g2.drawOval(clockX, clockY, CLOCK_DIAMETER, CLOCK_DIAMETER);
        
        // Draw clock ticks and numbers
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            int x1 = (int) (CENTER_X + (CLOCK_RADIUS - 20) * Math.sin(angle));
            int y1 = (int) (CENTER_Y - (CLOCK_RADIUS - 20) * Math.cos(angle));
            int x2 = (int) (CENTER_X + (CLOCK_RADIUS - 10) * Math.sin(angle));
            int y2 = (int) (CENTER_Y - (CLOCK_RADIUS - 10) * Math.cos(angle));
            g2.drawLine(clockX + x1, clockY + y1, clockX + x2, clockY + y2);
            
            // Draw numbers
            String number = Integer.toString(i + 1);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(number);
            int textHeight = fm.getAscent();
            int x = (int) (CENTER_X + (CLOCK_RADIUS - 35) * Math.sin(angle)) - textWidth / 2;
            int y = (int) (CENTER_Y - (CLOCK_RADIUS - 35) * Math.cos(angle)) + textHeight / 2;
            g2.drawString(number, clockX + x, clockY + y);
        }
        
        // Draw hands
        LocalTime now = LocalTime.now();
        double hour = now.getHour() % 12 + now.getMinute() / 60.0;
        double minute = now.getMinute();
        double second = now.getSecond();
        
        drawHand(g2, hour * 30 - 90, 0.5 * CLOCK_RADIUS, 6, clockX, clockY);
        drawHand(g2, minute * 6 - 90, 0.8 * CLOCK_RADIUS, 4, clockX, clockY);
        drawHand(g2, second * 6 - 90, 0.9 * CLOCK_RADIUS, 2, clockX, clockY);
    }
    
    private void drawHand(Graphics2D g2, double angle, double length, int width, int offsetX, int offsetY) {
        AffineTransform transform = g2.getTransform();
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(width));
        g2.rotate(Math.toRadians(angle), offsetX + CENTER_X, offsetY + CENTER_Y);
        g2.drawLine(offsetX + CENTER_X, offsetY + CENTER_Y, offsetX + CENTER_X, (int) (offsetY + CENTER_Y - length));
        g2.setTransform(transform);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        ClockApp clock = new ClockApp();
        frame.add(clock);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
