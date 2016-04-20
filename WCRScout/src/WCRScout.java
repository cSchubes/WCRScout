import javafx.application.Application;
import javafx.stage.Stage;

public class WCRScout extends Application{
	public static Stage window;
	public static MainMenu mainMenu;
	public static FieldDraw fieldDraw;
	public static ScoutSheet scoutSheet;
	
	public static void main (String[] args){
		launch();
	}
	
	public void start(Stage primaryStage) throws Exception {
		//Application.setUserAgentStylesheet(getClass().getResource("testCSS.css")
			//	.toExternalForm());
		window = primaryStage;
		window.setTitle("WCRScout");
		window.centerOnScreen();
		mainMenu = new MainMenu();
		fieldDraw = new FieldDraw();
		scoutSheet = new ScoutSheet();
		window.setScene(mainMenu.getScene());
		window.show();
	}
}
