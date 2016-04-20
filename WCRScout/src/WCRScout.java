import javafx.application.Application;
import javafx.stage.Stage;

public class WCRScout extends Application{
	public static Stage window;
	public static MainMenu mainMenu;
	public static FieldDraw fieldDraw;
	
	public static void main (String[] args){
		launch();
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("WCRScout");
		window.centerOnScreen();
		mainMenu = new MainMenu();
		fieldDraw = new FieldDraw();
		window.setScene(mainMenu.getScene());
		window.show();
	}
}
