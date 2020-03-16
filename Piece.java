package knightstour_javafx;


import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;

public class Piece extends Button{
	private Point2D point = new Point2D(0,0);
	private int accessibility = 0;
	private ArrayList<Point2D> possiblePoint = new ArrayList<>(8);
	
	public Piece(Point2D point, int accessibility) {
		super();
		this.point = point;
		this.accessibility = accessibility;
		Point2D[] temPoint = new Point2D[8];
		temPoint[0] = new Point2D(this.point.getX() + 2, this.point.getY() - 1);
		temPoint[1] = new Point2D(this.point.getX() + 1, this.point.getY() - 2);
		temPoint[2] = new Point2D(this.point.getX() - 1, this.point.getY() - 2);
		temPoint[3] = new Point2D(this.point.getX() - 2, this.point.getY() - 1);
		temPoint[4] = new Point2D(this.point.getX() - 2, this.point.getY() + 1);
		temPoint[5] = new Point2D(this.point.getX() - 1, this.point.getY() + 2);
		temPoint[6] = new Point2D(this.point.getX() + 1, this.point.getY() + 2);
		temPoint[7] = new Point2D(this.point.getX() + 2, this.point.getY() + 1);
		for(int i = 0; i < 8; i++) {
			if((temPoint[i].getX() >= 0 && temPoint[i].getX() <= 7) && (temPoint[i].getY() >= 0 && temPoint[i].getY() <= 7)) {
				possiblePoint.add(temPoint[i]);
			}
		}
	}

	public Point2D getPoint() {
		return point;
	}

	public int getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(int accessibility) {
		this.accessibility = accessibility;
	}

	public ArrayList<Point2D> getPossiblePoints(){
		return possiblePoint;
	}
	
	public void updateAccessibility() {
		this.setAccessibility(0);
		for(Piece piece : Main.pieces) { 
			for(Point2D point : this.getPossiblePoints()) {
				if(piece.getPoint().equals(point)) {
					if(piece.getAccessibility() != 0) {
						piece.setAccessibility(piece.getAccessibility() - 1);
					}
				}
			}
		}
	}
	
	
}
