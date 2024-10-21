import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CastleScene extends JPanel implements ActionListener {

    private int flagOffset = 0; // To animate flag waving
    private int cloudX1 = 600;
    private int cloudY1 = 100;
    private int cloudX2 = 800;
    private int cloudY2 = 150;
    private Timer timer;

    public CastleScene() {
        setPreferredSize(new Dimension(1000, 600)); // Screen size
        setBackground(new Color(135, 206, 235)); // Sky blue background
        timer = new Timer(50, this); // Timer for animation
        timer.start(); // Start the animation
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, getWidth() - 40, getHeight() - 40);

        // Draw title
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("Castle Scene", getWidth() / 2 - 100, 50);

        // Draw moving clouds
        g.setColor(Color.WHITE);
        drawCloud(g, cloudX1, cloudY1);
        drawCloud(g, cloudX2, cloudY2);
        
        // Draw the ground (green grass)
        g.setColor(new Color(34, 139, 34)); // Green ground
        g.fillRect(0, getHeight() - 100, getWidth(), 100);

        // Draw the castle base
        g.setColor(new Color(169, 169, 169)); // Castle gray color
        g.fillRect(getWidth() / 2 - 200, getHeight() / 2, 400, 200); // Castle main base

        // Draw the castle towers (3 towers)
        g.fillRect(getWidth() / 2 - 250, getHeight() / 2 - 100, 100, 300); // Left tower
        g.fillRect(getWidth() / 2 + 150, getHeight() / 2 - 100, 100, 300); // Right tower
        g.fillRect(getWidth() / 2 - 50, getHeight() / 2 - 150, 100, 350); // Center tower

        // Draw the tower tops (triangular)
        g.setColor(new Color(139, 69, 19)); // Brown tops for towers
        g.fillPolygon(new int[]{getWidth() / 2 - 300, getWidth() / 2 - 200, getWidth() / 2 - 250}, new int[]{getHeight() / 2 - 100, getHeight() / 2 - 100, getHeight() / 2 - 150}, 3); // Left tower top
        g.fillPolygon(new int[]{getWidth() / 2 + 200, getWidth() / 2 + 300, getWidth() / 2 + 250}, new int[]{getHeight() / 2 - 100, getHeight() / 2 - 100, getHeight() / 2 - 150}, 3); // Right tower top
        g.fillPolygon(new int[]{getWidth() / 2 - 100, getWidth() / 2 + 100, getWidth() / 2}, new int[]{getHeight() / 2 - 150, getHeight() / 2 - 150, getHeight() / 2 - 200}, 3); // Center tower top

        // Draw the castle door
        g.setColor(new Color(139, 69, 19)); // Brown door
        g.fillRect(getWidth() / 2 - 50, getHeight() / 2 + 100, 100, 100); // Main door
        g.setColor(Color.BLACK);
        g.fillOval(getWidth() / 2 - 10, getHeight() / 2 + 150, 20, 20); // Door knob

        // Draw the castle windows
        g.setColor(Color.BLACK);
        g.fillRect(getWidth() / 2 - 40, getHeight() / 2 + 50, 30, 40); // Left window
        g.fillRect(getWidth() / 2 + 10, getHeight() / 2 + 50, 30, 40); // Right window
        g.fillRect(getWidth() / 2 - 90, getHeight() / 2 - 50, 30, 40); // Left tower window
        g.fillRect(getWidth() / 2 + 60, getHeight() / 2 - 50, 30, 40); // Right tower window
        g.fillRect(getWidth() / 2 - 15, getHeight() / 2 - 100, 30, 40); // Center tower window

        // Draw the castle flag (on the center tower, animated)
        g.setColor(Color.RED);
        g.fillRect(getWidth() / 2 - 5, getHeight() / 2 - 250, 5, 60); // Flag pole
        g.fillRect(getWidth() / 2, getHeight() / 2 - 245 + flagOffset, 50, 30); // Waving flag


    
        // Draw the text "Code with AP"
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 50);

    }

    // Helper method to draw clouds
    private void drawCloud(Graphics g, int x, int y) {
        g.fillOval(x, y, 80, 50);
        g.fillOval(x + 30, y - 20, 80, 50);
        g.fillOval(x + 60, y + 10, 80, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Animate the flag (waving motion)
        flagOffset = (flagOffset == 0) ? 5 : 0;

        // Move the clouds to the left
        cloudX1 -= 2;
        cloudX2 -= 3;
        if (cloudX1 < -150) {
            cloudX1 = getWidth();
        }
        if (cloudX2 < -150) {
            cloudX2 = getWidth();
        }

        // Redraw the panel to update animations
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Castle Scene");
        CastleScene panel = new CastleScene();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getCloudY1() {
        return cloudY1;
    }

    public void setCloudY1(int cloudY1) {
        this.cloudY1 = cloudY1;
    }

    public int getCloudY2() {
        return cloudY2;
    }

    public void setCloudY2(int cloudY2) {
        this.cloudY2 = cloudY2;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
