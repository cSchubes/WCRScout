import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BlueAlliance {
	private Scene scene;
	private ScrollPane scroller;
	private BorderPane root;
	private HBox top;
	private Button backButton;
	private Image backImage;
	final WebView browser = new WebView();
	final WebEngine engine = browser.getEngine();
	
	public BlueAlliance(){
		root = new BorderPane();
		scroller = new ScrollPane();
		top = new HBox();
		backButton = new Button();
		backImage = new Image(getClass().getResourceAsStream("back arrow.png"));
		BackgroundImage back = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(30, 30, true, true, true, false));
		BackgroundFill fill2 = new BackgroundFill(Color.MAROON, null, null);
		BackgroundImage[] backArr2 = new BackgroundImage[1];
		BackgroundFill[] fillArr2 = new BackgroundFill[1];
		backArr2[0] = back;
		fillArr2[0] = fill2;
		backButton.setBackground(new Background(fillArr2, backArr2));
		backButton.setOnAction(e -> {
			WCRScout.window.setScene(WCRScout.mainMenu.getScene());
			WCRScout.window.centerOnScreen();
		});
		backButton.setPrefSize(30, 30);
		backButton.setAlignment(Pos.CENTER_LEFT);
		top.getChildren().addAll(backButton);
		scroller.setContent(browser);
		engine.load("https://www.thebluealliance.com/");
		browser.setPrefSize(1280, 750);
		root.setCenter(scroller);
		root.setTop(top);
		scene = new Scene(root, 1280, 750);
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public void load(String number){
		engine.load("https://www.thebluealliance.com/team/" + number);
	}
	
	public void reset(){
		engine.load("https://www.thebluealliance.com/");
	}
}
