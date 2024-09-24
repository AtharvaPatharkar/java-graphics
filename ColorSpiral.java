import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorSpiral extends JPanel {

    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private final int userAngle;

    public ColorSpiral(int angle) {
        this.userAngle = angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int r = 255, gr = 0, b = 0;
        int x = getWidth() / 2, y = getHeight() / 2;
        int angle = 1;
        int lineWidth = 1;



        for (int i = 0; i < 255 * 18; i++) {
            g2d.setColor(new Color(r, gr, b));
            g2d.setStroke(new java.awt.BasicStroke(lineWidth));
            int length = 5 + (i / 10);
            
            g2d.drawLine(x, y, 
                         x + (int) (length * Math.cos(Math.toRadians(angle))),
                         y + (int) (length * Math.sin(Math.toRadians(angle))));
            
            x += (int) (length * Math.cos(Math.toRadians(angle)));
            y += (int) (length * Math.sin(Math.toRadians(angle)));
            
            angle = (angle + userAngle) % 360;

            if (i % 255 == 0 && lineWidth < 10) {
                lineWidth += 1;
            }

            // Color transition logic
            if (i < 255) {
                gr += 1;
            } else if (i < 255 * 2) {
                r -= 1;
            } else if (i < 255 * 3) {
                b += 1;
            } else if (i < 255 * 4) {
                gr -= 1;
            } else if (i < 255 * 5) {
                r += 1;
            } else if (i < 255 * 6) {
                b -= 1;
            } else if (i < 255 * 7) {
                r += 1;
            } else if (i < 255 * 8) {
                gr += 1;
            } else if (i < 255 * 9) {
                b += 1;
            } else if (i < 255 * 10) {
                r -= 1;
            } else if (i < 255 * 11) {
                gr -= 1;
            } else if (i < 255 * 12) {
                b -= 1;
            } else if (i < 255 * 13) {
                r += 1;
            } else if (i < 255 * 14) {
                gr += 1;
            } else if (i < 255 * 15) {
                b += 1;
            } else if (i < 255 * 16) {
                r -= 1;
            } else if (i < 255 * 17) {
                gr -= 1;
            } else if (i < 255 * 18) {
                b -= 1;
            }

            r = clamp(r, 0, 255);
            gr = clamp(gr, 0, 255);
            b = clamp(b, 0, 255);
        }
    }

    private static void createAndShowGUI(int angle) {
        JFrame frame = new JFrame("Color Spiral");
        ColorSpiral panel = new ColorSpiral(angle);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // Add a listener to handle the window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                promptUserForAngle();
            }
        });
    }

    private static void promptUserForAngle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to enter a new angle? (yes/no): ");
        String response = scanner.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the angle for the spiral (30, 45, 60, 90, 120, 150, Any Angle): ");
            int angle = scanner.nextInt();
            createAndShowGUI(angle);
        } else {
            System.out.println("Program ended.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        promptUserForAngle();
    }
}
