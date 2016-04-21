import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FieldDraw{
	private Scene scene;
	private Canvas canvas;
	private Button backButton, clearButton, blackBut, redBut, blueBut, greenBut, whiteBut, obstacleBut, rightObstacleBut, clearObstacleBut, undo;
	private ArrayList<Button> colorButtons;
	private ArrayList<Obstacle> leftObstacles, rightObstacles;
	private Label colorLabel;
	private Image backImage, blueBotImage;
	private HBox top, bot;
	private GraphicsContext gc;
	private Obstacle leftLowBar, rightLowBar;
	private ArrayList<Integer> xpoints, ypoints;
	private Stack<ArrayList<Integer>> allXPoints;
	private Stack<ArrayList<Integer>> allYPoints;
	private Stack<Color> lineColors;
	
	public static final int LEFT_POS_X = 449;
	public static final int RIGHT_POS_X = 761;
	public static final int RIGHT_SIDE_LENGTH = 383;
	public static final int LEFT_POS_ONE_Y = 482;
	public static final int LEFT_POS_FIVE_Y = 171;
	public static final int OBSTACLE_BACK_WIDTH = 68;
	public static final int OBSTACLE_BACK_HEIGHT = 78;
	
	public FieldDraw(){
		canvas = new Canvas(1280, 800);
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		xpoints = new ArrayList<Integer>();
		ypoints = new ArrayList<Integer>();
		allXPoints = new Stack<ArrayList<Integer>>();
		allYPoints = new Stack<ArrayList<Integer>>();
		lineColors = new Stack<Color>();
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				lineColors.push((Color)gc.getStroke());
				gc.beginPath();
				double x = event.getX();
				double y = event.getY();
				gc.moveTo(x, y);
				xpoints.add((int)x);
				ypoints.add((int)y);
				gc.stroke();
			}
		});
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				double x = event.getX();
				double y = event.getY();
				gc.lineTo(x, y);
				xpoints.add((int)x);
				ypoints.add((int)y);
				gc.stroke();
			}
		});
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				allXPoints.push(xpoints);
				allYPoints.push(ypoints);
				xpoints = new ArrayList<Integer>();
				ypoints = new ArrayList<Integer>();
			}
		});
		
		leftObstacles = new ArrayList<Obstacle>();
		rightObstacles = new ArrayList<Obstacle>();
		
		drawObstacleRects();
		
		leftLowBar = new Obstacle("Low Bar", 1, 0);
		rightLowBar = new Obstacle("Low Bar", 1, 1);
		
		undo = new Button("Undo");
		undo.setFont(Font.font("Verdana", 20));
		undo.setPrefSize(100, 38);
		undo.setOnAction(e -> {
			undoMark();
		});
		
		obstacleBut = new Button("Edit Left");
		obstacleBut.setFont(Font.font("Verdana", 20));
		obstacleBut.setPrefSize(150, 38);
		obstacleBut.setOnAction(e -> {
			ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
			for(Obstacle o:leftObstacles){
				temp.add(o);
			}
			leftObstacles.clear();
			clearCanvas(false);
			leftObstacles = ObstacleChooser.display("Left Side", temp);
			clearCanvas(false);
		});
		
		rightObstacleBut = new Button("Edit Right");
		rightObstacleBut.setFont(Font.font("Verdana", 20));
		rightObstacleBut.setPrefSize(150, 38);
		rightObstacleBut.setOnAction(e -> {
			ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
			for(Obstacle o:rightObstacles){
				temp.add(o);
			}
			rightObstacles.clear();
			clearCanvas(false);
			rightObstacles = ObstacleChooser.display("Right Side", temp);
			clearCanvas(false);
		});
		
		backButton = new Button();
		backImage = new Image(getClass().getResourceAsStream("back arrow.png"));
		BackgroundImage back = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(30, 30, true, true, true, false));
		BackgroundFill fill = new BackgroundFill(Color.MAROON, null, null);
		BackgroundImage[] backArr = new BackgroundImage[1];
		BackgroundFill[] fillArr = new BackgroundFill[1];
		backArr[0] = back;
		fillArr[0] = fill;
		backButton.setBackground(new Background(fillArr, backArr));
		backButton.setOnAction(e -> {
			WCRScout.window.setScene(WCRScout.mainMenu.getScene());
			WCRScout.window.centerOnScreen();
		});
		backButton.setPrefSize(38, 38);
		
		clearButton = new Button("Clear Marks");
		clearButton.setFont(Font.font("Verdana", 20));
		clearButton.setOnAction(e -> {
			clearCanvas(true);
		});
		clearButton.setPrefSize(150, 38);
		
		clearObstacleBut = new Button("Clear Obstacles");
		clearObstacleBut.setFont(Font.font("Verdana", 20));
		clearObstacleBut.setPrefSize(185, 38);
		clearObstacleBut.setOnAction(e -> {
			leftObstacles.clear();
			rightObstacles.clear();
			clearCanvas(false);
		});
		
		colorLabel = new Label("Select Color:");
		colorLabel.setAlignment(Pos.CENTER);
		colorLabel.setPrefSize(150, 38);
		colorLabel.setFont(Font.font("Verdana", 20));
		
		colorButtons = new ArrayList<Button>();
		
		blackBut = new Button();
		blackBut.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		blackBut.setPrefSize(38, 38);
		setSelectedColorButton(blackBut);
		blackBut.setOnAction(e -> {
			gc.setStroke(Color.BLACK);
			setSelectedColorButton(blackBut);
		});
		
		redBut = new Button();
		redBut.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		redBut.setPrefSize(38, 38);
		redBut.setOnAction(e -> {
			gc.setStroke(Color.RED);
			setSelectedColorButton(redBut);
		});
		
		blueBut = new Button();
		blueBut.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		blueBut.setPrefSize(38, 38);
		blueBut.setOnAction(e -> {
			gc.setStroke(Color.BLUE);
			setSelectedColorButton(blueBut);
		});
		
		greenBut = new Button();
		greenBut.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		greenBut.setPrefSize(38, 38);
		greenBut.setOnAction(e -> {
			gc.setStroke(Color.GREEN);
			setSelectedColorButton(greenBut);
		});
		
		whiteBut = new Button();
		whiteBut.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
		whiteBut.setPrefSize(38, 38);
		whiteBut.setOnAction(e -> {
			gc.setStroke(Color.WHITE);
			setSelectedColorButton(whiteBut);
		});
		
		colorButtons.add(blackBut);
		colorButtons.add(redBut);
		colorButtons.add(blueBut);
		colorButtons.add(greenBut);
		colorButtons.add(whiteBut);
		
		blueBotImage = new Image(getClass().getResourceAsStream("BlueBot.png"));
		ImageView viewer = new ImageView(blueBotImage);
		
		top = new HBox(5);
		top.getChildren().addAll(backButton, colorLabel, blackBut, redBut, blueBut, greenBut, whiteBut, clearButton, undo, obstacleBut, rightObstacleBut, clearObstacleBut);
		top.setAlignment(Pos.CENTER);
		
		bot = new HBox(5);
		bot.setAlignment(Pos.CENTER);
		/*
		bot.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				Dragboard db = bot.startDragAndDrop(TransferMode.MOVE);
				
				ClipboardContent clip = new ClipboardContent();
				clip.putImage(blueBotImage);
				db.setContent(clip);
			}
		});
		*/
		bot.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		bot.setPrefSize(1280, 50);
		bot.getChildren().addAll(viewer);
		
		BorderPane root = new BorderPane();
		Image field = new Image(getClass().getResourceAsStream("Stronghold Good Field.PNG"));
		BackgroundImage fieldImage = new BackgroundImage(field, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		root.setBackground(new Background(fieldImage));
		root.setCenter(canvas);
		root.setTop(top);
		root.setBottom(bot);
		root.setPadding(new Insets(10,0,0,0));
		scene = new Scene(root, 1280, 750);
		drawAllObstacles();
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public void setSelectedColorButton(Button b){
		b.setBorder(new Border(new BorderStroke(Color.DARKORANGE, BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
		for(Button but:colorButtons)
			if(!but.equals(b)){
				if(!but.equals(whiteBut))
					but.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.NONE, null, null)));
				else
					but.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
			}		
	}
	
	public void setSelectedColorButton(Color c){
		for(Button b:colorButtons){
			if(((Color)b.getBackground().getFills().get(0).getFill()).equals(c)){
				setSelectedColorButton(b);
			}
		}
		gc.setStroke(c);
	}
	
	public void drawAllObstacles(){
		for(Obstacle o:leftObstacles){
			gc.drawImage(o.getImage(), o.getX(), o.getY());
		}
		for(Obstacle o:rightObstacles){
			gc.drawImage(o.getImage(), o.getX(), o.getY());
		}
		gc.drawImage(leftLowBar.getImage(), leftLowBar.getX(), leftLowBar.getY());
		gc.drawImage(rightLowBar.getImage(), rightLowBar.getX(), rightLowBar.getY());
	}
	
	public void drawObstacleRects(){
		gc.setFill(Color.WHITE);
		gc.fillRect(LEFT_POS_X, LEFT_POS_FIVE_Y, OBSTACLE_BACK_WIDTH, LEFT_POS_ONE_Y-LEFT_POS_FIVE_Y+74);
		gc.fillRect(RIGHT_POS_X, 96, OBSTACLE_BACK_WIDTH+1, 383);
	}
	
	public void clearCanvas(boolean all){
		if(all){
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			drawObstacleRects();
			drawAllObstacles();
			allXPoints.clear();
			allYPoints.clear();
		}
		else{
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			redrawLines();
		}
	}
	
	public void undoMark(){
		Color popped = Color.BLACK;
		if(!allXPoints.isEmpty()){
			allXPoints.pop();
			allYPoints.pop();
			redrawLines();
			popped = lineColors.pop();
		}
		if(lineColors.isEmpty())
			setSelectedColorButton(popped);
		else
			setSelectedColorButton(lineColors.peek());
	}
	
	public void redrawLines(){
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		drawObstacleRects();
		drawAllObstacles();
		for(int i = 0; i<allXPoints.size(); i++){
			for(int k = 0; k<allXPoints.get(i).size(); k++){
				if(k == 0){
					gc.setStroke(lineColors.get(i));
					gc.beginPath();
					gc.moveTo(allXPoints.get(i).get(k), allYPoints.get(i).get(k));
					gc.stroke();
				}
				else{
					gc.lineTo(allXPoints.get(i).get(k), allYPoints.get(i).get(k));
					gc.stroke();
				}	
			}
		}
	}
}
