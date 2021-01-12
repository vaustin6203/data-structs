package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a Clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    /**
     * Clorus gains energy of the creature it attacks.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        this.energy -= 0.03;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void stay() {
        this.energy += 0.01;
    }

    public Clorus replicate() {
        Clorus baby = new Clorus(energy / 2);
        this.energy *= 0.5;
        return baby;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        Deque<Direction> plips = new ArrayDeque<>();

        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name() == "empty") {
                emptyNeighbors.addFirst(key);
            }
            if (neighbors.get(key).name() == "plip") {
                anyPlip = true;
                plips.addFirst(key);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlip)  {
            Direction random = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, random);
        } else if (energy >= 1) {
            Direction space = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action (Action.ActionType.REPLICATE, space);
        }
        Direction emptySpace = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, emptySpace);
    }
}
