import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Report {	
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private GridPane grid, lowBarGrid, portGrid, chevalGrid, roughGrid, rampartGrid, moatGrid, drawGrid, sallyGrid, rockGrid, shotGrid, challengeGrid;
	private BorderPane border;
	private Label teamNumLab, teamNameLab, defenseLab, aLabel, bLabel, cLabel, dLabel, capabilities, shot, challenge, general;
	private TextField teamNumber, teamName;
	private ImageView lowBarView, portView, chevalView, roughView, rampartView, moatView, drawView, sallyView, rockView;
	private CheckBox lowBarCheck, portCheck, chevalCheck, roughCheck, rampartCheck, moatCheck, drawCheck, sallyCheck, rockCheck, highShotCheck, lowShotCheck, parkCheck, scaleCheck;
	private TextArea lowBarNotes, portNotes, chevalNotes, roughNotes, rampartNotes, moatNotes, drawNotes, sallyNotes, rockNotes, shotNotes, challengeNotes, generalNotes;
	private Button clear, save, backButton;
	private Image backImage;
	
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 125, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 125, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 0, 125, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 0, 125, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 0, 125, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 125, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 0, 125, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 0, 125, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 0, 125, true, true);
	
	//private Team team;
	
	public Report(){
		//teamNumLab = new Label(team.getNumber());
		//teamNumLab.setFont(Font.font("Verdana", 30));
		/*
		teamNumber = new TextField();
		teamNumber.setFont(Font.font("Verdana", 30));
		teamNumber.setPrefWidth(120);
		*/
		//teamNameLab = new Label(team.getName());
		//teamNameLab.setFont(Font.font("Verdana", 30));
		/*
		teamName = new TextField();
		teamName.setFont(Font.font("Verdana", 30));
		teamName.setPrefWidth(300);
		*/
		
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
		backButton.setPrefSize(50, 50);
		
		clear = new Button("Clear");
		clear.setFont(Font.font("Verdana", 30));
		clear.setPrefSize(200, 150);
		GridPane.setConstraints(clear, 5, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		clear.setPadding(new Insets(0, 25, 0, 25));
		clear.setTextAlignment(TextAlignment.CENTER);
		clear.setOnAction(e -> {
			//clear();
		});
		
		save = new Button("Save");
		save.setFont(Font.font("Verdana", 30));
		save.setPrefSize(200, 150);
		GridPane.setConstraints(save, 5, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		save.setPadding(new Insets(0, 25, 0, 25));
		save.setTextAlignment(TextAlignment.CENTER);
		
		defenseLab = new Label("Defenses");
		defenseLab.setFont(Font.font("Verdana", 40));
		defenseLab.setId("defenseLabels");
		aLabel = new Label("Category A");
		aLabel.setFont(Font.font("Verdana", 30));
		aLabel.setId("defenseLabels");
		bLabel = new Label("Category B");
		bLabel.setFont(Font.font("Verdana", 30));
		bLabel.setId("defenseLabels");
		cLabel = new Label("Category C");
		cLabel.setFont(Font.font("Verdana", 30));
		cLabel.setId("defenseLabels");
		dLabel = new Label("Category D");
		dLabel.setFont(Font.font("Verdana", 30));
		dLabel.setId("defenseLabels");
		GridPane.setConstraints(aLabel, 1, 5, 4, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(defenseLab, 1, 0, 4, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(bLabel, 1, 9, 4, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(cLabel, 1, 14, 4, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(dLabel, 1, 19, 4, 1, HPos.CENTER, VPos.CENTER);
		
		capabilities = new Label("Capablities");
		capabilities.setFont(Font.font("Verdana", 40));
		capabilities.setId("defenseLabels");
		capabilities.setPadding(new Insets(0, 0, 0, 50));
		GridPane.setConstraints(capabilities, 5, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		
		lowBarGrid = new GridPane();
		lowBarView = new ImageView(lowBar);
		GridPane.setConstraints(lowBarView, 0, 0);
		lowBarCheck = new CheckBox("Y/N");
		GridPane.setConstraints(lowBarCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		lowBarNotes = new TextArea();
		lowBarNotes.setPrefSize(0, 100);
		GridPane.setConstraints(lowBarNotes, 0, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		lowBarGrid.getChildren().addAll(lowBarCheck, lowBarView, lowBarNotes);
		GridPane.setConstraints(lowBarGrid, 1, 1, 2, 4, HPos.CENTER, VPos.CENTER);
		
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
		GridPane.setConstraints(roughGrid, 2, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		
		rampartGrid = new GridPane();
		rampartView = new ImageView(ramparts);
		GridPane.setConstraints(rampartView, 0, 0);
		rampartCheck = new CheckBox("Y/N");
		GridPane.setConstraints(rampartCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		rampartNotes = new TextArea();
		rampartNotes.setPrefSize(100, 100);
		GridPane.setConstraints(rampartNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		rampartGrid.getChildren().addAll(rampartView, rampartNotes, rampartCheck);
		GridPane.setConstraints(rampartGrid, 0, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		
		moatGrid = new GridPane();
		moatView = new ImageView(moat);
		GridPane.setConstraints(moatView, 0, 0);
		moatCheck = new CheckBox("Y/N");
		GridPane.setConstraints(moatCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		moatNotes = new TextArea();
		moatNotes.setPrefSize(100, 100);
		GridPane.setConstraints(moatNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		moatGrid.getChildren().addAll(moatView, moatCheck, moatNotes);
		GridPane.setConstraints(moatGrid, 2, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		
		drawGrid = new GridPane();
		drawView = new ImageView(drawbridge);
		GridPane.setConstraints(drawView, 0, 0);
		drawCheck = new CheckBox("Y/N");
		GridPane.setConstraints(drawCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		drawNotes = new TextArea();
		drawNotes.setPrefSize(100, 100);
		GridPane.setConstraints(drawNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		drawGrid.getChildren().addAll(drawView, drawCheck, drawNotes);
		GridPane.setConstraints(drawGrid, 0, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		
		sallyGrid = new GridPane();
		sallyView = new ImageView(sallyPort);
		GridPane.setConstraints(sallyView, 0, 0);
		sallyCheck = new CheckBox("Y/N");
		GridPane.setConstraints(sallyCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		sallyNotes = new TextArea();
		sallyNotes.setPrefSize(100, 100);
		GridPane.setConstraints(sallyNotes, 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		sallyGrid.getChildren().addAll(sallyView, sallyCheck, sallyNotes);
		GridPane.setConstraints(sallyGrid, 2, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		
		rockGrid = new GridPane();
		rockView = new ImageView(rockWall);
		GridPane.setConstraints(rockView, 0, 0);
		rockCheck = new CheckBox("Y/N");
		GridPane.setConstraints(rockCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		rockNotes = new TextArea();
		rockNotes.setPrefSize(100, 100);
		GridPane.setConstraints(rockNotes, 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		rockGrid.getChildren().addAll(rockView, rockCheck, rockNotes);
		GridPane.setConstraints(rockGrid, 0, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		
		shotGrid = new GridPane();
		shot = new Label("Shot:");
		shot.setFont(Font.font("Verdana", 35));
		shot.setPadding(new Insets(0, 0, 0, 50));
		GridPane.setConstraints(shot, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		highShotCheck = new CheckBox("High");
		highShotCheck.setId("capLabel");
		lowShotCheck = new CheckBox("Low ");
		lowShotCheck.setId("capLabel");
		GridPane.setConstraints(highShotCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(lowShotCheck, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		shotNotes = new TextArea();
		shotNotes.setPrefSize(100, 100);
		GridPane.setConstraints(shotNotes, 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.getChildren().addAll(shot, highShotCheck, lowShotCheck, shotNotes);
		GridPane.setConstraints(shotGrid, 5, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.setPadding(new Insets(0, 0, 0, 50));
		
		challengeGrid = new GridPane();
		challenge = new Label("Challenge:");
		challenge.setFont(Font.font("Verdana", 35));
		GridPane.setConstraints(challenge, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		parkCheck = new CheckBox("Park ");
		parkCheck.setId("capLabel");
		scaleCheck = new CheckBox("Scale");
		scaleCheck.setId("capLabel");
		GridPane.setConstraints(parkCheck, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(scaleCheck, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		challengeNotes = new TextArea();
		challengeNotes.setPrefSize(100, 100);
		GridPane.setConstraints(challengeNotes, 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.getChildren().addAll(challenge, parkCheck, scaleCheck, challengeNotes);
		GridPane.setConstraints(challengeGrid, 5, 5, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.setPadding(new Insets(0, 0, 0, 50));
		
		general = new Label("General Notes");
		general.setFont(Font.font("Verdana", 30));
		GridPane.setConstraints(general, 5, 9, 2, 1, HPos.CENTER, VPos.CENTER);
		
		generalNotes = new TextArea();
		generalNotes.setPrefSize(200, 400);
		GridPane.setConstraints(generalNotes, 5, 10, 3, 2, HPos.CENTER, VPos.CENTER);
		
		top = new HBox(10);
		top.setPadding(new Insets(10, 0, 0, 0));
		top.getChildren().addAll(backButton, teamNumLab, teamNameLab);
		top.setAlignment(Pos.CENTER);
		
		teamNumLab.setPadding(new Insets(0, 0, 0, 50));
		teamNameLab.setPadding(new Insets(0, 0, 0, 50));
		
		grid = new GridPane();
		grid.setPadding(new Insets(20,50,50,50));
		grid.setVgap(10);
		grid.setHgap(10);
		grid.getChildren().addAll(defenseLab, save, shotGrid, clear, general, generalNotes, challengeGrid, capabilities, lowBarGrid, rockGrid, dLabel, moatGrid, aLabel, bLabel, cLabel, drawGrid, portGrid, chevalGrid, roughGrid, rampartGrid, sallyGrid);
		border = new BorderPane();
		//border.setPrefSize(1280, 800);
		//Image back = new Image(getClass().getResourceAsStream("Complete Logo LOWQUALITY.png"));
		//BackgroundImage backImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(247,82, false, false, true, false));
		BackgroundFill fill = new BackgroundFill(Color.WHITE, null, null);
		//BackgroundImage[] imgArr = new BackgroundImage[1];
		BackgroundFill[] fillArr = new BackgroundFill[1];
		//imgArr[0] = backImg;
		fillArr[0] = fill;
		border.setMaxWidth(1280);
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
