import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

public class FieldDraw{
	private Scene scene;
	private Canvas canvas;
	private Button backButton;
	private Image backImage;
	private HBox top;
	
	public FieldDraw(){
		canvas = new Canvas(1280, 800);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
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
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				
			}
		});
		
		backButton = new Button();
		backImage = new Image(getClass().getResourceAsStream("back arrow.png"));
		BackgroundImage back = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(30, 30, true, true, true, false));
		BackgroundFill fill = new BackgroundFill(Color.FIREBRICK, null, null);
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
		
		top = new HBox();
		top.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, null, BorderStroke.THIN)));
		top.getChildren().addAll(backButton);
		
		BorderPane root = new BorderPane();
		root.setCenter(canvas);
		root.setTop(top);
		scene = new Scene(root, 1280, 800);
	}
	
	public Scene getScene(){
		return scene;
	}

}
