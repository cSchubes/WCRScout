
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenu{
	private Scene scene;
	private BorderPane mainLayout;
	private MenuBar menuBar;
	private Menu file;
	private Menu edit;
	private Image logo;
	private Button scoutSheet, fieldDraw, lookup;
	private GridPane grid;
	
	public MainMenu(){
		logo = new Image(getClass().getResourceAsStream("LargeLogoPic.jpg"));
		BackgroundImage back = new BackgroundImage(logo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(316*1.5, 359*1.5, false, false, false, false));
		mainLayout = new BorderPane();
		mainLayout.setBackground(new Background(back));
		
		menuBar = new MenuBar();
		menuBar.setBackground(new Background(new BackgroundFill(Color.MAROON, null, null)));
		file = new Menu("File");
		edit = new Menu("Edit");
		menuBar.getMenus().addAll(file, edit);
		mainLayout.setTop(menuBar);
		
		scoutSheet = new Button("New Scout Sheet");
		scoutSheet.setPrefSize(450, 200);
		scoutSheet.setFont(Font.font("Verdana", 45));
		scoutSheet.setOnAction(e -> {
			WCRScout.window.setScene(WCRScout.scoutSheet.getScene());
			WCRScout.window.centerOnScreen();
		});
		
		fieldDraw = new Button("Strategy Field");
		fieldDraw.setPrefSize(450, 200);
		fieldDraw.setFont(Font.font("Verdana", 50));
		fieldDraw.setOnAction(e -> {
			WCRScout.window.setScene(WCRScout.fieldDraw.getScene());
			WCRScout.window.centerOnScreen();
		});
		
		lookup = new Button("Team Report");
		lookup.setPrefSize(450, 200);
		
		grid = new GridPane();
		grid.setPadding(new Insets(100, 100, 100, 100));
		grid.setVgap(100);
		grid.setHgap(180);
		grid.add(scoutSheet, 0, 0);
		grid.add(fieldDraw, 1, 0);
		grid.add(lookup, 0, 1);
		
		mainLayout.setCenter(grid);
		
		scene = new Scene(mainLayout, 1280, 750);
		scene.getStylesheets().add(WCRScout.class.getResource("/testC.css").toExternalForm());
	}
	
	public Scene getScene(){
		return scene;
	}
}
