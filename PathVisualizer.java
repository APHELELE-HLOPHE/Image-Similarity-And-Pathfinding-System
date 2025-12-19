import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;



public class PathVisualizer {
    public static BufferedImage drawPathOnImage(File imageFile, PositionList<Pixel> path) throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        Graphics2D g2d = image.createGraphics();
        
        // Draw the path
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        
        Pixel prev = null;
        for (Pixel node : path) {
            if (prev != null) {
                g2d.drawLine(prev.getX(), prev.getY(), node.getX(), node.getY());
            }
            prev = node;
        }
        
        // Mark start and end points
        if (!path.isEmpty()) {
            Pixel start = path.first();
            Pixel end = path.last();
            
            g2d.setColor(Color.GREEN);
            g2d.fillOval(start.getX() - 5, start.getY() - 5, 10, 10);
            
            g2d.setColor(Color.BLUE);
            g2d.fillOval(end.getX() - 5, end.getY() - 5, 10, 10);
        }
        
        g2d.dispose();
        return image;
    }

    public static BufferedImage drawGraphOnImage(File imageFile, PositionList<Edge> positionList) throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        Graphics2D g2d = image.createGraphics();
        
        // Draw the edges
        g2d.setColor(new Color(255, 0, 0, 150));
        g2d.setStroke(new BasicStroke(2));
        
        for (Edge edge : positionList) {
            g2d.drawLine(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY());
        }
        
        // Draw the nodes
        g2d.setColor(new Color(0, 255, 0, 200)); 
        for (Edge edge : positionList) {
            g2d.fillOval(edge.getSource().getX() - 3, edge.getSource().getY() - 3, 6, 6);
            g2d.fillOval(edge.getTarget().getX() - 3, edge.getTarget().getY() - 3, 6, 6);
        }
        
        g2d.dispose();
        return image;
        
    }
}