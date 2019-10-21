
//kadai04_1 17D8010128  とう　こうてつ 2019/10/15
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.io.*;

public class Kadai04_2 extends Application {
	private static int[][] board = new int[9][9];
	private TextField[][] tf = new TextField[3][3];
	private Button bt;

	public static void main(String[] args) {
		String fname = "input.txt";
		if (args.length > 0)
			fname = args[0];
		try {
			Scanner sc = new Scanner(new File(fname));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					board[i][j] = sc.nextInt();
					if (board[i][j] < 0 || 9 < board[i][j])
						board[i][j] = 0;
				}
			}
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		bt = new Button("reset");
		GridPane gp = new GridPane();
		gp.setHgap(15);
		gp.setVgap(15);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				GridPane sub_gp = new GridPane();
				sub_gp.setHgap(5);
				sub_gp.setVgap(5);
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (board[i * 3 + x][j * 3 + y] == 0) {
							tf[x][y] = new TextField();
						} else {
							tf[x][y] = new TextField(String.valueOf(board[i * 3 + x][j * 3 + y]));
							tf[x][y].setEditable(false);
							tf[x][y].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
						}
						tf[x][y].setMaxWidth(40);
						tf[x][y].setFont(Font.font("MonoSpace", 20));

						sub_gp.add(tf[x][y], y, x);
					}
				}
				gp.add(sub_gp, j, i);
			}

		}
		
		
		BorderPane bp=new BorderPane();
		gp.setAlignment(Pos.CENTER);
		bp.setAlignment(bt, Pos.CENTER);
		bt.setOnAction((ActionEvent e) ->{
			
		});
		bp.setCenter(gp);
		bp.setBottom(bt);
		bp.setMargin(bt, new Insets(12,12,12,12));
		Scene sc=new Scene(bp,600,600);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}
}