import javafx.application.Application;
import javafx.stage.Stage;

public class WCRScout extends Application{
	public static Stage window;
	public static MainMenu mainMenu;
	public static FieldDraw fieldDraw;
	public static ScoutSheet scoutSheet;
	public static ReportSearch reportSearch;
	public static Report report;
	
	public static void main (String[] args){
		launch();
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("WCRScout");
		window.centerOnScreen();
		window.setResizable(false);
		window.setMaxWidth(1280);
		window.setMaxHeight(800);
		mainMenu = new MainMenu();
		fieldDraw = new FieldDraw();
		scoutSheet = new ScoutSheet();
		reportSearch = new ReportSearch();
		window.setScene(mainMenu.getScene());
		window.show();
	}
}
