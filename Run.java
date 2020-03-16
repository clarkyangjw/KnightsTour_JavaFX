package knightstour_javafx;

import java.awt.Color;

import java.awt.Point;


import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;




public class Run {

	public static void start() {
		Main.isAuto = false;
		Main.stepCount = 1;
		Utility.clearBackground();
		for(Piece piece : Main.pieces) {
			piece.setText("");
			//piece.setBackground(new Background(new BackgroundFill(Paint.valueOf("RED"), CornerRadii.EMPTY, Insets.EMPTY)));
			piece.setDisable(false);
		}
	}
	//start() end

	

	public static void perform(Piece pressed) {
		Utility.clearBackground();
		for(Piece piece : Main.pieces) {
			piece.setDisable(true);
			if(piece == pressed) {
				piece.setText(String.valueOf(Main.stepCount++));
			}
		}

		for(Piece piece : Main.pieces) { 
			for(Point2D point : pressed.getPossiblePoints()) {
				if(piece.getPoint().equals(point)) {
					if(piece.getText().isEmpty()) {
						piece.setDisable(false);
						piece.setBackground(new Background(new BackgroundFill(Paint.valueOf("RED"), CornerRadii.EMPTY, Insets.EMPTY)));
					}
				}
			}
		}
		
		if(!Main.isAuto) {
			boolean gameOver = true;
			for(Piece piece : Main.pieces) {
				if(piece.isDisable() == false) {
					gameOver = false;
					break;
				}
			}
			if(gameOver) {
				JOptionPane.showMessageDialog(null, "Game over!", 
						"Knight's Tour", JOptionPane.WARNING_MESSAGE);
			}
		}

		pressed.updateAccessibility();
		//		int i = 1;
		//		for(Piece piece: Board.pieces) {
		//			System.out.print(piece.getAccessibility());
		//			if(i%8 == 0) {
		//				System.out.println();
		//			}
		//			i++;
		//		}

	}
	//perform() end
	
	
	public  static void autoPlay() {
		Main.isAuto = true;
		Main.stepCount = 1;
		Utility.clearBackground();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Main.route[i][j] = 0;
			}
		}
		for(Piece piece : Main.pieces) {
			int index = Main.pieces.indexOf(piece);
			piece.setAccessibility(Utility.getAccessibility(index));
		}
		for(Piece piece : Main.pieces) {
			piece.setText("");
			piece.setDisable(false);
		}
		int max=63,min=0;
		int ran = (int) (Math.random()*(max-min)+min);
		Main.pieces.get(ran).fire();
		Main.route[(int)Main.pieces.get(ran).getPoint().getX()][(int)Main.pieces.get(ran).getPoint().getY()] = Main.stepCount - 1;
		Piece pressed = Main.pieces.get(ran);
		boolean flag = true;
		do {
			int accessibility = 9;
			Main.possiblePieces = Utility.getPossiblePieces(pressed.getPossiblePoints());
			for(Piece piece : Main.pieces) { 
				for(Piece possiblePiece : Main.possiblePieces) { 
					if(piece== possiblePiece) {
						if(piece.getText().isEmpty() == true) {
							if(piece.getAccessibility() < accessibility) {
								accessibility = piece.getAccessibility();
								pressed = piece;
							}
						}
					}
				}
			}

			for(Piece piece : Main.pieces) { 
				if(piece == pressed) {
					piece.fire();
					Main.route[(int)piece.getPoint().getX()][(int)piece.getPoint().getY()] = Main.stepCount - 1;
					break;
				}
			}

			for(Piece piece : Main.pieces) {
				if(piece.isDisable() == false) {
					flag = true;
					break;
				}else {
					flag = false;
				}
			}
		}while(flag);
		

		
	}
	//autoPlay() end

	public static void autoPlayX() {
		int count = 0;
		String playTime = JOptionPane.showInputDialog("How Many time do you want to auto play?");
		int xTime = Integer.parseInt(playTime);
		for(int i = 0; i < xTime; i++) {
			autoPlay();
			if(Main.stepCount == 65) {
				count++;
			}
			System.out.printf("Round %d gets %d, and the route is: \n", i + 1, Main.stepCount - 1);
			for(int j = 0; j < 8; j++) {
				for(int k = 0; k < 8; k++) {
					System.out.printf("%02d ", Main.route[j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.printf("Autoplay runs %d times, and totally gets full tour %d times!", xTime, count);
		System.out.println();
	}
	
	
}
