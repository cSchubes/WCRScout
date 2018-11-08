import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ObstacleChooser {
	private static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	static final ObservableList<String> choices = FXCollections.observableArrayList("Cheval de Frise", "Portcullis", "Ramparts", "Moat", "Sally Port", "Rock Wall", "Rough Terrain", "Drawbridge");
	static ObservableList<String> choicesTest = FXCollections.observableArrayList();
	
	public static ArrayList<Obstacle> display(String message, ArrayList<Obstacle> current){
		ArrayList<ChoiceBox<String>> cbs = new ArrayList<ChoiceBox<String>>();
		GridPane grid = new GridPane();
		int index = 0;
		for(int i = 0; i<4; i++){
			ChoiceBox<String> cb = new ChoiceBox<String>();
			cb.setItems(choices);
			cb.setPrefSize(400, 75);
			if(current.size() != 0){
				switch(current.get(i).getName()){
				case "Cheval de Frise":
					index = 0;
					break;
				case "Portcullis":
					index = 1;
					break;
				case "Ramparts":
					index = 2;
					break;
				case "Moat":
					index = 3;
					break;
				case "Sally Port":
					index = 4;
					break;
				case "Rock Wall":
					index = 5;
					break;
				case "Rough Terrain":
					index = 6;
					break;
				case "Drawbridge":
					index = 7;
					break;
				}
				cb.getSelectionModel().select(index);
			}
			cbs.add(cb);
			grid.add(cbs.get(i), 1, i);
		}
		Stage window = new Stage();
		window.setTitle(message);
		Label twoLab = new Label("Position 2:");
		twoLab.setFont(Font.font("Verdana", 30));
		Label threeLab = new Label("Position 3:");
		threeLab.setFont(Font.font("Verdana", 30));
		Label fourLab = new Label("Position 4:");
		fourLab.setFont(Font.font("Verdana", 30));
		Label fiveLab = new Label("Position 5:");
		fiveLab.setFont(Font.font("Verdana", 30));
		
		Button apply = new Button("Apply");
		apply.setAlignment(Pos.CENTER);
		apply.setPrefSize(200, 50);
		apply.setFont(Font.font("Verdana", 30));
		GridPane.setConstraints(apply, 1, 4, 1, 1, HPos.CENTER, VPos.BOTTOM);
		apply.setOnAction(e -> {
			if(window.getTitle().equals("Left Side"))
				for(int i = 2; i<6; i++){
					obstacles.add(new Obstacle(cbs.get(i-2).getValue(), i, 0));
				}
			else
				for(int i = 2; i<6; i++){
					obstacles.add(new Obstacle(cbs.get(i-2).getValue(), i, 1));
				}
			window.close();
		});
		
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setHgap(30);
		grid.setVgap(20);
		grid.add(twoLab, 0, 0);
		grid.add(threeLab, 0, 1);
		grid.add(fourLab, 0, 2);
		grid.add(fiveLab, 0, 3);
		grid.getChildren().addAll(apply);
		
		Scene sc = new Scene(grid, 700, 500);
		window.setScene(sc);
		sc.getStylesheets().add(WCRScout.class.getResource("/testC.css").toExternalForm());
		window.showAndWait();
		
		return obstacles;
	}
}
