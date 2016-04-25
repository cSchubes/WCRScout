import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

public class DeleteSearch {
	private Scene scene;
	private HBox top;
	private GridPane grid;
	private BorderPane root;
	private Label lookup, instruct, dropDownLab;
	private Button search, backButton;
	private TextField number;
	private ChoiceBox<String> dropdown;
	private ObservableList<String> choices = FXCollections.observableArrayList();
	private ComboBox<String> dropCombo;
	
	public DeleteSearch(){
		root = new BorderPane();
		
		for(Team t: WCRScout.data.getArray()){
			choices.add(t.getName() + "\t" + t.getNumber());
		}
		
		lookup = new Label("Delete Team");
		lookup.setFont(Font.font("Verdana", 70));
		GridPane.setConstraints(lookup, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);
		instruct = new Label("Enter team number/name:");
		instruct.setFont(Font.font("Verdana", 40));
		GridPane.setConstraints(instruct, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		number = new TextField();
		number.setFont(Font.font("Verdana", 40));
		GridPane.setConstraints(number, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);
		
		dropdown = new ChoiceBox<String>();
		dropdown.setItems(choices);
		dropdown.setId("bigChoice");
		//GridPane.setConstraints(dropdown, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		dropdown.setPrefSize(500, 100);
		
		dropCombo = new ComboBox<String>();
		dropCombo.setItems(choices);
		dropCombo.setId("bigChoice");
		GridPane.setConstraints(dropCombo, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		dropCombo.setPrefSize(500, 100);
		
		dropDownLab = new Label("Or select team:");
		dropDownLab.setFont(Font.font("Verdana", 40));
		GridPane.setConstraints(dropDownLab, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		search = new Button("Delete");
		search.setFont(Font.font("Verdana", 40));
		GridPane.setConstraints(search, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER);
		search.setOnAction(e -> {
			if(dropCombo.getValue()==null){
				try{
					Team t = WCRScout.data.getTeam(Integer.parseInt(number.getText()));
					WCRScout.data.remove(t.getNumber());
				}catch(Exception o){
					Team t = WCRScout.data.getTeam(number.getText());
					WCRScout.data.remove(t.getNumber());
				}
			}
			else{
				Team t = WCRScout.data.getTeam(dropCombo.getValue().substring(0, dropCombo.getValue().indexOf(" ")));
				WCRScout.data.remove(t.getNumber());
			}
			WCRScout.window.setScene(WCRScout.mainMenu.getScene());
		});
		
		grid = new GridPane();
		grid.setVgap(50);
		grid.setHgap(50);
		grid.getChildren().addAll(lookup, instruct, number, search, dropCombo, dropDownLab);
		grid.setAlignment(Pos.CENTER);
		
		top = new HBox();
		backButton = new Button();
		Image backImage = new Image(getClass().getResourceAsStream("back arrow.png"));
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
			number.clear();
		});
		backButton.setPrefSize(50, 50);
		backButton.setAlignment(Pos.CENTER_LEFT);
		top.getChildren().addAll(backButton);
		
		root.setPadding(new Insets(20, 50, 50, 20));
		root.setTop(top);
		root.setCenter(grid);
		
		scene = new Scene(root, 1280, 750);
		scene.getStylesheets().add(WCRScout.class.getResource("/testC.css").toExternalForm());
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public void update(){
		if(!WCRScout.data.isEmpty()){
			choices.clear();
			for(Team t: WCRScout.data.getArray()){
				choices.add(t.getName() + " " + t.getNumber());
			}
			dropdown.setItems(choices);
		}
		else
			choices.clear();
	}
}
