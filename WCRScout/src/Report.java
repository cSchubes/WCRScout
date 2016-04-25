import javax.swing.GroupLayout.Alignment;

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
	private ImageView lowBarView, portView, chevalView, roughView, rampartView, moatView, drawView, sallyView, rockView;
	private Label[] infoLabels;
	private Label[] noteLabels;
	private Button backButton, another, edit;
	private Image backImage;
	
	final int WIDTH = 150;
	
	static final int NUMBER_INDEX = 0;
	static final int NAME_INDEX = 1;
	static final int LOWBAR_INDEX = 0;
	static final int PORTCULLIS_INDEX = 1;
	static final int CHEVAL_INDEX = 3;
	static final int ROUGH_TERRAIN_INDEX = 8;
	static final int RAMPART_INDEX = 2;
	static final int MOAT_INDEX = 4;
	static final int DRAWBRIDGE_INDEX = 5;
	static final int SALLYPORT_INDEX = 6;
	static final int ROCKWALL_INDEX = 7;
	static final int LOW_SHOT_INDEX = 9;
	static final int HIGH_SHOT_INDEX = 10;
	static final int SHOT_INDEX = 9;
	static final int PARK_INDEX = 11;
	static final int SCALE_INDEX = 12;
	static final int CHALLENGE_INDEX = 10;
	static final int GENERAL_INDEX = 11;
	
	final Image lowBar = new Image(getClass().getResourceAsStream("LowBarImage.png"), 0, 125, true, true);
	final Image portCullis = new Image(getClass().getResourceAsStream("PortCullisImage.png"), 0, 125, true, true);
	final Image ramparts = new Image(getClass().getResourceAsStream("RampartsImage.png"), 0, 125, true, true);
	final Image cheval = new Image(getClass().getResourceAsStream("ChevalImage.png"), 0, 125, true, true);
	final Image moat = new Image(getClass().getResourceAsStream("MoatImage.png"), 0, 125, true, true);
	final Image drawbridge = new Image(getClass().getResourceAsStream("DrawbridgeImage.png"), 0, 125, true, true);
	final Image sallyPort = new Image(getClass().getResourceAsStream("SallyPortImage.png"), 0, 125, true, true);
	final Image rockWall = new Image(getClass().getResourceAsStream("RockWallImage.png"), 0, 125, true, true);
	final Image roughTerrain = new Image(getClass().getResourceAsStream("RoughTerrainImage.png"), 0, 125, true, true);
	
	private Team team;
	
	public Report(Team t){
		team = t;
		
		infoLabels = new Label[13];
		noteLabels = new Label[12];
		
		teamNumLab = new Label(team.getNumber() + "");
		teamNumLab.setFont(Font.font("Verdana", 50));
		teamNameLab = new Label(team.getName());
		teamNameLab.setFont(Font.font("Verdana", 50));
		
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
		
		another = new Button("Lookup Team");
		another.setFont(Font.font("Verdana", 25));
		another.setPrefSize(300, 150);
		GridPane.setConstraints(another, 5, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		another.setPadding(new Insets(0, 25, 0, 25));
		another.setTextAlignment(TextAlignment.CENTER);
		another.setAlignment(Pos.CENTER);
		another.setOnAction(e -> {
			WCRScout.reportSearch.update();
			WCRScout.window.setScene(WCRScout.reportSearch.getScene());
			WCRScout.window.centerOnScreen();
		});
		
		edit = new Button("Edit");
		edit.setFont(Font.font("Verdana", 25));
		edit.setPrefSize(300, 150);
		GridPane.setConstraints(edit, 5, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		edit.setPadding(new Insets(0, 25, 0, 25));
		edit.setTextAlignment(TextAlignment.CENTER);
		edit.setAlignment(Pos.CENTER);
		edit.setOnAction(e -> {
			WCRScout.scoutSheet.load(team);
			WCRScout.scoutSheet.setEditing(true);
			WCRScout.window.setScene(WCRScout.scoutSheet.getScene());
			WCRScout.window.centerOnScreen();
		});
		
		defenseLab = new Label("Defenses");
		defenseLab.setFont(Font.font("Verdana", 40));
		defenseLab.setId("defenseLabels");
		defenseLab.setAlignment(Pos.CENTER);
		aLabel = new Label("Category A");
		aLabel.setFont(Font.font("Verdana", 30));
		aLabel.setId("defenseLabels");
		aLabel.setAlignment(Pos.CENTER);
		bLabel = new Label("Category B");
		bLabel.setFont(Font.font("Verdana", 30));
		bLabel.setId("defenseLabels");
		bLabel.setAlignment(Pos.CENTER);
		cLabel = new Label("Category C");
		cLabel.setFont(Font.font("Verdana", 30));
		cLabel.setId("defenseLabels");
		cLabel.setAlignment(Pos.CENTER);
		dLabel = new Label("Category D");
		dLabel.setFont(Font.font("Verdana", 30));
		dLabel.setId("defenseLabels");
		dLabel.setAlignment(Pos.CENTER);
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
		if(team.getDefense(Team.LOWBAR_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[LOWBAR_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[LOWBAR_INDEX] = temp;
		}
		noteLabels[LOWBAR_INDEX] = new Label(team.getNotes(Team.LOWBAR_INDEX));
		noteLabels[LOWBAR_INDEX].setWrapText(true);
		noteLabels[LOWBAR_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[LOWBAR_INDEX].setPrefWidth(300);
		noteLabels[LOWBAR_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[LOWBAR_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[LOWBAR_INDEX], 0, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(infoLabels[LOWBAR_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		lowBarGrid.getChildren().addAll(infoLabels[LOWBAR_INDEX], lowBarView, noteLabels[LOWBAR_INDEX]);
		GridPane.setConstraints(lowBarGrid, 1, 1, 2, 4, HPos.CENTER, VPos.CENTER);
		lowBarGrid.setAlignment(Pos.CENTER_LEFT);
		
		portGrid = new GridPane();
		portView = new ImageView(portCullis);
		GridPane.setConstraints(portView, 0, 0);
		if(team.getDefense(Team.PORTCULLIS_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[PORTCULLIS_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[PORTCULLIS_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[PORTCULLIS_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[PORTCULLIS_INDEX] = new Label(team.getNotes(Team.PORTCULLIS_INDEX));
		noteLabels[PORTCULLIS_INDEX].setWrapText(true);
		noteLabels[PORTCULLIS_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[PORTCULLIS_INDEX].setPrefWidth(300);
		noteLabels[PORTCULLIS_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[PORTCULLIS_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[PORTCULLIS_INDEX], 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		portGrid.getChildren().addAll(portView, infoLabels[PORTCULLIS_INDEX], noteLabels[PORTCULLIS_INDEX]);
		GridPane.setConstraints(portGrid, 0, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		portGrid.setAlignment(Pos.CENTER);
		
		chevalGrid = new GridPane();
		chevalView = new ImageView(cheval);
		GridPane.setConstraints(chevalView, 0, 0);
		if(team.getDefense(Team.CHEVAL_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[CHEVAL_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[CHEVAL_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[CHEVAL_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[CHEVAL_INDEX] = new Label(team.getNotes(Team.CHEVAL_INDEX));
		noteLabels[CHEVAL_INDEX].setWrapText(true);
		noteLabels[CHEVAL_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[CHEVAL_INDEX].setPrefWidth(300);
		noteLabels[CHEVAL_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[CHEVAL_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[CHEVAL_INDEX], 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		chevalGrid.getChildren().addAll(chevalView, infoLabels[CHEVAL_INDEX], noteLabels[CHEVAL_INDEX]);
		GridPane.setConstraints(chevalGrid, 2, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		chevalGrid.setAlignment(Pos.CENTER);
		
		roughGrid = new GridPane();
		roughView = new ImageView(roughTerrain);
		GridPane.setConstraints(roughView, 0, 0);
		if(team.getDefense(Team.ROUGH_TERRAIN_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[ROUGH_TERRAIN_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[ROUGH_TERRAIN_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[ROUGH_TERRAIN_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[ROUGH_TERRAIN_INDEX] = new Label(team.getNotes(Team.ROUGH_TERRAIN_INDEX));
		noteLabels[ROUGH_TERRAIN_INDEX].setWrapText(true);
		noteLabels[ROUGH_TERRAIN_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[ROUGH_TERRAIN_INDEX].setPrefWidth(300);
		noteLabels[ROUGH_TERRAIN_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[ROUGH_TERRAIN_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[ROUGH_TERRAIN_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		roughGrid.getChildren().addAll(roughView, infoLabels[ROUGH_TERRAIN_INDEX], noteLabels[ROUGH_TERRAIN_INDEX]);
		GridPane.setConstraints(roughGrid, 2, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		roughGrid.setAlignment(Pos.CENTER);
		
		rampartGrid = new GridPane();
		rampartView = new ImageView(ramparts);
		GridPane.setConstraints(rampartView, 0, 0);
		if(team.getDefense(Team.RAMPARTS_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[RAMPART_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[RAMPART_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[RAMPART_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[RAMPART_INDEX] = new Label(team.getNotes(Team.RAMPARTS_INDEX));
		noteLabels[RAMPART_INDEX].setWrapText(true);
		noteLabels[RAMPART_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[RAMPART_INDEX].setPrefWidth(300);
		noteLabels[RAMPART_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[RAMPART_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[RAMPART_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		rampartGrid.getChildren().addAll(rampartView, noteLabels[RAMPART_INDEX], infoLabels[RAMPART_INDEX]);
		GridPane.setConstraints(rampartGrid, 0, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		rampartGrid.setAlignment(Pos.CENTER);
		
		moatGrid = new GridPane();
		moatView = new ImageView(moat);
		GridPane.setConstraints(moatView, 0, 0);
		if(team.getDefense(Team.MOAT_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[MOAT_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[MOAT_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[MOAT_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[MOAT_INDEX] = new Label(team.getNotes(Team.MOAT_INDEX));
		noteLabels[MOAT_INDEX].setWrapText(true);
		noteLabels[MOAT_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[MOAT_INDEX].setPrefWidth(300);
		noteLabels[MOAT_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[MOAT_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[MOAT_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		moatGrid.getChildren().addAll(moatView, infoLabels[MOAT_INDEX], noteLabels[MOAT_INDEX]);
		GridPane.setConstraints(moatGrid, 2, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		moatGrid.setAlignment(Pos.CENTER);
		
		drawGrid = new GridPane();
		drawView = new ImageView(drawbridge);
		GridPane.setConstraints(drawView, 0, 0);
		if(team.getDefense(Team.DRAWBRIDGE_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[DRAWBRIDGE_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[DRAWBRIDGE_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[DRAWBRIDGE_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[DRAWBRIDGE_INDEX] = new Label(team.getNotes(Team.DRAWBRIDGE_INDEX));
		noteLabels[DRAWBRIDGE_INDEX].setWrapText(true);
		noteLabels[DRAWBRIDGE_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[DRAWBRIDGE_INDEX].setPrefWidth(300);
		noteLabels[DRAWBRIDGE_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[DRAWBRIDGE_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[DRAWBRIDGE_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		drawGrid.getChildren().addAll(drawView, infoLabels[DRAWBRIDGE_INDEX], noteLabels[DRAWBRIDGE_INDEX]);
		GridPane.setConstraints(drawGrid, 0, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		drawGrid.setAlignment(Pos.CENTER);
		
		sallyGrid = new GridPane();
		sallyView = new ImageView(sallyPort);
		GridPane.setConstraints(sallyView, 0, 0);
		if(team.getDefense(Team.SALLY_PORT_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[SALLYPORT_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[SALLYPORT_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[SALLYPORT_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[SALLYPORT_INDEX] = new Label(team.getNotes(Team.SALLY_PORT_INDEX));
		noteLabels[SALLYPORT_INDEX].setWrapText(true);
		noteLabels[SALLYPORT_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[SALLYPORT_INDEX].setPrefWidth(300);
		noteLabels[SALLYPORT_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[SALLYPORT_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[SALLYPORT_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		sallyGrid.getChildren().addAll(sallyView, infoLabels[SALLYPORT_INDEX], noteLabels[SALLYPORT_INDEX]);
		GridPane.setConstraints(sallyGrid, 2, 15, 2, 3, HPos.CENTER, VPos.CENTER);
		sallyGrid.setAlignment(Pos.CENTER);
		
		rockGrid = new GridPane();
		rockView = new ImageView(rockWall);
		GridPane.setConstraints(rockView, 0, 0);
		if(team.getDefense(Team.ROCKWALL_INDEX)){
			Label temp = new Label("Y");
			temp.setId("yesLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[ROCKWALL_INDEX] = temp;
		}
		else{
			Label temp = new Label("N");
			temp.setId("noLab");
			temp.setPrefSize(WIDTH, 125);
			temp.setAlignment(Pos.CENTER);
			infoLabels[ROCKWALL_INDEX] = temp;
		}
		GridPane.setConstraints(infoLabels[ROCKWALL_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[ROCKWALL_INDEX] = new Label(team.getNotes(Team.ROCKWALL_INDEX));
		noteLabels[ROCKWALL_INDEX].setWrapText(true);
		noteLabels[ROCKWALL_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[ROCKWALL_INDEX].setPrefWidth(300);
		noteLabels[ROCKWALL_INDEX].setPadding(new Insets(10,0,0,0));
		noteLabels[ROCKWALL_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[ROCKWALL_INDEX], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		rockGrid.getChildren().addAll(rockView, infoLabels[ROCKWALL_INDEX], noteLabels[ROCKWALL_INDEX]);
		GridPane.setConstraints(rockGrid, 0, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		rockGrid.setAlignment(Pos.CENTER);
		
		shotGrid = new GridPane();
		shot = new Label("Shot:");
		shot.setFont(Font.font("Verdana", 30));
		shot.setPadding(new Insets(0, 30, 0, 50));
		GridPane.setConstraints(shot, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		Label temp = new Label("High");
		temp.setPrefSize(WIDTH, 100);
		temp.setAlignment(Pos.CENTER);
		if(team.getDefense(Team.HIGH_GOAL_INDEX))
			temp.setId("yesLab");
		else
			temp.setId("noLab");
		infoLabels[HIGH_SHOT_INDEX] = temp;
		Label temp2 = new Label("Low");
		temp2.setPrefSize(WIDTH, 100);
		temp2.setAlignment(Pos.CENTER);
		if(team.getDefense(Team.LOW_GOAL_INDEX))
			temp2.setId("yesLab");
		else
			temp2.setId("noLab");
		infoLabels[LOW_SHOT_INDEX] = temp2;
		GridPane.setConstraints(infoLabels[10], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(infoLabels[9], 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[SHOT_INDEX] = new Label(team.getNotes(Team.SHOT_INDEX));
		noteLabels[SHOT_INDEX].setWrapText(true);
		noteLabels[SHOT_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[SHOT_INDEX].setPrefWidth(300);
		noteLabels[SHOT_INDEX].setPadding(new Insets(20,0,0,0));
		noteLabels[SHOT_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[9], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.getChildren().addAll(shot, infoLabels[9], infoLabels[10], noteLabels[SHOT_INDEX]);
		GridPane.setConstraints(shotGrid, 5, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.setPadding(new Insets(0, 0, 0, 50));
		shotGrid.setAlignment(Pos.CENTER);
		
		challengeGrid = new GridPane();
		challenge = new Label("Challenge:");
		challenge.setFont(Font.font("Verdana", 30));
		GridPane.setConstraints(challenge, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		Label temp3 = new Label("Park");
		temp3.setPrefSize(WIDTH, 100);
		temp3.setAlignment(Pos.CENTER);
		if(team.getDefense(Team.PARK_INDEX))
			temp3.setId("yesLab");
		else
			temp3.setId("noLab");
		infoLabels[PARK_INDEX] = temp3;
		Label temp4 = new Label("Scale");
		temp4.setPrefSize(WIDTH, 100);
		temp4.setAlignment(Pos.CENTER);
		if(team.getDefense(Team.SCALE_INDEX))
			temp4.setId("yesLab");
		else
			temp4.setId("noLab");
		infoLabels[SCALE_INDEX] = temp4;
		GridPane.setConstraints(infoLabels[11], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(infoLabels[12], 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		noteLabels[CHALLENGE_INDEX] = new Label(team.getNotes(Team.CHALLENGE_INDEX));
		noteLabels[CHALLENGE_INDEX].setWrapText(true);
		noteLabels[CHALLENGE_INDEX].setFont(Font.font("Verdana", 15));
		noteLabels[CHALLENGE_INDEX].setPrefWidth(300);
		noteLabels[CHALLENGE_INDEX].setPadding(new Insets(20,0,0,0));
		noteLabels[CHALLENGE_INDEX].setAlignment(Pos.CENTER);
		GridPane.setConstraints(noteLabels[10], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.getChildren().addAll(challenge, infoLabels[11], infoLabels[12], noteLabels[10]);
		GridPane.setConstraints(challengeGrid, 5, 5, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.setPadding(new Insets(0, 0, 0, 50));
		challengeGrid.setAlignment(Pos.CENTER);
		
		general = new Label("General Notes");
		general.setFont(Font.font("Verdana", 35));
		GridPane.setConstraints(general, 5, 9, 2, 1, HPos.CENTER, VPos.CENTER);
		
		noteLabels[GENERAL_INDEX] = new Label(team.getNotes(Team.GENERAL_NOTES_INDEX));
		noteLabels[GENERAL_INDEX].setPrefSize(300, 500);
		noteLabels[GENERAL_INDEX].setWrapText(true);
		noteLabels[GENERAL_INDEX].setFont(Font.font("Verdana", 20));
		noteLabels[GENERAL_INDEX].setAlignment(Pos.TOP_CENTER);
		noteLabels[GENERAL_INDEX].setBorder(new Border(new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
		GridPane.setConstraints(noteLabels[GENERAL_INDEX], 5, 10, 3, 2, HPos.CENTER, VPos.TOP);
		
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
		grid.setAlignment(Pos.CENTER);
		grid.getChildren().addAll(defenseLab, shotGrid, edit, general, another, noteLabels[GENERAL_INDEX], challengeGrid, capabilities, lowBarGrid, rockGrid, dLabel, moatGrid, aLabel, bLabel, cLabel, drawGrid, portGrid, chevalGrid, roughGrid, rampartGrid, sallyGrid);
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
