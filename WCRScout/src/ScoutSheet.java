import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.scene.text.Font;

public class ScoutSheet {
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private BorderPane border;
	private Label teamNumLab;
	private TextField teamNumber;
	
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 74, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 78, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 67, 0, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 67, 0, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 67, 0, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 73, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 67, 0, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 67, 0, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 67, 0, true, true);
	
	public ScoutSheet(){
		teamNumLab = new Label("Team Number:");
		teamNumLab.setFont(Font.font("Verdana", 20));
		teamNumber = new TextField();
		teamNumber.setFont(Font.font("Verdana", 20));
		teamNumber.setPrefWidth(80);
		top =  new HBox(10);
		top.setPadding(new Insets(10, 0, 0, 0));
		top.getChildren().addAll(teamNumLab, teamNumber);
		top.setAlignment(Pos.CENTER);
		border = new BorderPane();
		Image back = new Image(getClass().getResourceAsStream("Complete Logo LOWQUALITY.png"));
		BackgroundImage backImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(247,82, false, false, true, false));
		BackgroundFill fill = new BackgroundFill(Color.WHITE, null, null);
		BackgroundImage[] imgArr = new BackgroundImage[1];
		BackgroundFill[] fillArr = new BackgroundFill[1];
		imgArr[0] = backImg;
		fillArr[0] = fill;
		border.setBackground(new Background(fillArr, imgArr));
		border.setTop(top);
		scroller = new ScrollPane();
		scroller.setContent(border);
		scroller.setFitToHeight(true);
		scroller.setFitToWidth(true);
		scene = new Scene(scroller, 1280, 750);
	}
	
	public Scene getScene(){
		return scene;
	}
}
