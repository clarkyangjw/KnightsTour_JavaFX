package knightstour_javafx;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public class Utility {
	public static int accessibilityRate[][] = {
			{0,7,56,63},
			{1,6,8,15,48,55,57,62},
			{2,3,4,5,9,14,16,24,32,40,49,58,59,60,61,54,47,39,31,23},
			{10,11,12,13,17,25,33,41,50,51,52,53,46,38,30,22},
			{18,19,20,21,26,27,28,29,34,35,36,37,42,43,44,45}
	};
	
	
	public static int getAccessibility(int index) {
		int accessibility = 0;
		for(int i = 0; i < accessibilityRate.length; i++) {
			for(int j = 0; j < accessibilityRate[i].length; j++) {
				if(accessibilityRate[i][j]==index){
					switch(i) {
					case 0: accessibility = 2; break;
					case 1: accessibility = 3; break;
					case 2: accessibility = 4; break;
					case 3: accessibility = 6; break;
					case 4: accessibility = 8; break;
					default:
					}
				}
			}
		}
		return accessibility;
	}
	//getAccessibility() end
	
	
	public static void clearBackground() {
		for(Piece piece : Main.pieces) {
			//int index = Main.pieces.indexOf(piece);
			piece.setBackground(new Background(new BackgroundFill(Paint.valueOf("LightGray"), CornerRadii.EMPTY, Insets.EMPTY)));
//			if((index/8)%2 == 0) {
//				if (index % 2 == 0) {
//					piece.setBackground(Color.BLACK);
//				} else {
//					piece.setBackground(Color.WHITE);
//				}
//			}else{
//				if (index % 2 == 0) {
//					piece.setBackground(Color.WHITE);
//				} else {
//					piece.setBackground(Color.BLACK);
//				}
//			}
		}
	}
	//clearBackground() end
	
	public static ArrayList<Piece> getPossiblePieces(ArrayList<Point2D> points) {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for(Piece piece : Main.pieces) {
			for(Point2D point : points) {
				if(piece.getPoint().equals(point)) {
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}
}
