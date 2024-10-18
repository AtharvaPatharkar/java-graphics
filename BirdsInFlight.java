import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class BirdsInFlight extends JPanel {
    private int[] birdX = {50, -100, -200};  // Initial horizontal positions of the birds
    public  int[] birdY = {150, 250, 200};   // Initial vertical positions of the birds
    private int[] wingOffsets = {0, 10, 5};  // Offset for wing flapping for each bird
    private int cloudX = 500;  // Initial position of the clouds
    public Timer timer;

    public BirdsInFlight() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Move each bird
                for (int i = 0; i < birdX.length; i++) {
                    birdX[i] += 3;  // Move birds to the right
                    wingOffsets[i] = (wingOffsets[i] + 1) % 20;  // Animate wing flapping
                    if (birdX[i] > getWidth()) {
                        birdX[i] = -100;  // Reset bird position once it leaves the screen
                    }
                }
                cloudX -= 1;  // Move clouds to the left
                if (cloudX < -150) {
                    cloudX = getWidth();  // Reset cloud position
                }
                repaint();  // Redraw the panel
            }
        }, 0, 50);  // Update every 50 ms
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the dimensions of the panel
        int width = getWidth();
        int height = getHeight();

        // Draw background (sky)
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, width, height);

        // Draw sun
        g.setColor(Color.ORANGE);
        g.fillOval(width / 2 - 50, 50, 80, 80);

        // Draw clouds
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, 100, 150, 60);
        g.fillOval(cloudX + 200, 80, 130, 50);

        // Draw ground (horizon)
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, height - 100, width, 100);

        // Draw multiple birds in flight
        for (int i = 0; i < birdX.length; i++) {
            drawBird(g, birdX[i], birdY[i], wingOffsets[i]);
        }

        // Draw text "Code with AP"
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", width / 2 - 80, height - 50);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, width - 10, height - 10);
    }

    private void drawBird(Graphics g, int x, int y, int wingOffset) {
        // Draw bird body
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 60, 30);  // Bird body

        // Draw wings (animated)
        g.setColor(Color.GRAY);
        if (wingOffset < 10) {
            g.fillPolygon(new int[]{x + 15, x + 45, x + 30},
                          new int[]{y, y, y - 30}, 3);  // Wing up
        } else {
            g.fillPolygon(new int[]{x + 15, x + 45, x + 30},
                          new int[]{y + 30, y + 30, y + 10}, 3);  // Wing down
        }

        // Draw bird beak
        g.setColor(Color.ORANGE);
        g.fillPolygon(new int[]{x + 60, x + 70, x + 60},
                      new int[]{y + 10, y + 15, y + 20}, 3);

        // Draw bird eye
        g.setColor(Color.WHITE);
        g.fillOval(x + 45, y + 5, 10, 10);
        g.setColor(Color.BLACK);
        g.fillOval(x + 48, y + 8, 5, 5);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Multiple Birds in Flight Animation");
        BirdsInFlight animation = new BirdsInFlight();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(animation);
        frame.setVisible(true);
    }
}
