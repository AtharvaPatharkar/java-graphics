import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CubeRendering extends JPanel implements ActionListener {

    private float angleX = 0; // Rotation angle around X-axis
    private float angleY = 0; // Rotation angle around Y-axis
    private Timer timer;

    public CubeRendering() {
        setPreferredSize(new Dimension(800, 600)); // Set panel size
        setBackground(new Color(200, 200, 255)); // Light blue background
        timer = new Timer(50, this); // Create a timer for animation
        timer.start(); // Start the animation
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
        g.drawString("3D Cube Rendering", getWidth() / 2 - 120, 50);

        // Draw the text "Code with AP"
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString("Code with AP", getWidth() / 2 - 60, getHeight() - 50);

        // Draw the cube
        drawCube(g);
    }

    private void drawCube(Graphics g) {
        // 3D cube vertices
        float[][] vertices = {
            {-1, -1, -1}, // 0
            { 1, -1, -1}, // 1
            { 1,  1, -1}, // 2
            {-1,  1, -1}, // 3
            {-1, -1,  1}, // 4
            { 1, -1,  1}, // 5
            { 1,  1,  1}, // 6
            {-1,  1,  1}, // 7
        };

        // Cube edges (connections between vertices)
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0}, // Back face
            {4, 5}, {5, 6}, {6, 7}, {7, 4}, // Front face
            {0, 4}, {1, 5}, {2, 6}, {3, 7}, // Connecting edges
        };

        // Cube faces (to be colored)
        int[][] faces = {
            {0, 1, 2, 3}, // Back face
            {4, 5, 6, 7}, // Front face
            {0, 1, 5, 4}, // Left face
            {2, 3, 7, 6}, // Right face
            {0, 3, 7, 4}, // Bottom face
            {1, 2, 6, 5}, // Top face
        };

        // Colors for each face
        Color[] faceColors = {
            new Color(255, 0, 0),    // Red
            new Color(0, 255, 0),    // Green
            new Color(0, 0, 255),    // Blue
            new Color(255, 255, 0),  // Yellow
            new Color(255, 165, 0),  // Orange
            new Color(128, 0, 128),  // Purple
        };

        // Center of the screen
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Scale factor
        float scale = 100;

        // Draw the cube faces with colors
        for (int i = 0; i < faces.length; i++) {
            int[] face = faces[i];

            // Get transformed vertices
            Polygon polygon = new Polygon();
            for (int vertexIndex : face) {
                float[] v = rotate(vertices[vertexIndex], angleX, angleY);
                polygon.addPoint(centerX + (int)(v[0] * scale), centerY + (int)(v[1] * scale));
            }

            // Set color and fill the face
            g.setColor(faceColors[i]);
            g.fillPolygon(polygon);
        }

        // Draw shadow
        g.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black
        g.fillOval(centerX - 60, centerY + 40, 120, 40); // Shadow under the cube

        // Draw edges to give a 3D effect
        g.setColor(Color.BLACK);
        for (int[] edge : edges) {
            float[] v1 = rotate(vertices[edge[0]], angleX, angleY);
            float[] v2 = rotate(vertices[edge[1]], angleX, angleY);
            g.drawLine(centerX + (int)(v1[0] * scale), centerY + (int)(v1[1] * scale),
                       centerX + (int)(v2[0] * scale), centerY + (int)(v2[1] * scale));
        }
    }

    // Method to rotate vertices
    private float[] rotate(float[] vertex, float angleX, float angleY) {
        float[] rotated = new float[3];
        // Rotation around X-axis
        float cosX = (float)Math.cos(angleX);
        float sinX = (float)Math.sin(angleX);
        rotated[1] = cosX * vertex[1] - sinX * vertex[2];
        rotated[2] = sinX * vertex[1] + cosX * vertex[2];
        rotated[0] = vertex[0];

        // Rotation around Y-axis
        float cosY = (float)Math.cos(angleY);
        float sinY = (float)Math.sin(angleY);
        float tempX = rotated[0];
        rotated[0] = cosY * tempX + sinY * rotated[2];
        rotated[2] = -sinY * tempX + cosY * rotated[2];

        return rotated;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Increment rotation angles
        angleX += Math.toRadians(2);
        angleY += Math.toRadians(2);

        // Redraw the panel to update the animations
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Cube Rendering");
        CubeRendering panel = new CubeRendering();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
