package creatures;

import huglife.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestClorus {

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // If any Plips are seen, attack.
        Clorus cl = new Clorus(1.2);
        HashMap<Direction, Occupant> plips = new HashMap<Direction, Occupant>();
        plips.put(Direction.TOP, new Empty());
        plips.put(Direction.BOTTOM, new Empty());
        plips.put(Direction.LEFT, new Impassible());
        plips.put(Direction.RIGHT, new Plip(1.1));

        Action actual1 = cl.chooseAction(plips);
        Action expected1 = new Action(Action.ActionType.ATTACK, Direction.RIGHT);

        assertEquals(actual1, expected1);

        // If Clorus's energy >= 1, replicate.
        Clorus clo = new Clorus(1.5);
        HashMap<Direction, Occupant> empty = new HashMap<Direction, Occupant>();
        empty.put(Direction.TOP, new Empty());
        empty.put(Direction.BOTTOM, new Impassible());
        empty.put(Direction.RIGHT, new Impassible());
        empty.put(Direction.LEFT, new Impassible());

        Action actual2 = clo.chooseAction(empty);
        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(actual2, expected2);

        // If Clorus's energy < 1, move.
        Clorus clor = new Clorus(0.9);
        HashMap<Direction, Occupant> spaces = new HashMap<Direction, Occupant>();
        spaces.put(Direction.TOP, new Impassible());
        spaces.put(Direction.BOTTOM, new Empty());
        spaces.put(Direction.LEFT, new Impassible());
        spaces.put(Direction.RIGHT, new Impassible());

        Action actual3 = clor.chooseAction(spaces);
        Action expected3 = new Action(Action.ActionType.MOVE, Direction.BOTTOM);

        assertEquals(actual3, expected3);
    }
}
