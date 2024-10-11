import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class AquariumDesign extends JPanel implements ActionListener {

    private final Timer timer;
    private final ArrayList<SeaCreature> creatures;
    private final int AQUARIUM_WIDTH;
    private final int AQUARIUM_HEIGHT;

    public AquariumDesign() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.AQUARIUM_WIDTH = screenSize.width;
        this.AQUARIUM_HEIGHT = screenSize.height;

        this.setPreferredSize(new Dimension(AQUARIUM_WIDTH, AQUARIUM_HEIGHT));
        this.setBackground(Color.CYAN);
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        creatures = new ArrayList<>();
        Random random = new Random();

        // Create various sea creatures
        for (int i = 0; i < 15; i++) {
            switch (i % 3) {
                case 0 -> creatures.add(new Shark(random.nextInt(AQUARIUM_WIDTH), random.nextInt(AQUARIUM_HEIGHT - 100), random.nextInt(5) + 3));
                case 1 -> creatures.add(new Clownfish(random.nextInt(AQUARIUM_WIDTH), random.nextInt(AQUARIUM_HEIGHT - 100), random.nextInt(5) + 1));
                default -> creatures.add(new Jellyfish(random.nextInt(AQUARIUM_WIDTH), random.nextInt(AQUARIUM_HEIGHT - 100), random.nextInt(2) + 1));
            }
        }

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw title and text
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.setColor(Color.BLACK);
        g.drawString("Aquarium Animation", AQUARIUM_WIDTH / 2 - 200, 50);
        
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Code with AP", AQUARIUM_WIDTH / 2 - 100, 100);

        // Draw sea creatures
        for (SeaCreature creature : creatures) {
            creature.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (SeaCreature creature : creatures) {
            creature.move();
        }
        repaint();
    }

    // Abstract SeaCreature class
    private abstract static class SeaCreature {
        protected int x, y, speed;
        protected Color color;

        public SeaCreature(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public abstract void draw(Graphics g);

        public void move() {
            x += speed;
            if (x > Toolkit.getDefaultToolkit().getScreenSize().width) {
                x = 0; // Reset to start from the left side
            }
        }
    }

    // Shark class
    private static class Shark extends SeaCreature {
        public Shark(int x, int y, int speed) {
            super(x, y, speed);
            this.color = Color.GRAY;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, 60, 30); // Draw shark as an oval
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[]{x + 60, x + 75, x + 60}, new int[]{y + 10, y + 15, y + 20}, 3); // Shark fin
        }
    }

    // Clownfish class
    private static class Clownfish extends SeaCreature {
        public Clownfish(int x, int y, int speed) {
            super(x, y, speed);
            this.color = Color.ORANGE;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, 30, 15); // Draw clownfish as an oval
            g.setColor(Color.BLACK);
            g.fillOval(x + 5, y + 2, 5, 5); // Eye
            g.fillOval(x + 20, y + 2, 5, 5); // Eye
        }
    }

    // Jellyfish class
    private static class Jellyfish extends SeaCreature {
        public Jellyfish(int x, int y, int speed) {
            super(x, y, speed);
            this.color = new Color(255, 192, 203); // Light pink
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, 20, 20); // Draw jellyfish head
            g.setColor(Color.BLUE);
            for (int i = 0; i < 3; i++) {
                g.drawLine(x + 10, y + 20, x + 10 - i * 3, y + 30 + i * 5); // Tentacles
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aquarium Design");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setUndecorated(true); // Remove title bar
        AquariumDesign aquarium = new AquariumDesign();
        frame.add(aquarium);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
