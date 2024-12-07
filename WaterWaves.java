import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class WaterWaves extends JPanel implements ActionListener {
    private int screenWidth = 1800;
    private int screenHeight = 800;
    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Wave> waves;

    public WaterWaves() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.CYAN); // Sky blue background

        timer = new Timer(30, this); // Timer for animation
        timer.start();

        waves = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waves.add(new Wave(i * 360, screenHeight - 100)); // Create waves
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTitle(g);
        drawWaves(g);
        drawSun(g);
        drawClouds(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Water Waves", screenWidth / 2 - 180, 50); // Title at top-center
    }

    // Draw waves on the scene
    private void drawWaves(Graphics g) {
        for (Wave wave : waves) {
            wave.draw(g);
        }
    }

    // Draw sun in the sky
    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(screenWidth - 150, 50, 100, 100); // Sun in the top-right corner
    }

    // Draw clouds in the sky
    private void drawClouds(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(100, 100, 150, 80); // Cloud 1
        g.fillOval(130, 80, 200, 100); // Cloud 2
        g.fillOval(350, 120, 150, 80); // Cloud 3
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", screenWidth / 2 - 60, screenHeight - 30); // Footer text at bottom-center
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Wave wave : waves) {
            wave.move();
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Water Waves");
        WaterWaves scene = new WaterWaves();
        frame.add(scene);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}

// Wave class representing the animated water waves
class Wave {
    private int x, y;
    private int amplitude;
    private int frequency;
    private int phase;

    public Wave(int x, int y) {
        this.x = x;
        this.y = y;
        this.amplitude = 20 + (int) (Math.random() * 20); // Random amplitude
        this.frequency = 15; // Frequency of the wave
        this.phase = 0; // Phase shift
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 255, 150)); // Semi-transparent blue color for waves
        for (int i = 0; i < 1800; i++) {
            int waveHeight = (int) (amplitude * Math.sin((i + phase) * Math.PI / frequency));
            g.drawLine(i, y + waveHeight, i, y + waveHeight + 10); // Draw wave
        }
    }

    public void move() {
        phase += 2; // Increase phase to animate wave
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
