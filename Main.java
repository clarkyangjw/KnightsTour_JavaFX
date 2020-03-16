package knightstour_javafx;


import java.util.ArrayList;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class Main extends Application{

	Stage window;
	Label label;
	Button button;
	BorderPane layout;

	public static boolean isAuto = false;
	public static ArrayList<Piece> pieces= new ArrayList<>(64);
	public static ArrayList<Piece> possiblePieces = new ArrayList<>(8);
	public static int stepCount = 1;
	public static int[][] route = new int[8][8];
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Knight's Tour");
		
		
		BorderPane pane = new BorderPane();
	
		
		Menu gameMenu = new Menu("Game");
		MenuItem start = new MenuItem("Start");
		start.setOnAction(e -> Run.start());
		gameMenu.getItems().add(start);
		
		
		Menu helpMenu = new Menu("Help");
		MenuItem autoPlay = new MenuItem("AutoPlay");
		autoPlay.setOnAction(e -> Run.autoPlay());
		MenuItem autoPlayX = new MenuItem("AutoPlayX");
		autoPlayX.setOnAction(e -> Run.autoPlayX());
		helpMenu.getItems().addAll(autoPlay, autoPlayX);
		
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(gameMenu, helpMenu);
	
		pane.setTop(menuBar);
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		
		int grids = 8;	
		int gridsSize = 80;
		int labelWidth = 80;
		int labelHeight = 40;
		

		for(int i = 0; i < grids; i++) {
			label = new Label("        "+String.valueOf(i));
			label.setPrefSize(labelWidth, labelHeight);
			GridPane.setConstraints(label, i + 1, 0);
			grid.getChildren().add(label);
		}

		for(int i = 0; i < grids; i++) {
			label = new Label("   "+String.valueOf(i));
			label.setPrefSize(labelHeight, labelWidth);
			GridPane.setConstraints(label, 0, i + 1);
			grid.getChildren().add(label);
		}
		
		for(int i = 0; i < grids; i++) {
			for(int j = 0; j < grids;j++) {
				Point2D point = new Point2D(i,j);
				Piece piece = new Piece(point, 0);
				piece.setPrefSize(gridsSize, gridsSize);
				piece.setBackground(new Background(new BackgroundFill(Paint.valueOf("LightGray"), CornerRadii.EMPTY, Insets.EMPTY)));
				piece.setFont(Font.font("Arial", FontWeight.BOLD, 25.0));
				piece.setDisable(true);
				piece.setOnAction(e -> Run.perform((Piece)e.getSource()));
				GridPane.setConstraints(piece, i + 1, j + 1);
				grid.getChildren().add(piece);
				pieces.add(piece);
			}
		}
		
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(pane, grid);
		
		Scene scene = new Scene(vBox, 750, 800);
		window.setScene(scene);
		window.setResizable(false); 
		window.show();
	}

}
