import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SubwayTrainAnimation extends JPanel implements ActionListener {

    private int screenWidth = 1800;
    private int screenHeight = 800;

    private Timer timer;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<TrainCar> trainCars;
    private int trainX;
    private int trainSpeed = 3;
    private int skyColorStep = 1;
    private Color skyColor;

    public SubwayTrainAnimation() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.DARK_GRAY); // Background color

        // Initialize the timer for animation (60 FPS)
        timer = new Timer(16, this);
        timer.start();

        // Initialize the train with 5 cars
        trainCars = new ArrayList<>();
        trainX = -500; // Start the train off-screen (from the left)
        for (int i = 0; i < 5; i++) {
            trainCars.add(new TrainCar(trainX + i * 150, screenHeight - 200));
        }

        // Initial sky color for the background
        skyColor = new Color(70, 130, 180);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTitle(g);
        drawSky(g);
        drawCityScape(g);
        drawRails(g);
        drawTrain(g);
        drawFooterText(g);
    }

    // Draw the title at the top
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Serif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("Subway Train Animation", getWidth() / 2 - 150, 50); // Title in center-top
    }

    // Draw the footer text at the bottom
    private void drawFooterText(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 30); // Text at bottom
    }

    // Draw the changing sky (sky color transitions from blue to orange like a sunset)
    private void drawSky(Graphics g) {
        g.setColor(skyColor);
        g.fillRect(0, 0, screenWidth, screenHeight / 2);
    }

    // Draw the cityscape in the background (buildings)
    private void drawCityScape(Graphics g) {
        g.setColor(new Color(105, 105, 105)); // Dark gray for city buildings
        for (int i = 0; i < screenWidth; i += 200) {
            int buildingHeight = 100 + (int) (Math.random() * 150); // Random building height
            g.fillRect(i, screenHeight / 2 - buildingHeight, 150, buildingHeight);
        }
    }

    // Draw the subway tracks or rails
    private void drawRails(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, screenHeight - 150, screenWidth, 20); // Top rail
        g.fillRect(0, screenHeight - 100, screenWidth, 20); // Bottom rail

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < screenWidth; i += 50) {
            g.fillRect(i, screenHeight - 150, 10, 70); // Draw vertical rail ties
        }
    }

    // Draw the subway train
    private void drawTrain(Graphics g) {
        for (TrainCar car : trainCars) {
            car.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the train to the right
        for (TrainCar car : trainCars) {
            car.move(trainSpeed);
        }

        // If the last car goes off-screen, reset the train's position
        if (trainCars.get(trainCars.size() - 1).getX() > screenWidth) {
            trainX = -500;
            for (int i = 0; i < trainCars.size(); i++) {
                trainCars.get(i).setX(trainX + i * 150);
            }
        }

        // Sky color change effect (like a day-night transition)
        if (skyColorStep % 3 == 0) {
            int red = Math.min(255, skyColor.getRed() + 1);
            int green = Math.max(0, skyColor.getGreen() - 1);
            skyColor = new Color(red, green, skyColor.getBlue());
        }
        skyColorStep++;

        // Repaint the scene
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Subway Train Animation");
        SubwayTrainAnimation animation = new SubwayTrainAnimation();
        frame.add(animation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public int getTrainSpeed() {
        return trainSpeed;
    }

    public void setTrainSpeed(int trainSpeed) {
        this.trainSpeed = trainSpeed;
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

// TrainCar class representing each train car
class TrainCar {
    private int x, y;
    private int width = 150;
    private int height = 100;

    public TrainCar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Draw the train car
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        
        // Windows of the train car
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 10; i <= width - 40; i += 40) {
            g.fillRect(x + i, y + 30, 30, 30); // Windows
        }

        // Wheels of the train car
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + height, 30, 30);
        g.fillOval(x + width - 40, y + height, 30, 30);
    }

    public void move(int speed) {
        x += speed; // Move the train car horizontally
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
