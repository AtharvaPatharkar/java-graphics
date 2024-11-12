import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HauntedHouseDesign extends JPanel implements ActionListener {
    private Timer timer;
    private int ghostX = 0;    // X position of the ghost
    private int ghostY = 300;  // Y position of the ghost
    private int ghostDirection = 1; // 1 for moving right, -1 for moving left
    private int batX = 400;     // X position of the bat
    private int batY = 50;      // Y position of the bat
    private int batDirection = 1; // 1 for moving down, -1 for moving up
    private int pumpkinY = 350; // Y position of the pumpkin

    public HauntedHouseDesign() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        timer = new Timer(50, this); // Timer for animation
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHome(g);
        drawGhost(g);
        drawBat(g);
        drawPumpkin(g);
        drawTitle(g);
        drawText(g);
    }

    private void drawHome(Graphics g) {
        // Draw home (haunted house)
        g.setColor(Color.DARK_GRAY);
        g.fillRect(300, 250, 200, 200); // House body
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(350, 350, 100, 100); // Door
        g.setColor(Color.YELLOW);
        g.fillRect(320, 280, 40, 40); // Left window
        g.fillRect(440, 280, 40, 40); // Right window
        g.setColor(Color.RED);
        int[] xPoints = {300, 400, 500};
        int[] yPoints = {250, 150, 250};
        g.fillPolygon(xPoints, yPoints, 3); // Roof
    }

    private void drawGhost(Graphics g) {
        // Draw ghost
        g.setColor(Color.WHITE);
        g.fillOval(ghostX, ghostY, 50, 50); // Ghost body
        g.setColor(Color.BLACK);
        g.fillOval(ghostX + 10, ghostY + 10, 10, 10); // Left eye
        g.fillOval(ghostX + 30, ghostY + 10, 10, 10); // Right eye
        g.drawArc(ghostX + 10, ghostY + 25, 30, 15, 0, -180); // Mouth
    }

    private void drawBat(Graphics g) {
        // Draw bat
        g.setColor(Color.GRAY);
        int[] xPoints = {batX, batX - 10, batX - 15, batX - 10, batX - 25, batX - 20, batX - 15, batX - 20, batX - 10, batX};
        int[] yPoints = {batY, batY - 10, batY - 15, batY - 20, batY - 25, batY - 20, batY - 15, batY - 10, batY, batY + 10};
        g.fillPolygon(xPoints, yPoints, 10); // Bat wings
        g.setColor(Color.BLACK);
        g.fillOval(batX - 5, batY - 5, 10, 10); // Bat body
    }

    private void drawPumpkin(Graphics g) {
        // Draw pumpkin
        g.setColor(Color.ORANGE);
        g.fillOval(350, pumpkinY, 50, 50); // Pumpkin body
        g.setColor(Color.GREEN);
        g.fillRect(370, pumpkinY - 10, 10, 10); // Pumpkin stem
        g.setColor(Color.BLACK);
        g.fillOval(360, pumpkinY + 10, 10, 10); // Left eye
        g.fillOval(380, pumpkinY + 10, 10, 10); // Right eye
        g.drawArc(360, pumpkinY + 20, 30, 20, 0, -180); // Mouth
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.MAGENTA); // Title color
        g.setFont(new Font("Arial", Font.BOLD, 36));
        String title = "Haunted House";
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - metrics.stringWidth(title)) / 2;
        g.drawString(title, x, 50);
    }

    private void drawText(Graphics g) {
        g.setColor(Color.CYAN); // Text color
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        String text = "Code with AP";
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        g.drawString(text, x, getHeight() - 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move ghost
        ghostX += ghostDirection * 5;
        if (ghostX >= getWidth() - 50 || ghostX <= 0) {
            ghostDirection *= -1; // Change direction
        }

        // Move bat
        batY += batDirection * 2; // Move bat up and down
        if (batY >= 100 || batY <= 20) {
            batDirection *= -1; // Change bat direction
        }

        // Animate pumpkin bobbing up and down
        pumpkinY += (Math.sin(System.currentTimeMillis() / 500.0) * 2); // Pumpkins bob up and down

        repaint(); // Refresh the panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Haunted House Design");
        HauntedHouseDesign hauntedHouse = new HauntedHouseDesign();
        frame.add(hauntedHouse);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public int getBatX() {
        return batX;
    }

    public void setBatX(int batX) {
        this.batX = batX;
    }

    public int getGhostY() {
        return ghostY;
    }

    public void setGhostY(int ghostY) {
        this.ghostY = ghostY;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
