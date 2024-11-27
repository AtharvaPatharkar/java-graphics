import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SkyscraperWindowView extends JPanel implements ActionListener {

    private Timer timer;
    private Random random;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Bird> birds;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Cloud> clouds;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean[][] windowLights;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Star> stars;
    @SuppressWarnings("FieldMayBeFinal")
    private Helicopter helicopter;
    
    public SkyscraperWindowView() {
        random = new Random();
        birds = new ArrayList<>();
        clouds = new ArrayList<>();
        stars = new ArrayList<>();
        
        // Add some birds for the animation
        for (int i = 0; i < 5; i++) {
            birds.add(new Bird(random.nextInt(800), random.nextInt(400)));
        }

        // Add clouds
        for (int i = 0; i < 3; i++) {
            clouds.add(new Cloud(random.nextInt(800), random.nextInt(200)));
        }
        
        // Add stars
        for (int i = 0; i < 50; i++) {
            stars.add(new Star(random.nextInt(800), random.nextInt(300)));
        }

        // Helicopter
        helicopter = new Helicopter(-200, 150);

        // Skyscraper window lights (true = on, false = off)
        windowLights = new boolean[8][8];
        for (boolean[] windowLight : windowLights) {
            for (int j = 0; j < windowLight.length; j++) {
                windowLight[j] = random.nextBoolean(); // Randomly turn lights on/off
            }
        }

        timer = new Timer(30, this);
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Enhanced Skyscraper View");
        SkyscraperWindowView view = new SkyscraperWindowView();
        
        frame.add(view);
        frame.setSize(800, 800);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color with gradient effect
        GradientPaint background = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.DARK_GRAY);
        g2d.setPaint(background);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw twinkling stars
        for (Star star : stars) {
            star.draw(g2d);
        }

        // Draw moving clouds
        for (Cloud cloud : clouds) {
            cloud.draw(g2d);
        }

        // Draw helicopter
        helicopter.draw(g2d);

        // Draw the title at the top
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String title = "Skyscraper Window View";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, getWidth() / 2 - titleWidth / 2, 40);

        // Draw the skyscraper in the center with 3D effects
        draw3DSkyscraper(g2d);

        // Draw birds flying across the screen
        for (Bird bird : birds) {
            bird.draw(g2d);
        }

        // Draw the footer text with gradient color
        drawGradientFooter(g2d);
    }

    // Method to draw a 3D-effect skyscraper
    private void draw3DSkyscraper(Graphics2D g2d) {
        int buildingWidth = 400;
        int buildingHeight = 600;
        int buildingX = getWidth() / 2 - buildingWidth / 2;
        int buildingY = getHeight() / 2 - buildingHeight / 2;

        // Gradient effect for the building
        GradientPaint buildingPaint = new GradientPaint(buildingX, buildingY, Color.GRAY, buildingX + buildingWidth, buildingY + buildingHeight, Color.DARK_GRAY);
        g2d.setPaint(buildingPaint);
        g2d.fillRect(buildingX, buildingY, buildingWidth, buildingHeight);

        // Draw windows with light and shadows
        drawWindows(g2d, buildingX, buildingY, buildingWidth, buildingHeight);
    }

    private void drawWindows(Graphics2D g2d, int buildingX, int buildingY, int buildingWidth, int buildingHeight) {
        int rows = 8, cols = 8;
        int windowWidth = buildingWidth / cols;
        int windowHeight = buildingHeight / rows;
        int gap = 10; 

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = buildingX + col * (windowWidth + gap) + gap / 2;
                int y = buildingY + row * (windowHeight + gap) + gap / 2;

                if (windowLights[row][col]) {
                    g2d.setColor(Color.YELLOW);
                } else {
                    g2d.setColor(Color.DARK_GRAY);
                }

                g2d.fillRect(x, y, windowWidth - gap, windowHeight - gap);
                g2d.setColor(new Color(50, 50, 50, 120));  // Shadow for a 3D effect
                g2d.drawRect(x + 2, y + 2, windowWidth - gap, windowHeight - gap);
            }
        }
    }

    private void drawGradientFooter(Graphics2D g2d) {
        String footerText = "Code with AP";
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        GradientPaint gradientPaint = new GradientPaint(0, getHeight() - 40, Color.BLUE, getWidth(), getHeight() - 40, Color.MAGENTA);
        g2d.setPaint(gradientPaint);
        int textWidth = g2d.getFontMetrics().stringWidth(footerText);
        g2d.drawString(footerText, getWidth() / 2 - textWidth / 2, getHeight() - 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update birds, clouds, and helicopter
        for (Bird bird : birds) {
            bird.move();
        }
        for (Cloud cloud : clouds) {
            cloud.move();
        }
        helicopter.move();
        
        // Randomly change window lights
        if (random.nextInt(10) < 2) { 
            int randomRow = random.nextInt(8);
            int randomCol = random.nextInt(8);
            windowLights[randomRow][randomCol] = !windowLights[randomRow][randomCol];
        }

        // Update star twinkling
        for (Star star : stars) {
            star.twinkle();
        }

        repaint();  
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    // Bird class representing birds flying across the screen
    class Bird {
        private int x, y;
        private int size;
        private int speed;

        public Bird(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = random.nextInt(20) + 10;
            this.speed = random.nextInt(5) + 2;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x, y, size, size / 2);
        }

        public void move() {
            x += speed;
            if (x > getWidth()) {
                x = -size;
                y = random.nextInt(400);
            }
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    // Cloud class representing clouds moving across the sky
    class Cloud {
        private int x, y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(x, y, 100, 50);
        }

        public void move() {
            x += 1;
            if (x > getWidth()) {
                x = -100;
                y = random.nextInt(200);
            }
        }
    }

    // Helicopter class representing a helicopter flying across the screen
    class Helicopter {
        private int x, y;
        private int speed;

        public Helicopter(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = 4;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.fillRect(x, y, 80, 30);  // Body
            g2d.setColor(Color.BLACK);
            g2d.fillRect(x + 20, y - 10, 40, 10);  // Rotor
        }

        public void move() {
            x += speed;
            if (x > getWidth()) {
                x = -200;
                y = random.nextInt(200) + 50;
            }
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    // Star class for twinkling stars
    class Star {
        private int x, y;
        private boolean twinkleOn;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
            this.twinkleOn = random.nextBoolean();
        }

        public void draw(Graphics2D g2d) {
            if (twinkleOn) {
                g2d.setColor(Color.WHITE);
                g2d.fillOval(x, y, 3, 3);
            }
        }

        public void twinkle() {
            if (random.nextInt(10) < 2) {
                twinkleOn = !twinkleOn;
            }
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
    }
}
