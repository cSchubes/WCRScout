import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	private Button backButton, clearButton, blackBut, redBut, blueBut, greenBut, whiteBut;
	private ArrayList<Button> colorButtons;
	private Label colorLabel;
	private Image backImage;
	private HBox top;
	
	public FieldDraw(){
		canvas = new Canvas(1280, 800);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				gc.beginPath();
				gc.moveTo(event.getX(), event.getY());
				gc.stroke();
			}
		});
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				gc.lineTo(event.getX(), event.getY());
				gc.stroke();
			}
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
		
		clearButton = new Button("Clear");
		clearButton.setFont(Font.font("Verdana", 20));
		clearButton.setOnAction(e -> {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		});
		clearButton.setPrefSize(100, 38);
		
		colorLabel = new Label("Select Color:");
		colorLabel.setAlignment(Pos.CENTER);
		colorLabel.setPrefSize(150, 38);
		colorLabel.setFont(Font.font("Verdana", 20));
		
		colorButtons = new ArrayList<Button>();
		
		blackBut = new Button();
		blackBut.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		blackBut.setPrefSize(38, 38);
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
		
		top = new HBox(5);
		//top.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, null, BorderStroke.THIN)));
		top.getChildren().addAll(backButton, colorLabel, blackBut, redBut, blueBut, greenBut, whiteBut, clearButton);
		top.setAlignment(Pos.CENTER);
		
		BorderPane root = new BorderPane();
		Image field = new Image(getClass().getResourceAsStream("Stronghold Good Field.png"));
		BackgroundImage fieldImage = new BackgroundImage(field, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
		root.setBackground(new Background(fieldImage));
		root.setCenter(canvas);
		root.setTop(top);
		scene = new Scene(root, 1280, 800);
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
}
