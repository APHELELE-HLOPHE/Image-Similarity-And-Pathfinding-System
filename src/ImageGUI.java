
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;



import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class ImageGUI extends GridPane {
	
	private Button btnLoadImage1, btnLoadImage2, btnCompareImages, btnFindPath;
    private ImageView imageView1, imageView2, resultImageView;
    private TextArea txtResponse;
    private File file1, file2;
    private FileChooser fileChooser;
    private Stage stage;
    private GraphADT graph1, graph2;
    
    public ImageGUI() {
    	initializeGUI();
    	setEventHandlers();
    }

	private void setEventHandlers() {
		btnLoadImage1.setOnAction(e -> loadImage(1));
		btnLoadImage2.setOnAction(e ->loadImage(2));
		btnCompareImages.setOnAction(e -> compare());
		btnFindPath.setOnAction(e -> findPath());
	}



	private ImageView createImageView() {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(200);
		imgView.setFitWidth(200);
		imgView.setPreserveRatio(true);
		return imgView;
	}

	
	private void loadImage(int img) {
	    fileChooser = new FileChooser();
	    fileChooser.setTitle("Select Image File");
	    
	    File file = fileChooser.showOpenDialog(stage);
	   
	    if (file == null) { 
	    	return;
	    
	    }
	    
	    try {
	        Image fxImage = new Image(new FileInputStream(file));
	        
	        if(fxImage == null) {
	        	txtResponse.appendText("Image file creation failed!");
	        }
	        
	        if (img == 1) {
	        	
	            imageView1.setImage(fxImage);
	            file1 = file;
	           
	            graph1 = GraphBuilder.buildGraphFromImage(file1);
	            
	            txtResponse.appendText("\nImage 1 Loaded: " + file1.getName() + 
	                               " (" + graph1.getNodes().size() + " nodes)");
	        } else {
	            imageView2.setImage(fxImage);
	            file2 = file;
	            graph2 = GraphBuilder.buildGraphFromImage(file2);
	            txtResponse.appendText("\nImage 2 Loaded: " + file2.getName() + 
	                               " (" + graph2.getNodes().size() + " nodes)");
	        }
	    } catch (IllegalArgumentException e) {
	        txtResponse.appendText("\nERROR loading image: " + e.getMessage());
	        if (img == 1) graph1 = null;
	        else graph2 = null;
	    } catch (IOException e) {
			// TODO Auto-generated catch block
	    	txtResponse.appendText("\nERROR loading image: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeGUI() {
		// TODO Auto-generated method stub
		//buttons
				btnLoadImage1 = new Button("Load Image 1");
				btnLoadImage2 = new Button("Load Image 2");
				btnCompareImages = new Button("Compare Images");
				btnFindPath = new Button("Find Path");
				
				//Image view
				imageView1 = createImageView();
				imageView2 = createImageView();
				resultImageView = createImageView();
				
				//TextArea 
				txtResponse = new TextArea();
				
				add(btnLoadImage1,0,0);
				add(btnLoadImage2,1,0);
				add(btnCompareImages,2,0);
				add(btnFindPath,3,0);
				add(imageView1,0,1);
				add(imageView2,1,1);
				add(resultImageView,2,1);
				add(txtResponse,0,2,4,1);
				
				setHgap(10);
				setVgap(10);
				
	}

	private void compare() {
	    if (file1 == null || file2 == null) {
	        txtResponse.appendText("\nPlease load both images first!");
	        return;
	    }
	    
	    if (graph1 == null || graph2 == null) {
	        txtResponse.appendText("\nGraphs not initialized properly!");
	        return;
	    }
	    
	    try {
	        double similarity = GraphCompare.compare(graph1, graph2, 5);
	        txtResponse.appendText(String.format("\nImage similarity: %.2f%%", similarity * 100));
	        
	        BufferedImage mstImage = PathVisualizer.drawGraphOnImage(file1, graph1.buildMST());
	        resultImageView.setImage(ImageUtils.convertToJavaFXImage(mstImage));
	    } catch (Exception e) {
	        txtResponse.appendText("\nError comparing images: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	private void findPath() {
        if (graph1 == null) {
            txtResponse.appendText("\nPlease load an image first!");
            return;
        }
        
        if (graph1.getNodes().size() < 2) {
            txtResponse.appendText("\nImage is too small for path finding!");
            return;
        }
        
        try {
            Pixel start = graph1.getNodes().first();
            Pixel end = graph1.getNodes().last();
            
            PositionList<Pixel> path = graph1.findShortestPath(start, end);
            txtResponse.appendText(String.format("\nFound path from (%d,%d) to (%d,%d) with %d steps", 
                start.getX(), start.getY(), end.getX(), end.getY(), path.size()));
            
            BufferedImage pathImage = PathVisualizer.drawPathOnImage(file1, path);
            resultImageView.setImage(ImageUtils.convertToJavaFXImage(pathImage));
            
        } catch (Exception e) {
            txtResponse.appendText("\nError finding path: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
