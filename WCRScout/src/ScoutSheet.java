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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoutSheet {
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private GridPane grid, lowBarGrid, portGrid, chevalGrid, roughGrid, rampartGrid, moatGrid, drawGrid;
	private BorderPane border;
	private Label teamNumLab, defenseLab, aLabel, bLabel, cLabel;
	private TextField teamNumber;
	private ImageView lowBarView, portView, chevalView, roughView, rampartView, moatView, drawView;
	private CheckBox lowBarCheck, portCheck, chevalCheck, roughCheck, rampartCheck, moatCheck, drawCheck;
	private TextArea lowBarNotes, portNotes, chevalNotes, roughNotes, rampartNotes, moatNotes, drawNotes;
	
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 125, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 125, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 0, 125, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 0, 125, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 0, 125, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 73, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 67, 0, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 67, 0, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 0, 125, true, true);
	
	public ScoutSheet(){
		teamNumLab = new Label("Team Number:");
		teamNumLab.setFont(Font.font("Verdana", 30));
		teamNumber = new TextField();
		teamNumber.setFont(Font.font("Verdana", 30));
		teamNumber.setPrefWidth(120);
		defenseLab = new Label("Defenses");
		defenseLab.setFont(Font.font("Verdana", 40));
		aLabel = new Label("Category A");
		aLabel.setFont(Font.font("Verdana", 30));
		aLabel.setId("defenseLabels");
		bLabel = new Label("Category B");
		bLabel.setFont(Font.font("Verdana", 30));
		bLabel.setId("defenseLabels");
		GridPane.setConstraints(aLabel, 1, 4, 5, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(defenseLab, 1, 0, 4, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(bLabel, 1, 9, 4, 1, HPos.CENTER, VPos.CENTER);
		
		lowBarGrid = new GridPane();
		lowBarView = new ImageView(lowBar);
		GridPane.setConstraints(lowBarView, 0, 0);
		lowBarCheck = new CheckBox("Y/N");
		GridPane.setConstraints(lowBarCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		lowBarNotes = new TextArea();
		lowBarNotes.setPrefSize(0, 100);
		GridPane.setConstraints(lowBarNotes, 0, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		lowBarGrid.getChildren().addAll(lowBarCheck, lowBarView, lowBarNotes);
		GridPane.setConstraints(lowBarGrid, 0, 1, 2, 4, HPos.CENTER, VPos.CENTER);
		
		portGrid = new GridPane();
		portView = new ImageView(portCullis);
		GridPane.setConstraints(portView, 0, 0);
		portCheck = new CheckBox("Y/N");
		GridPane.setConstraints(portCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		portNotes = new TextArea();
		portNotes.setPrefSize(100, 100);
		GridPane.setConstraints(portNotes, 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		portGrid.getChildren().addAll(portView, portCheck, portNotes);
		GridPane.setConstraints(portGrid, 0, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		
		chevalGrid = new GridPane();
		chevalView = new ImageView(cheval);
		GridPane.setConstraints(chevalView, 0, 0);
		chevalCheck = new CheckBox("Y/N");
		GridPane.setConstraints(chevalCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		chevalNotes = new TextArea();
		chevalNotes.setPrefSize(100, 100);
		GridPane.setConstraints(chevalNotes, 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		chevalGrid.getChildren().addAll(chevalView, chevalCheck, chevalNotes);
		GridPane.setConstraints(chevalGrid, 2, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		
		roughGrid = new GridPane();
		roughView = new ImageView(roughTerrain);
		GridPane.setConstraints(roughView, 0, 0);
		roughCheck = new CheckBox("Y/N");
		GridPane.setConstraints(roughCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		roughNotes = new TextArea();
		roughNotes.setPrefSize(100, 100);
		GridPane.setConstraints(roughNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		roughGrid.getChildren().addAll(roughView, roughCheck, roughNotes);
		GridPane.setConstraints(roughGrid, 2, 1, 2, 3, HPos.CENTER, VPos.CENTER);
		
		rampartGrid = new GridPane();
		rampartView = new ImageView(ramparts);
		GridPane.setConstraints(rampartView, 0, 0);
		rampartCheck = new CheckBox("Y/N");
		GridPane.setConstraints(rampartCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		rampartNotes = new TextArea();
		rampartNotes.setPrefSize(100, 100);
		GridPane.setConstraints(rampartNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		rampartGrid.getChildren().addAll(rampartView, rampartNotes, rampartCheck);
		GridPane.setConstraints(rampartGrid, 0, 9, 2, 3, HPos.CENTER, VPos.CENTER);
		
		moatGrid = new GridPane();
		moatView = new ImageView(moat);
		GridPane.setConstraints(moatView, 0, 0);
		moatCheck = new CheckBox("Y/N");
		GridPane.setConstraints(moatCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		moatNotes = new TextArea();
		moatNotes.setPrefSize(100, 100);
		GridPane.setConstraints(moatNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		moatGrid.getChildren().addAll(moatView, moatCheck, moatNotes);
		GridPane.setConstraints(moatGrid, 3, 9, 2, 3, HPos.CENTER, VPos.CENTER);
		
		drawGrid = new GridPane();
		drawView = new ImageView(drawBridge);
		
		top =  new HBox(10);
		top.setPadding(new Insets(10, 10, 0, 10));
		top.getChildren().addAll(teamNumLab, teamNumber);
		top.setAlignment(Pos.CENTER);
		grid = new GridPane();
		grid.setPadding(new Insets(0,50,50,50));
		grid.setVgap(10);
		grid.setHgap(10);
		grid.getChildren().addAll(defenseLab, lowBarGrid, moatGrid, aLabel, bLabel, portGrid, chevalGrid, roughGrid, rampartGrid);
		border = new BorderPane();
		Image back = new Image(getClass().getResourceAsStream("Complete Logo LOWQUALITY.png"));
		//BackgroundImage backImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(247,82, false, false, true, false));
		BackgroundFill fill = new BackgroundFill(Color.WHITE, null, null);
		//BackgroundImage[] imgArr = new BackgroundImage[1];
		BackgroundFill[] fillArr = new BackgroundFill[1];
		//imgArr[0] = backImg;
		fillArr[0] = fill;
		border.setBackground(new Background(fillArr));
		border.setBorder(new Border(new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID, null, new BorderWidths(20))));
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
