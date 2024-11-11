import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrainOnRoad extends JPanel implements ActionListener {
    private int trainX = -300;  // Train starts off-screen to the left
    private int trainSpeed = 3;  // Speed of the train
    private Timer timer;
    private int smokeOffset = 0;  // For animated smoke

    public TrainOnRoad() {
        // Set up the timer for the animation
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the background and scenery
        drawBackground(g);
        drawScenery(g);

        // Draw the train on the road with the original colors and updated design
        drawTrain(g, trainX, getHeight() - 100);  // Lower the train to the "road"

        // Draw title and border
        drawTitle(g);
        drawBorder(g);
    }

    private void drawBackground(Graphics g) {
        // Sky
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Road (lowered the road for the train to be on it)
        g.setColor(new Color(100, 100, 100));
        g.fillRect(0, getHeight() - 80, getWidth(), 60);  // Road area
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Train on Road Animation", getWidth() / 2 - 150, 30);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Code with AP", getWidth() / 2 - 50, 60);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
    }

    private void drawTrain(Graphics g, int x, int y) {
        // Draw the locomotive without changing the color
        drawLocomotive(g, x, y);
        
        // Draw bogies (train cars)
        drawBogie(g, x + 100, y);
        drawBogie(g, x + 200, y);
        drawBogie(g, x + 300, y);
    }

    private void drawLocomotive(Graphics g, int x, int y) {
        // Body of the locomotive
        g.setColor(Color.RED);
        g.fillRect(x, y + 20, 100, 50);

        // Window
        g.setColor(Color.WHITE);
        g.fillRect(x + 20, y + 25, 30, 25);

        // Wheels with improved design
        g.setColor(Color.BLACK);
        g.fillOval(x + 15, y + 60, 30, 30);  // Left wheel
        g.fillOval(x + 55, y + 60, 30, 30);  // Right wheel

        // Draw animated smoke
        drawSmoke(g, x, y);
    }

    private void drawSmoke(Graphics g, int x, int y) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x + 20 + smokeOffset, y - 40, 20, 20);
        g.fillOval(x + 40 + smokeOffset * 2, y - 60, 30, 30);
        g.fillOval(x + 60 + smokeOffset * 3, y - 80, 40, 40);
    }

    private void drawBogie(Graphics g, int x, int y) {
        // Body of the bogie
        g.setColor(Color.BLUE);
        g.fillRect(x, y + 20, 100, 50);

        // Bogie windows
        g.setColor(Color.WHITE);
        g.fillRect(x + 15, y + 30, 20, 20);
        g.fillRect(x + 55, y + 30, 20, 20);

        // Wheels
        g.setColor(Color.BLACK);
        g.fillOval(x + 15, y + 60, 30, 30);  // Left wheel
        g.fillOval(x + 55, y + 60, 30, 30);  // Right wheel
    }

    private void drawScenery(Graphics g) {
        // Draw trees along the road
        drawTree(g, 100, getHeight() - 150);
        drawTree(g, 300, getHeight() - 150);
        drawTree(g, 500, getHeight() - 150);
    }

    private void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(139, 69, 19));  // Brown for tree trunks
        g.fillRect(x, y, 20, 40);  // Tree trunk

        g.setColor(Color.GREEN);  // Green for leaves
        g.fillOval(x - 20, y - 30, 60, 60);  // Tree leaves
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the train to the right (train moves from left to right)
        trainX += trainSpeed;

        // Reset train position when it goes off-screen
        if (trainX > getWidth()) {
            trainX = -300;
        }

        // Animate the smoke
        smokeOffset += 2;
        if (smokeOffset > 20) {
            smokeOffset = 0;
        }

        // Trigger repaint to animate movement
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Train Animation on Road");
        TrainOnRoad animatedTrain = new TrainOnRoad();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(animatedTrain);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
    }

    public int getTrainSpeed() {
        return trainSpeed;
    }

    public void setTrainSpeed(int trainSpeed) {
        this.trainSpeed = trainSpeed;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
