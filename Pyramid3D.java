import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.Random;
import javax.swing.*;

public class Pyramid3D extends JPanel implements ActionListener {

    private Timer timer;
    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;
    private Random random = new Random();

    public Pyramid3D() {
        timer = new Timer(30, this);  // Timer for smooth animation
        timer.start();  // Start animation
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Pyramid Rendering");
        Pyramid3D display = new Pyramid3D();
        frame.add(display);
        frame.setSize(800, 800);  // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Background Color
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), new Color(25, 25, 112));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Title text with animation
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        String title = "3D Pyramid Animation";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, getWidth() / 2 - titleWidth / 2, 40);
        
        // Draw 3D pyramid in the center
        drawPyramid(g2d, getWidth() / 2, getHeight() / 2, 200);

        // Footer text with simple animation
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        String footerText = "Code with AP";
        int textWidth = g2d.getFontMetrics().stringWidth(footerText);
        g2d.drawString(footerText, getWidth() / 2 - textWidth / 2, getHeight() - 40);
    }

    private void drawPyramid(Graphics2D g2d, int cx, int cy, int size) {
        // Define pyramid vertices in 3D space (x, y, z)
        double[][] vertices = {
            {0, size, 0},   // Top point
            {-size, -size, -size}, // Bottom left front
            {size, -size, -size},  // Bottom right front
            {size, -size, size},   // Bottom right back
            {-size, -size, size}   // Bottom left back
        };

        // 3D rotation matrices
        double cosX = Math.cos(angleX), sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY), sinY = Math.sin(angleY);
        double cosZ = Math.cos(angleZ), sinZ = Math.sin(angleZ);

        // Project the 3D points onto 2D plane
        int[][] projected = new int[vertices.length][2];
        for (int i = 0; i < vertices.length; i++) {
            double[] v = vertices[i];

            // Rotate around X axis
            double y = cosX * v[1] - sinX * v[2];
            double z = sinX * v[1] + cosX * v[2];
            double x = v[0];

            // Rotate around Y axis
            double tempX = cosY * x + sinY * z;
            z = -sinY * x + cosY * z;
            x = tempX;

            // Rotate around Z axis
            double tempX2 = cosZ * x - sinZ * y;
            y = sinZ * x + cosZ * y;
            x = tempX2;

            // 3D to 2D projection (simple perspective)
            double scale = 500 / (500 + z);  // Perspective projection formula
            projected[i][0] = (int) (cx + x * scale);
            projected[i][1] = (int) (cy - y * scale);
        }

        // Draw the pyramid with each face a different color
        Color[] faceColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};

        // Draw base (connecting bottom 4 points with a single color)
        g2d.setColor(faceColors[0]);
        Path2D base = new Path2D.Double();
        base.moveTo(projected[1][0], projected[1][1]);
        base.lineTo(projected[2][0], projected[2][1]);
        base.lineTo(projected[3][0], projected[3][1]);
        base.lineTo(projected[4][0], projected[4][1]);
        base.closePath();
        g2d.fill(base);  // Fill with color

        // Draw sides (connect top point to base with different colors)
        for (int i = 1; i <= 4; i++) {
            g2d.setColor(faceColors[i]);
            Path2D side = new Path2D.Double();
            side.moveTo(projected[0][0], projected[0][1]);  // Top point
            side.lineTo(projected[i][0], projected[i][1]);  // Base point
            side.lineTo(projected[(i % 4) + 1][0], projected[(i % 4) + 1][1]);  // Next base point
            side.closePath();
            g2d.fill(side);  // Fill side face with color
        }
        
        // Extra elements rotating around pyramid
        drawRotatingCircles(g2d, cx, cy, size, projected[0][0], projected[0][1]);
    }

    private void drawRotatingCircles(Graphics2D g2d, @SuppressWarnings("unused") int cx, @SuppressWarnings("unused") int cy, @SuppressWarnings("unused") int size, int topX, int topY) {
        g2d.setColor(Color.MAGENTA);
        
        // Draw circles rotating around the pyramid's top point
        int radius = 120;
        for (int i = 0; i < 360; i += 60) {
            int x = (int) (topX + radius * Math.cos(Math.toRadians(i + angleY * 50)));
            int y = (int) (topY + radius * Math.sin(Math.toRadians(i + angleY * 50)));
            g2d.fillOval(x - 10, y - 10, 20, 20);  // Draw rotating circle
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Animate rotation angles
        angleX += 0.01;
        angleY += 0.02;
        angleZ += 0.01;

        // Redraw the scene
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
