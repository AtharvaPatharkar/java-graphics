import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoatAndOceanScene extends JPanel implements ActionListener {

    private int boatX = 50;
    private int boatY = 300;
    private int waveOffset = 0;
    private int sunX = 350;
    private int sunY = 50;
    private int sunDirection = 1; // 1 means moving right, -1 means moving left
    private Timer timer;

    public BoatAndOceanScene() {
        setPreferredSize(new Dimension(1000, 600)); // Scene size
        setBackground(new Color(135, 206, 250)); // Sky blue
        timer = new Timer(50, this); // Create a timer to handle animation
        timer.start(); // Start the animation timer
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
        g.drawString("Boat and Ocean Scene", getWidth() / 2 - 180, 50);

        // Draw text "Code with AP"
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 50);

        // Draw the ocean (bottom half of the screen)
        g.setColor(new Color(0, 105, 148)); // Ocean color
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw animated waves (move them left and right)
        g.setColor(new Color(173, 216, 230)); // Light blue for waves
        for (int i = 0; i < getWidth(); i += 100) {
            g.fillArc(i - waveOffset, getHeight() / 2 - 20, 100, 40, 0, 180);
        }

        // Draw the sun with animation
        g.setColor(Color.ORANGE);
        g.fillOval(sunX, sunY, 100, 100);

        // Draw the boat (body)
        g.setColor(new Color(139, 69, 19)); // Brown boat
        g.fillRect(boatX, boatY, 150, 40); // Boat body
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{boatX, boatX + 150, boatX + 75}, new int[]{boatY, boatY, boatY - 40}, 3); // Sail triangle

        // Draw the boat mast
        g.setColor(Color.DARK_GRAY);
        g.fillRect(boatX + 70, boatY - 60, 10, 60); // Mast

        // Draw boat flag
        g.setColor(Color.YELLOW);
        g.fillRect(boatX + 80, boatY - 50, 30, 20); // Flag

        // Add extra elements like a seagull
        g.setColor(Color.WHITE);
        drawSeagull(g, boatX + 300, boatY - 150);
        drawSeagull(g, boatX + 350, boatY - 100);

        
        // Draw text "Code with AP"
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 50);

    }

    // Helper method to draw a seagull
    private void drawSeagull(Graphics g, int x, int y) {
        g.drawArc(x, y, 40, 20, 0, 180);
        g.drawArc(x + 20, y, 40, 20, 0, 180);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the boat to the right
        boatX += 5;
        if (boatX > getWidth()) {
            boatX = -150; // Reset boat position when it goes off-screen
        }

        // Animate the waves (simple left-right movement)
        waveOffset += 2;
        if (waveOffset > 100) {
            waveOffset = 0;
        }

        // Move the sun across the sky
        sunX += sunDirection * 2;
        if (sunX > getWidth() - 100 || sunX < 0) {
            sunDirection *= -1; // Change direction when sun reaches the edges
        }

        // Redraw the panel to update the animations
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Boat and Ocean Scene");
        BoatAndOceanScene panel = new BoatAndOceanScene();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getBoatY() {
        return boatY;
    }

    public void setBoatY(int boatY) {
        this.boatY = boatY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
