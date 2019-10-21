
//kadai04_3 17D8010128  とう　こうてつ 2019/10/15
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

public class Kadai04_3 extends Application {
	private static int[][] board = new int[9][9];
	private int[][] res = new int[9][9];
	private TextField[][][] tf = new TextField[9][3][3];
	private Button bt;
	private Label lb;

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
		lb = new Label("空いてるマスに１桁数字を入力してください");

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
							tf[i*3+j][x][y] = new TextField();
							res[(i*9+j*3+x)%9][(i*9+j*3+x)/9] = 0;
						} else {
							tf[i*3+j][x][y] = new TextField(String.valueOf(board[i * 3 + x][j * 3 + y]));
							res[(i*9+j*3+x)%9][(i*9+j*3+x)/9] = board[i * 3 + x][j * 3 + y];
							tf[i*3+j][x][y].setEditable(false);
							tf[i*3+j][x][y].setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
						}
						tf[i*3+j][x][y].setMaxWidth(40);
						tf[i*3+j][x][y].setFont(Font.font("MonoSpace", 20));

						sub_gp.add(tf[i*3+j][x][y], y, x);

						// System.out.println("["+x+"]["+y+"]"+board[x][y]);
					}
				}
				gp.add(sub_gp, j, i);
			}

		}
		

		
		BorderPane bp=new BorderPane();
		gp.setAlignment(Pos.CENTER);
		bp.setAlignment(bt, Pos.CENTER);
		bt.setOnAction(new ClickEventHandler());
		
		for(int i=0;i<9;i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					tf[i][j][k].setOnAction(new InputEventHandler());
					tf[i][j][k].setUserData(i*9+j*3+k);
				}
			}
		}
		bp.setTop(gp);
		bp.setCenter(lb);
		bp.setBottom(bt);
		bp.setMargin(bt, new Insets(12,12,12,12));
		Scene sc=new Scene(bp,600,600);
		stage.setScene(sc);
		stage.setTitle("数独");
		stage.show();
	}

	class ClickEventHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int x = 0; x < 3; x++) {
						for (int y = 0; y < 3; y++) {
							if (board[i * 3 + x][j * 3 + y] == 0) {
								tf[i*3+j][x][y].setText(String.valueOf(" "));
							}else {
								tf[i*3+j][x][y].setText(String.valueOf(board[i * 3 + x][j * 3 + y]));
							}
						}
					}
				}

			}		
		}
	}
	class InputEventHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			TextField cur=(TextField)e.getTarget();
			int id=(int)cur.getUserData();
			tf[id/9][id%9/3][id%9%3].setText(cur.getText());
			int flag=1;
			for(int i=0;i<9;i++){
				for(int j=0;j<3;j++){
					for(int k=0;k<3;k++){
						if(tf[i][j][k].getText().equals("")){
							flag=0;
						}
						

						for(int m=0;m<3;m++){
							for(int n=0;n<3;n++){
								if(tf[i][j][k].getText().equals(tf[i][m][n].getText())){
									flag=0;
								}
							}
						}
					}

				}
			}

			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){	
					for(int k=0;k<9;k++){
						if(res[i][j]==res[i][k]){
							flag=0;
						}
						if(res[i][j]==res[k][j]){
							flag=0;
						}
					}

				}
			}
			if(flag==0){
				lb.setText("空いてるマスに１桁数字を入力してください");
			}else{
				lb.setText("ゲームグリア！おめとうございます");
			}
		}
	}
}