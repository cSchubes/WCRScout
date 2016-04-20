import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ScoutSheet {
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private GridPane grid;
	private BorderPane border;
	private Label teamNumLab;
	private TextField teamNumber;
	
	public ScoutSheet(){
		teamNumLab = new Label("Team Number:");
		teamNumber = new TextField();
		top =  new HBox(10);
		top.setPadding(new Insets(10, 0, 0, 0));
		top.getChildren().addAll(teamNumLab, teamNumber);
		top.setAlignment(Pos.CENTER);
		//grid = new GridPane();
		border = new BorderPane();
		Image back = new Image(getClass().getResourceAsStream("Complete Logo LOWQUALITY.png"));
		BackgroundImage backImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(247,82, false, false, true, false));
		border.setBackground(new Background(backImg));
		border.setTop(top);
		scroller = new ScrollPane();
		scroller.setContent(border);
		scroller.setFitToHeight(true);
		scroller.setFitToWidth(true);
		scene = new Scene(scroller, 1280, 800);
	}
	
	public Scene getScene(){
		return scene;
	}
}
