package pl.sylwia.fours;

import java.util.HashMap;
import java.util.Map;

public class Area {
	Integer xMax = 5;
	Integer yMax = 6;
	Map<Point, String> area;
	Boolean greenPalyer = true;
	Integer numberOfWin = 4;

	public Area() {
		area = new HashMap<>();

		for (int i = 0; i <= xMax; i++) {
			for (int j = 0; j <= yMax; j++) {
				area.put(new Point(i, j), "");
			}
		}
	}

	public String move(Integer column) {

		for (String myArea : area.values()) {
			if (myArea.isEmpty())
				return moveInColumn(column);
		}

		String move = "Brak wolnych pól. Remis. ";
		showStatus(move);
		return move;

	}

	private void showStatus(String move) {
		System.out.println(move);
		showArea();

	}

	private String moveInColumn(Integer column) {
		for (int i = xMax; i >= 0; i--) {

			Point point = new Point(i, column);
			if (area.get(point).isEmpty()) {
				return movePlayer(point);
			}
		}
		String move = "Brak możliwości ruchu. ";
		showStatus(move);
		return move;
	}

	private String movePlayer(Point point) {

		return greenPalyer ? movePlayerZ(point) : movePlayerC(point);

	}

	private String movePlayerC(Point point) {
		String player = "c";
		area.replace(point, player);
		greenPalyer = true;

		return checkTheWinner(point, player);

	}

	private String movePlayerZ(Point point) {
		String player = "z";
		area.replace(point, player);
		greenPalyer = false;

		return checkTheWinner(point, player);
	}

	private String checkTheWinner(Point point, String player) {
		Integer four = checkWinnerColumns(point, player);
		Integer fourR = checkWinnerRows(point, player);
		Integer fourC = checkWinnerCross(point, player);

		if (four >= numberOfWin || fourR >= numberOfWin || fourC >= numberOfWin) {
			return showWhoWin(point);
		}

		String move = "Wykonano ruch. ";

		showStatus(move);
		return move;
	}

	private Integer checkWinnerColumns(Point point, String player) {
		Integer four = 1;

		for (int i = point.getX() + 1; i <= xMax; i++) {
			Point backPoint = new Point(i, point.getY());
			if (area.get(backPoint).equals(player))
				four++;
			else
				break;
		}
		return four;
	}

	private Integer checkWinnerRows(Point point, String player) {
		Integer fourR = 1;
		for (int j = point.getY() + 1; j <= yMax; j++) {
			Point forwardPoint = new Point(point.getX(), j);

			if (area.get(forwardPoint).equals(player))
				fourR++;
			else
				break;
		}

		for (int k = point.getY() - 1; k >= 0; k--) {
			Point backPointColumn = new Point(point.getX(), k);
			if (area.get(backPointColumn).equals(player))
				fourR++;
			else
				break;
		}
		return fourR;
	}

	private Integer checkWinnerCross(Point point, String player) {
		Integer fourC = 1;
		for (int i = point.getX() - 1, j = point.getY() + 1; i >= 0 && j <= yMax; i--, j++) {
			Point crossPoint = new Point(i, j);
			if (area.get(crossPoint).equals(player))
				fourC++;
			else
				break;

		}
		for (int i = point.getX() + 1, j = point.getY() - 1; j >= 0 && i <= xMax; i++, j--) {
			Point crossPoint = new Point(i, j);
			if (area.get(crossPoint).equals(player))
				fourC++;
			else
				break;

		}
		return fourC;
	}

	private String showWhoWin(Point point) {
		String move = "Wygrał gracz : " + area.get(point);
		showStatus(move);
		return move;
	}

	public Map<Point, String> getArea() {
		return area;
	}

	public void setArea(Map<Point, String> area) {
		this.area = area;
	}

	public Integer getxMax() {
		return xMax;
	}

	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}

	public Integer getyMax() {
		return yMax;
	}

	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}

	public void showArea() {

		for (int i = 0; i <= xMax; i++) {

			for (int j = 0; j <= yMax; j++) {
				if (area.get(new Point(i, j)) == "")
					System.out.print("|" + " " + "|");
				else
					System.out.print("|" + area.get(new Point(i, j)) + "|");
			}
			System.out.println("");

		}

	}

}
