package tetris;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class TetrisUnitTest {
	@Test
	public void testMoveSquare() {
		Grid g = new Grid();
		Square s = new Square(g, 4, 4, Color.MAGENTA, true);
		// s is blocked on the right
		g.set(4, 5, Color.YELLOW);
		// s should not be able to move to the right
		boolean b = s.canMove(Direction.RIGHT);
		// System.out.println("b = " + b);
		assertFalse(b);
		// s should be able to move left and down
		assertTrue(s.canMove(Direction.LEFT));
		assertTrue(s.canMove(Direction.DOWN));
		// move s down
		s.move(Direction.DOWN);
		assertTrue(s.getRow() == 5 && s.getCol() == 4);
	}

	/**
	 * An example of a unit test for check rows Add your own grid example
	 */
	@Test
	public void testCheckRows() {
		// Create a grid with a few complete rows
		// e.g.
		/**
		 * <pre>
		 *             -> row = 0
		 *  XXXXXXXXXX -> row = 1
		 *    XXXX     -> row = 2
		 *     XX      -> row = 3
		 *     XX      -> row = 4
		 *  XXXXXXXXXX -> row = 5
		 *     XX      -> row = 6
		 *     XX      -> row = 7
		 *  XXXXXXXXXX -> row = 8
		 *     XX      -> row = 9
		 *  (empty rows from row = 10 to 19)
		 * </pre>
		 */
		// After calling checkRows, the grid should be
		/**
		 * <pre>
		 *             -> row = 0
		 *             -> row = 1
		 *             -> row = 2
		 *             -> row = 3
		 *    XXXX     -> row = 4
		 *     XX      -> row = 5
		 *     XX      -> row = 6
		 *     XX      -> row = 7
		 *     XX      -> row = 8
		 *     XX      -> row = 9
		 *  (empty rows from row = 10 to 19)
		 * </pre>
		 */
		Grid g = new Grid();
		// rows[r] = number of non empty squares on row r (the non empty squares
		// are centered)
		int[] rows = { 0, 10, 4, 2, 2, 10, 2, 2, 10, 2, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		for (int r = 0; r < Grid.HEIGHT; r++) {
			int cLeft = 0, cRight = Grid.WIDTH - 1;
			while (cLeft <= cRight) {
				if (cRight - cLeft < rows[r]) {
					g.set(r, cLeft, Color.MAGENTA);
					g.set(r, cRight, Color.MAGENTA);
				}
				cLeft++;
				cRight--;
			}
		}

		g.checkRows();

		rows = new int[] { 0, 0, 0, 0, 4, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		for (int r = 0; r < Grid.HEIGHT; r++) {
			int cLeft = 0, cRight = Grid.WIDTH - 1;
			while (cLeft <= cRight) {
				if (cRight - cLeft < rows[r]) {
					assertTrue(g.isSet(r, cLeft));
					assertTrue(g.isSet(r, cRight));
				} else {
					assertFalse(g.isSet(r, cLeft));
					assertFalse(g.isSet(r, cRight));
				}
				cLeft++;
				cRight--;
			}
		}
	}

	@Test
	public void checkRows2() {
		Grid g = new Grid();
		for (int r = 0; r < Grid.HEIGHT; r++) {
			if (r == 10) {
				for (int c = 6; c <= 7; c++) {
					g.set(r, c, Color.RED);
				}
			} else {
				for (int c = 0; c < Grid.WIDTH; c++) {
					g.set(r, c, Color.RED);
				}
			}
		}
		g.checkRows();
		
		// check the grid
		for (int r = 0; r < Grid.HEIGHT - 1; r ++) {
			for (int c = 0; c < Grid.WIDTH; c ++) {
				assertFalse(g.isSet(r, c));
			}
		}
		// bottom row
		for (int c = 0; c < Grid.WIDTH; c ++) {
			if (c != 6 && c != 7) {
			assertFalse(g.isSet(Grid.HEIGHT - 1, c));
			} else {
				assertTrue(g.isSet(Grid.HEIGHT - 1, c));
			}
		}
	}

}

