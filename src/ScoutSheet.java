import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
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

public class ScoutSheet {
	private Scene scene;
	private ScrollPane scroller;
	private HBox top;
	private GridPane grid, lowBarGrid, portGrid, chevalGrid, roughGrid, rampartGrid, moatGrid, drawGrid, sallyGrid, rockGrid, shotGrid, challengeGrid;
	private BorderPane border;
	private Label teamNumLab, teamNameLab, colorLab, defenseLab, aLabel, bLabel, cLabel, dLabel, capabilities, shot, challenge, general;
	private TextField[] textFields;
	private ImageView lowBarView, portView, chevalView, roughView, rampartView, moatView, drawView, sallyView, rockView;
	private CheckBox[] checkBoxes;
	private TextArea[] textAreas;
	private Button clear, save, backButton;
	private Image backImage;
	private ComboBox<String> colorPicker;
	
	private ObservableList<String> choices = FXCollections.observableArrayList("Blue", "Orange", "Green", "Red", "Maroon", "Yellow");
	final int BLUE_INDEX = 0;
	final int ORANGE_INDEX = 1;
	final int GREEN_INDEX = 2;
	final int RED_INDEX = 3;
	final int MAROON_INDEX = 4;
	final int YELLOW_INDEX = 5;
	
	private boolean editing;
	private String oldName;
	
	static final int NUMBER_INDEX = 0;
	static final int NAME_INDEX = 1;
	static final int LOWBAR_INDEX = 0;
	static final int PORTCULLIS_INDEX = 1;
	static final int RAMPART_INDEX = 2;	
	static final int CHEVAL_INDEX = 3;
	static final int MOAT_INDEX = 4;
	static final int DRAWBRIDGE_INDEX = 5;
	static final int SALLYPORT_INDEX = 6;
	static final int ROCKWALL_INDEX = 7;	
	static final int ROUGH_TERRAIN_INDEX = 8;
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
	
	public ScoutSheet(){
		textFields = new TextField[2];
		checkBoxes = new CheckBox[13];
		textAreas = new TextArea[12];
		
		editing = false;
		
		colorPicker = new ComboBox<String>();
		colorPicker.setItems(choices);
		colorPicker.setId("generalRep");
		colorPicker.setPrefSize(200, 50);
		GridPane.setConstraints(colorPicker, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		colorLab = new Label("Team Color");
		colorLab.setId("defenseLabels");
		colorLab.setFont(Font.font("Verdana", 20));
		GridPane.setConstraints(colorLab, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		teamNumLab = new Label("Team Number:");
		teamNumLab.setFont(Font.font("Verdana", 30));
		textFields[0] = new TextField();
		textFields[0].setFont(Font.font("Verdana", 30));
		textFields[0].setPrefWidth(120);
		textFields[0].getProperties().put("vkType", "numeric");
		teamNameLab = new Label("Team Name:");
		teamNameLab.setFont(Font.font("Verdana", 30));
		textFields[1] = new TextField();
		textFields[1].setFont(Font.font("Verdana", 30));
		textFields[1].setPrefWidth(300);
		
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
			clear();
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
			clear();
		});
		clear.setAlignment(Pos.CENTER);
		
		save = new Button("Save");
		save.setFont(Font.font("Verdana", 30));
		save.setPrefSize(200, 150);
		GridPane.setConstraints(save, 5, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		save.setPadding(new Insets(0, 25, 0, 25));
		save.setTextAlignment(TextAlignment.CENTER);
		save.setOnAction(e -> {
			save();
		});
		save.setAlignment(Pos.CENTER);
		
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
		checkBoxes[LOWBAR_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[LOWBAR_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[LOWBAR_INDEX] = new TextArea();
		textAreas[LOWBAR_INDEX].setPrefSize(0, 100);
		textAreas[LOWBAR_INDEX].setWrapText(true);
		GridPane.setConstraints(textAreas[LOWBAR_INDEX], 0, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		lowBarGrid.getChildren().addAll(checkBoxes[LOWBAR_INDEX], lowBarView, textAreas[LOWBAR_INDEX]);
		GridPane.setConstraints(lowBarGrid, 0, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		lowBarGrid.setAlignment(Pos.CENTER_LEFT);
		
		portGrid = new GridPane();
		portView = new ImageView(portCullis);
		GridPane.setConstraints(portView, 0, 0);
		checkBoxes[PORTCULLIS_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[PORTCULLIS_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[PORTCULLIS_INDEX] = new TextArea();
		textAreas[PORTCULLIS_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[PORTCULLIS_INDEX], 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		portGrid.getChildren().addAll(portView, checkBoxes[PORTCULLIS_INDEX], textAreas[PORTCULLIS_INDEX]);
		GridPane.setConstraints(portGrid, 0, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		portGrid.setAlignment(Pos.CENTER);
		
		chevalGrid = new GridPane();
		chevalView = new ImageView(cheval);
		GridPane.setConstraints(chevalView, 0, 0);
		checkBoxes[CHEVAL_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[CHEVAL_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[CHEVAL_INDEX] = new TextArea();
		textAreas[CHEVAL_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[CHEVAL_INDEX], 0, 1, 2, 2, HPos.LEFT, VPos.CENTER);
		chevalGrid.getChildren().addAll(chevalView, checkBoxes[CHEVAL_INDEX], textAreas[CHEVAL_INDEX]);
		GridPane.setConstraints(chevalGrid, 2, 6, 2, 3, HPos.CENTER, VPos.CENTER);
		chevalGrid.setAlignment(Pos.CENTER);
		
		roughGrid = new GridPane();
		roughView = new ImageView(roughTerrain);
		GridPane.setConstraints(roughView, 0, 0);
		checkBoxes[ROUGH_TERRAIN_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[ROUGH_TERRAIN_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[ROUGH_TERRAIN_INDEX] = new TextArea();
		textAreas[ROUGH_TERRAIN_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[ROUGH_TERRAIN_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		roughGrid.getChildren().addAll(roughView, checkBoxes[ROUGH_TERRAIN_INDEX], textAreas[ROUGH_TERRAIN_INDEX]);
		GridPane.setConstraints(roughGrid, 2, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		roughGrid.setAlignment(Pos.CENTER);
		
		rampartGrid = new GridPane();
		rampartView = new ImageView(ramparts);
		GridPane.setConstraints(rampartView, 0, 0);
		checkBoxes[RAMPART_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[RAMPART_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[RAMPART_INDEX] = new TextArea();
		textAreas[RAMPART_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[RAMPART_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		rampartGrid.getChildren().addAll(rampartView, textAreas[RAMPART_INDEX], checkBoxes[RAMPART_INDEX]);
		GridPane.setConstraints(rampartGrid, 0, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		rampartGrid.setAlignment(Pos.CENTER);
		
		moatGrid = new GridPane();
		moatView = new ImageView(moat);
		GridPane.setConstraints(moatView, 0, 0);
		checkBoxes[MOAT_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[MOAT_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[MOAT_INDEX] = new TextArea();
		textAreas[MOAT_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[MOAT_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		moatGrid.getChildren().addAll(moatView, checkBoxes[MOAT_INDEX], textAreas[MOAT_INDEX]);
		GridPane.setConstraints(moatGrid, 2, 10, 2, 3, HPos.CENTER, VPos.CENTER);
		moatGrid.setAlignment(Pos.CENTER);
		
		drawGrid = new GridPane();
		drawView = new ImageView(drawbridge);
		GridPane.setConstraints(drawView, 0, 0);
		checkBoxes[DRAWBRIDGE_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[DRAWBRIDGE_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[DRAWBRIDGE_INDEX] = new TextArea();
		textAreas[DRAWBRIDGE_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[DRAWBRIDGE_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		drawGrid.getChildren().addAll(drawView, checkBoxes[DRAWBRIDGE_INDEX], textAreas[DRAWBRIDGE_INDEX]);
		GridPane.setConstraints(drawGrid, 0, 15, 2, 4, HPos.CENTER, VPos.CENTER);
		drawGrid.setAlignment(Pos.CENTER);
		
		sallyGrid = new GridPane();
		sallyView = new ImageView(sallyPort);
		GridPane.setConstraints(sallyView, 0, 0);
		checkBoxes[SALLYPORT_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[SALLYPORT_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[SALLYPORT_INDEX] = new TextArea();
		textAreas[SALLYPORT_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[SALLYPORT_INDEX], 0, 2, 2, 2, HPos.LEFT, VPos.CENTER);
		sallyGrid.getChildren().addAll(sallyView, checkBoxes[SALLYPORT_INDEX], textAreas[SALLYPORT_INDEX]);
		GridPane.setConstraints(sallyGrid, 2, 15, 2, 4
				, HPos.CENTER, VPos.CENTER);
		sallyGrid.setAlignment(Pos.CENTER);
		
		rockGrid = new GridPane();
		rockView = new ImageView(rockWall);
		GridPane.setConstraints(rockView, 0, 0);
		checkBoxes[ROCKWALL_INDEX] = new CheckBox("Y/N");
		GridPane.setConstraints(checkBoxes[ROCKWALL_INDEX], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[ROCKWALL_INDEX] = new TextArea();
		textAreas[ROCKWALL_INDEX].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[ROCKWALL_INDEX], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		rockGrid.getChildren().addAll(rockView, checkBoxes[ROCKWALL_INDEX], textAreas[ROCKWALL_INDEX]);
		GridPane.setConstraints(rockGrid, 0, 20, 2, 3, HPos.CENTER, VPos.CENTER);
		rockGrid.setAlignment(Pos.CENTER);
		
		shotGrid = new GridPane();
		shot = new Label("Shot:");
		shot.setFont(Font.font("Verdana", 35));
		shot.setPadding(new Insets(0, 0, 0, 50));
		GridPane.setConstraints(shot, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		checkBoxes[9] = new CheckBox("High");
		checkBoxes[9].setId("capLabel");
		checkBoxes[10] = new CheckBox("Low ");
		checkBoxes[10].setId("capLabel");
		GridPane.setConstraints(checkBoxes[9], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(checkBoxes[10], 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[9] = new TextArea();
		textAreas[9].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[9], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.getChildren().addAll(shot, checkBoxes[9], checkBoxes[10], textAreas[9]);
		GridPane.setConstraints(shotGrid, 5, 1, 2, 2, HPos.CENTER, VPos.CENTER);
		shotGrid.setPadding(new Insets(0, 0, 0, 50));
		shotGrid.setAlignment(Pos.CENTER);
		
		challengeGrid = new GridPane();
		challenge = new Label("Challenge:");
		challenge.setFont(Font.font("Verdana", 35));
		GridPane.setConstraints(challenge, 0, 0, 1, 2, HPos.CENTER, VPos.CENTER);
		checkBoxes[11] = new CheckBox("Park ");
		checkBoxes[11].setId("capLabel");
		checkBoxes[12] = new CheckBox("Scale");
		checkBoxes[12].setId("capLabel");
		GridPane.setConstraints(checkBoxes[11], 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(checkBoxes[12], 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		textAreas[10] = new TextArea();
		textAreas[10].setPrefSize(100, 100);
		GridPane.setConstraints(textAreas[10], 0, 2, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.getChildren().addAll(challenge, checkBoxes[11], checkBoxes[12], textAreas[10]);
		GridPane.setConstraints(challengeGrid, 5, 5, 2, 2, HPos.CENTER, VPos.CENTER);
		challengeGrid.setPadding(new Insets(0, 0, 0, 50));
		challengeGrid.setAlignment(Pos.CENTER);
		
		general = new Label("General Notes");
		general.setFont(Font.font("Verdana", 30));
		GridPane.setConstraints(general, 5, 9, 2, 1, HPos.CENTER, VPos.CENTER);
		
		textAreas[11] = new TextArea();
		textAreas[11].setPrefSize(200, 400);
		GridPane.setConstraints(textAreas[11], 5, 10, 3, 2, HPos.CENTER, VPos.CENTER);
		
		top = new HBox(10);
		top.setPadding(new Insets(0, 0, 0, 0));
		top.getChildren().addAll(backButton, teamNumLab, textFields[0], teamNameLab, textFields[1]);
		top.setAlignment(Pos.CENTER);
		top.setBorder(new Border(new BorderStroke(Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.DASHED, BorderStrokeStyle.NONE, null, BorderStroke.THICK, null)));
		top.setPrefHeight(100);
		
		teamNumLab.setPadding(new Insets(0, 0, 0, 50));
		teamNameLab.setPadding(new Insets(0, 0, 0, 50));
		
		grid = new GridPane();
		grid.setPadding(new Insets(20,50,50,50));
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.getChildren().addAll(defenseLab, colorPicker, colorLab, save, shotGrid, clear, general, textAreas[11], challengeGrid, capabilities, lowBarGrid, rockGrid, dLabel, moatGrid, aLabel, bLabel, cLabel, drawGrid, portGrid, chevalGrid, roughGrid, rampartGrid, sallyGrid);
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
		border.setBorder(new Border(new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
		border.setTop(top);
		border.setCenter(grid);
		scroller = new ScrollPane();
		scroller.setContent(border);
		scroller.setFitToHeight(true);
		scroller.setFitToWidth(true);
		scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroller.setOnScroll(e -> {
			double deltaY = e.getDeltaY()*5;
			System.out.println(deltaY);
			double height = scroller.getContent().getBoundsInLocal().getHeight();
			double vValue = scroller.getVvalue();
			scroller.setVvalue(vValue + -deltaY/height);
		});
		scene = new Scene(scroller, 1280, 750);
		scene.getStylesheets().add(WCRScout.class.getResource("testC.css").toExternalForm());
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public void clear(){
		for (int i = 0; i < textFields.length; i++){
			textFields[i].clear();
		}
		
		for (int i = 0; i < checkBoxes.length; i++){
			checkBoxes[i].setSelected(false);
		}
		
		for (int i = 0; i < textAreas.length; i++){
			textAreas[i].clear();
		}
		setEditing(false);
		scroller.setVvalue(0);
		colorPicker.setValue("");
	}
	
	public void save(){
		if(editing){
			WCRScout.data.remove(oldName);
		}
		boolean inUse = false;
		String name = textFields[NAME_INDEX].getText();
		int number = 0;
		String color = "";
		if(colorPicker.getValue() != null)
			color = colorPicker.getValue();
		if(!textFields[NUMBER_INDEX].getText().equals(""))
			number = Integer.parseInt(textFields[NUMBER_INDEX].getText());
		if(!name.equals("")&&number!=0){
			for(Team t:WCRScout.data.getArray()){
				if(name.equals(t.getName())||number==t.getNumber())
					inUse = true;
			}
			if(!inUse){
				boolean[] information = new boolean[13];
				String[] notes = new String[12];
				
				for(int i = 0; i<information.length; i++){
					information[i] = checkBoxes[i].isSelected();
				}
				for(int i = 0; i<notes.length; i++){
					notes[i] = textAreas[i].getText();
				}
				Team t = new Team(name, number, information, notes, color);
				WCRScout.data.add(t);
				System.out.println(color);
				clear();
				WCRScout.window.setScene(WCRScout.mainMenu.getScene());
			}
			else{
				int answer = JOptionPane.showConfirmDialog(null, "Another team with the same number or name already exists.\nWould you like to overwrite?", "Duplicate", JOptionPane.YES_NO_OPTION);
				if(answer==0){
					boolean[] information = new boolean[13];
					String[] notes = new String[12];
					
					for(int i = 0; i<information.length; i++){
						information[i] = checkBoxes[i].isSelected();
					}
					for(int i = 0; i<notes.length; i++){
						notes[i] = textAreas[i].getText();
					}
					WCRScout.data.remove(number);
					Team t = new Team(name, number, information, notes, color);
					WCRScout.data.add(t);
					clear();
					WCRScout.window.setScene(WCRScout.mainMenu.getScene());
				}
				else{
					scroller.setVvalue(0);
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please enter a team name and number before saving.", "Missing Info", JOptionPane.ERROR_MESSAGE);
			scroller.setVvalue(0);
		}
			
	}
	
	public void load(Team t){
		textFields[NAME_INDEX].setText(t.getName());
		oldName = t.getName();
		textFields[NUMBER_INDEX].setText(t.getNumber() + "");
		String co = t.getColor();
		switch(co){
			case "Blue":
				colorPicker.getSelectionModel().select(BLUE_INDEX);
				break;
			case "Orange":
				colorPicker.getSelectionModel().select(ORANGE_INDEX);
				break;
			case "Green":
				colorPicker.getSelectionModel().select(GREEN_INDEX);
				break;
			case "Red":
				colorPicker.getSelectionModel().select(RED_INDEX);
				break;
			case "Maroon":
				colorPicker.getSelectionModel().select(MAROON_INDEX);
				break;
			case "Yellow":
				colorPicker.getSelectionModel().select(YELLOW_INDEX);
				break;
		}
		
		for(int i = 0; i<checkBoxes.length; i++){
			checkBoxes[i].setSelected(t.getDefense(i));
		}
		for(int i = 0; i<textAreas.length; i++){
			textAreas[i].setText(t.getNotes(i));
		}
	}
	
	public void setEditing(boolean b){
		editing = b;
	}
}
