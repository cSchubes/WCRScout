import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoutSheet {
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private GridPane grid;
	private BorderPane border;
	private Label teamNumLab, defenseLab; 
	private TextField teamNumber;
	private ImageView lowBarView;
	private CheckBox lowBarCheck;
	private TextArea lowBarNotes;
	
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 125, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 150, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 67, 0, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 67, 0, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 67, 0, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 73, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 67, 0, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 67, 0, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 67, 0, true, true);
	
	public ScoutSheet(){
		teamNumLab = new Label("Team Number:");
		teamNumLab.setFont(Font.font("Verdana", 30));
		teamNumber = new TextField();
		teamNumber.setFont(Font.font("Verdana", 30));
		teamNumber.setPrefWidth(120);
		defenseLab = new Label("Defenses");
		defenseLab.setFont(Font.font("Verdana", 40));
		GridPane.setConstraints(defenseLab, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		lowBarView = new ImageView(lowBar);
		GridPane.setConstraints(lowBarView, 0, 1);
		lowBarCheck = new CheckBox("Y/N");
		GridPane.setConstraints(lowBarCheck, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		lowBarNotes = new TextArea();
		lowBarNotes.setPrefSize(100, 100);
		GridPane.setConstraints(lowBarNotes, 0, 2, 2, 1, HPos.CENTER, VPos.CENTER);
		top =  new HBox(10);
		top.setPadding(new Insets(10, 10, 0, 10));
		top.getChildren().addAll(teamNumLab, teamNumber);
		top.setAlignment(Pos.CENTER);
		grid = new GridPane();
		grid.setPadding(new Insets(0,50,50,50));
		grid.getChildren().addAll(defenseLab, lowBarView, lowBarCheck, lowBarNotes);
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
		border.setCenter(grid);
		scroller = new ScrollPane();
		scroller.setContent(border);
		scroller.setFitToHeight(true);
		scroller.setFitToWidth(true);
		scene = new Scene(scroller, 1280, 750);
		scene.getStylesheets().add(WCRScout.class.getResource("testC.css").toExternalForm());
	}
	
	public Scene getScene(){
		return scene;
	}
}
