import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FireworksDisplay extends JPanel implements ActionListener {

    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Firework> fireworks;
    private Random random;
    private int glowAlpha = 0;
    private boolean increasing = true;

    public FireworksDisplay() {
        timer = new Timer(30, this);
        fireworks = new ArrayList<>();
        random = new Random();

        // Adding more fireworks for the animation
        for (int i = 0; i < 15; i++) {
            fireworks.add(new Firework());
        }

        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fireworks Display");
        FireworksDisplay display = new FireworksDisplay();
        frame.add(display);
        frame.setSize(1000, 800);  // Increased window width
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set a gradient night sky background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), new Color(25, 25, 112));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw left and right side elements (trees, buildings)
        drawSideElements(g2d);

        // Glowing text effect for the title
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.setColor(new Color(255, 255, 255, glowAlpha));
        String title = "Fireworks Display";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, getWidth() / 2 - titleWidth / 2, 50);

        // Animate fireworks
        for (Firework firework : fireworks) {
            firework.draw(g2d);
        }

        // Animate footer text
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        String footerText = "Code with AP";
        int textWidth = g2d.getFontMetrics().stringWidth(footerText);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawString(footerText, getWidth() / 2 - textWidth / 2, getHeight() - 40);
    }

    private void drawSideElements(Graphics2D g2d) {
        g2d.setColor(new Color(60, 60, 60));
        // Draw left-side building
        g2d.fillRect(50, 500, 100, 300);
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(70, 520, 20, 20);  // Windows
        g2d.fillRect(110, 550, 20, 20);

        // Draw right-side tree
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillOval(850, 650, 80, 100);  // Tree foliage
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(885, 700, 10, 100);  // Tree trunk
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Glow effect for title text
        if (increasing) {
            glowAlpha += 5;
            if (glowAlpha >= 255) increasing = false;
        } else {
            glowAlpha -= 5;
            if (glowAlpha <= 0) increasing = true;
        }

        for (Firework firework : fireworks) {
            firework.update();
        }
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

class Firework {
    private int x, y;
    private int size;
    private Color color;
    private int burstTime;
    private ArrayList<Spark> sparks;
    private boolean exploded;

    private Random random = new Random();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Firework() {
        reset();
    }

    public void reset() {
        this.x = random.nextInt(800) + 100;  // Updated range for random positioning
        this.y = 700;
        this.size = random.nextInt(40) + 20;  // Larger burst sizes
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        this.burstTime = random.nextInt(30) + 30;
        this.sparks = new ArrayList<>();
        this.exploded = false;
    }

    public void draw(Graphics2D g2d) {
        if (!exploded) {
            g2d.setColor(color);
            g2d.fillOval(x, y, 10, 10);
        } else {
            for (Spark spark : sparks) {
                spark.draw(g2d);
            }
        }
    }

    public void update() {
        if (!exploded) {
            y -= random.nextInt(6) + 3;  // Varying speed
            burstTime--;
            if (burstTime <= 0) {
                explode();
            }
        } else {
            for (Spark spark : sparks) {
                spark.update();
            }
            if (sparks.stream().allMatch(Spark::isFaded)) {
                reset();
            }
        }
    }

    public void explode() {
        exploded = true;
        int numSparks = random.nextInt(70) + 70;  // More sparks
        for (int i = 0; i < numSparks; i++) {
            sparks.add(new Spark(x, y, size, color));
        }
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}

class Spark {
    private int x, y;
    private int size;
    private Color color;
    private double angle;
    private double speed;
    private int life;

    private Random random = new Random();

    public Spark(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = random.nextInt(size / 2) + size / 2;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
        this.angle = random.nextDouble() * 2 * Math.PI;
        this.speed = random.nextDouble() * 5 + 2;
        this.life = random.nextInt(40) + 40;  // Increased spark life
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, size, size);
    }

    public void update() {
        x += (int) (speed * Math.cos(angle));
        y += (int) (speed * Math.sin(angle));
        life--;
        if (life > 0) {
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, color.getAlpha() - 5));
        }
    }

    public boolean isFaded() {
        return life <= 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
