package adventofcode.day.day1;

import java.util.Objects;

import adventofcode.utils.InputHelper;

public class Day1 {
    private static final InputHelper INPUT = new InputHelper(Input.THE_INPUT);

    public static void main(final String[] args) {
        // starOne();
        starTwo();
    }

    private static void starOne() {
        final Integer stepForward = 1;

        final Integer result = INPUT.intStream()
            .map(i -> Day1.checkMatch(i, stepForward))
            .sum();

        System.out.println(result);
    }

    private static void starTwo() {
        final Integer stepForward = INPUT.length() / 2;

        final Integer result = INPUT.intStream()
            .map(i -> Day1.checkMatch(i, stepForward))
            .sum();

        System.out.println(result);
    }

    private static Integer checkMatch(final Integer position, final Integer stepForward) {
        final int compareIndex = position + stepForward;
        final char currentChar = INPUT.get().charAt(position);
        final char compareChar;

        if (compareIndex > INPUT.lastIndex()) {
            compareChar = INPUT.get().charAt((compareIndex - INPUT.lastIndex()) - 1);
        } else {
            compareChar = INPUT.get().charAt(compareIndex);
        }

        if (Objects.equals(currentChar, compareChar)) {
            return InputHelper.toInt(currentChar);
        }

        return 0;
    }
}
