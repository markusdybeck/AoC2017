package adventofcode.utils;

public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    Direction(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

}
