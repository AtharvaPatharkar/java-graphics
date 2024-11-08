import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class LaptopIcon extends JPanel {
    public Timer timer;
    private boolean cursorVisible = true;  // For blinking cursor animation

    public LaptopIcon() {
        // Create a timer that will trigger a repaint every 500 milliseconds
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cursorVisible = !cursorVisible;  // Toggle cursor visibility
                repaint();  // Redraw the panel
            }
        }, 0, 500);  // Update every 500 ms (0.5 seconds)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the dimensions of the panel
        int width = getWidth();
        int height = getHeight();

        // Draw background (light gray)
        g.setColor(new Color(240, 240, 240));
        g.fillRect(0, 0, width, height);

        // Draw the laptop base
        int laptopWidth = 400;
        int laptopHeight = 300;
        int laptopX = (width - laptopWidth) / 2;
        int laptopY = (height - laptopHeight) / 2;

        g.setColor(new Color(100, 100, 100));  // Dark gray for the laptop
        g.fillRect(laptopX, laptopY, laptopWidth, laptopHeight);  // Laptop body

        // Draw the screen
        g.setColor(Color.BLACK);
        g.fillRect(laptopX + 10, laptopY + 10, laptopWidth - 20, laptopHeight - 20);  // Screen
        
        // Draw the laptop keyboard
        g.setColor(new Color(50, 50, 50));  // Darker gray for keyboard
        g.fillRect(laptopX + 20, laptopY + laptopHeight - 50, laptopWidth - 40, 20);  // Keyboard

        // Draw keyboard keys
        g.setColor(Color.LIGHT_GRAY);
        int keyWidth = 40;
        int keyHeight = 15;
        for (int i = 0; i < 9; i++) {
            g.fillRect(laptopX + 20 + i * keyWidth, laptopY + laptopHeight - 45, keyWidth - 5, keyHeight);
        }

        // Draw a blinking cursor (only if cursorVisible is true)
        if (cursorVisible) {
            g.setColor(Color.GREEN);  // Green color for the cursor
            g.fillRect(laptopX + 30, laptopY + laptopHeight - 42, 2, 15);  // Cursor
        }

        // Draw additional design elements (like Wi-Fi signal)
        g.setColor(Color.BLUE);
        g.drawArc(laptopX + laptopWidth - 60, laptopY + 20, 40, 40, 0, 180);  // Wi-Fi signal arc
        g.drawLine(laptopX + laptopWidth - 40, laptopY + 20, laptopX + laptopWidth - 40, laptopY + 10);  // Vertical line for Wi-Fi

        // Draw title text "Code with AP"
        g.setFont(new Font("Serif", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString("Code with AP", (width - 150) / 2, laptopY + laptopHeight + 50);

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, width - 10, height - 10);  // Border around the window
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Laptop Icon Animation");
        LaptopIcon laptopPanel = new LaptopIcon();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(laptopPanel);
        frame.setVisible(true);
    }
}
