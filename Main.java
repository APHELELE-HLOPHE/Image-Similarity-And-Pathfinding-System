import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author 223041748, 222088409, 219067401 , 223108398
 */
public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Image Similarity and Path Detection");
		
		ImageGUI gui = new ImageGUI();
		Scene scene = new Scene(gui,1000,600);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}

}
