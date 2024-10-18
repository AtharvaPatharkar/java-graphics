import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class BoatSailingAnimation extends JPanel implements ActionListener {
    private int boatX = 200;  // Initial position of the boat
    private int waveOffset = 0;  // Offset for the wave animation
    private int cloudX = 500;  // Initial position of the clouds
    public  Timer timer;
    private int sunY = 50; // Sun's vertical position

    public BoatSailingAnimation() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boatX += 2;  // Move boat to the right
                waveOffset = (waveOffset + 1) % 40;  // Create a wave animation effect
                cloudX -= 1;  // Move clouds to the left
                sunY = (int) (50 + 20 * Math.sin(System.currentTimeMillis() * 0.001));  // Sun moving up and down
                if (boatX > getWidth()) {
                    boatX = -150;  // Reset boat position once it leaves the screen
                }
                if (cloudX < -100) {
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
        g.fillOval(width / 2 - 50, sunY, 80, 80);

        // Draw clouds
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, 50, 100, 50);
        g.fillOval(cloudX + 60, 70, 120, 60);

        // Draw water
        g.setColor(new Color(0, 119, 190));
        g.fillRect(0, height / 2, width, height / 2);

        // Draw waves (animated)
        g.setColor(new Color(0, 150, 220));
        for (int i = 0; i < width; i += 40) {
            g.fillArc(i - waveOffset, height / 2 - 10, 40, 20, 0, 180);
        }

        // Draw boat
        int boatY = height / 2 - 30;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(boatX, boatY, 100, 20);  // Boat base
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{boatX + 30, boatX + 50, boatX + 30}, new int[]{boatY, boatY - 40, boatY}, 3);  // Sail

        // Draw mast
        g.setColor(Color.BLACK);
        g.fillRect(boatX + 30, boatY - 40, 5, 40);  // Mast

        // Draw text "Code with AP"
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", width / 2 - 80, height - 50);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, width - 10, height - 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Repaint action not required here since TimerTask handles the animation.
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Boat Sailing Animation");
        BoatSailingAnimation animation = new BoatSailingAnimation();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(animation);
        frame.setVisible(true);
    }
}
