import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * @author 223041748
 * @version mini project 
 */
public class GraphBuilder {
	public static GraphADT buildGraphFromImage(File imageFile) throws IOException {
	    BufferedImage image = ImageIO.read(imageFile);
	    GraphADT graph = new GraphADT();
	    
	    if (image == null) {
	        throw new IOException("Unsupported image format or corrupted file.");
	    }

	    try {
	    	for (int y = 0; y < image.getHeight(); y+=30) {
		        for (int x = 0; x < image.getWidth(); x+=30) {
		           
		                int rgb = image.getRGB(x, y);
		                double intensity = calculateIntensity(rgb);
		                // Add ALL pixels regardless of intensity
		                graph.addNode(new Pixel(x, y, intensity));
		            
		        }
		    }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
	    if (graph.getNodes().isEmpty()) {
	        throw new IOException("No pixels processed , check image dimensions");
	    }
	    
	    
	    graph.buildKNN(5);
	    return graph;
	}

    private static double calculateIntensity(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return 0.299 * r + 0.587 * g + 0.114 * b;
    }
}

