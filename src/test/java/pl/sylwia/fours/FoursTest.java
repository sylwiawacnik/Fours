package pl.sylwia.fours;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import pl.sylwia.fours.Area;
import pl.sylwia.fours.Point;

public class FoursTest {

	private Area area;

	@Before
	public void setUp() {
		// given
		area = new Area();
	}

	@Test
	public void checkTheAreaIsEmpty() {

		for (String value : area.getArea().values()) {

			Assertions.assertThat(value).isEmpty();
		}

	}

	@Test
	public void checkTheSizeArea() {

		Assertions.assertThat(area.getxMax()).isEqualTo(5);
		Assertions.assertThat(area.getyMax()).isEqualTo(6);
	}

	@Test
	public void checkTheFreeField() {
		// when
		area.move(3);
		// then
		Assertions.assertThat(area.getArea().get(new Point(5, 3))).isEqualTo("z");
	}

	@Test
	public void checkTheOccupiedField() {
		// when
		area.move(3);
		area.move(3);
		// then
		Assertions.assertThat(area.getArea().get(new Point(5, 3))).isEqualTo("z");
		Assertions.assertThat(area.getArea().get(new Point(4, 3))).isEqualTo("c");
		Assertions.assertThat(area.getArea().get(new Point(3, 3))).isEmpty();
	}

	@Test
	public void checkTheOccupiedColumn() {
		// when
		area.move(3);// 5
		area.move(3);// 4
		area.move(3);// 3
		area.move(3);// 2
		area.move(3);// 1
		area.move(3);// 0
		String noMove = area.move(3);
		// then
		Assertions.assertThat(area.getArea().get(new Point(0, 3))).isEqualTo("c");
		Assertions.assertThat(noMove).isEqualTo("Brak możliwości ruchu. ");
	}

	@Test
	public void checkTwoPlayers() {
		// when
		area.move(3);
		area.move(3);
		// then
		Assertions.assertThat(area.getArea().get(new Point(5, 3))).isEqualTo("z");
		Assertions.assertThat(area.getArea().get(new Point(4, 3))).isEqualTo("c");

	}

	@Test
	public void checkTheAreaIsGoodPrinting() {
		// when
		area.move(3);
		area.move(3);
		area.move(4);

	}

	@Test
	public void checkWhenIsDeadHeat() {
		// when
		area.move(0);
		area.move(0);
		area.move(0);
		area.move(0);
		area.move(0);
		area.move(0);

		area.move(1);
		area.move(1);
		area.move(1);
		area.move(1);
		area.move(1);
		area.move(1);

		area.move(2);
		area.move(2);
		area.move(2);
		area.move(2);
		area.move(2);
		area.move(2);

		area.move(3);
		area.move(3);
		area.move(3);
		area.move(3);
		area.move(3);
		area.move(3);

		area.move(4);
		area.move(4);
		area.move(4);
		area.move(4);
		area.move(4);
		area.move(4);

		area.move(5);
		area.move(5);
		area.move(5);
		area.move(5);
		area.move(5);
		area.move(5);

		area.move(6);
		area.move(6);
		area.move(6);
		area.move(6);
		area.move(6);
		area.move(6);

		String deadheat = area.move(6);

		// then
		Assertions.assertThat(deadheat).isEqualTo("Brak wolnych pól. Remis. ");
	}

	@Test
	public void checkTheWinnerInColumnC() {
		// given
		area.move(0);// z
		area.move(3);// c
		area.move(1);// z
		area.move(3);// c
		area.move(4);// z
		area.move(3);// c
		area.move(0);// z
		String winner = area.move(3);// c
		// when
		Assertions.assertThat(winner).isEqualTo("Wygrał gracz : " + "c");
	}

	@Test
	public void checkTheWinnerInColumnZ() {
		// given
		area.move(0);// z
		area.move(3);// c
		area.move(0);// z
		area.move(3);// c
		area.move(0);// z
		area.move(4);// c
		String winner = area.move(0);// z
		// when
		Assertions.assertThat(winner).isEqualTo("Wygrał gracz : " + "z");
	}

	@Test
	public void checkTheWinnerInRowZ() {
		area.move(0);// z
		area.move(0);// c
		area.move(1);// z
		area.move(0);// c
		area.move(2);// z
		area.move(4);// c
		String winner = area.move(3);// z
		// when
		Assertions.assertThat(winner).isEqualTo("Wygrał gracz : " + "z");
	}

	@Test
	public void checkTheWinnerInRowC() {
		area.move(5);// z
		area.move(0);// c
		area.move(5);// z
		area.move(1);// c
		area.move(0);// z
		area.move(2);// c
		area.move(0);// z
		String winner = area.move(3);// c
		// when
		Assertions.assertThat(winner).isEqualTo("Wygrał gracz : " + "c");
	}

	@Test
	public void checkTheWinnerCrossZ() {
		area.move(0);// z
		area.move(1);// c
		area.move(1);// z
		area.move(2);// c
		area.move(3);// z
		area.move(2);// c
		area.move(0);// z
		area.move(3);// c
		area.move(4);// z
		area.move(3);// c
		area.move(3);// z
		area.move(0);// c
		String winner = area.move(2);// z
		// when
		Assertions.assertThat(winner).isEqualTo("Wygrał gracz : " + "z");

	}
}
