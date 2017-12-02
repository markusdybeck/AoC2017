package adventofcode.day.day2;

import adventofcode.utils.InputHelper;

import java.util.Collections;
import java.util.List;

public class Day2 {
    private static final InputHelper INPUT = new InputHelper(Input.THE_INPUT);

    public static void main(final String[] args) {
        //starOne();
        starTwo();
    }

    private static void starOne() {
        final List<List<Integer>> theList = INPUT.toListOfIntegerList("\\t", "\\n");
        final Integer sum = theList.stream().mapToInt(Day2::calcCheckSum).sum();
        System.out.println(sum);
    }

    private static void starTwo() {
        final List<List<Integer>> theList = INPUT.toListOfIntegerList("\\t", "\\n");
        final Integer sum = theList.stream().mapToInt(Day2::calcEvenDividedNumbers).sum();
        System.out.println(sum);
    }

    private static Integer calcEvenDividedNumbers(final List<Integer> numbers) {
        for (Integer number : numbers) {
            for (Integer num : numbers) {
                if (!num.equals(number) && (number % num) == 0) {
                    return number / num;
                }
            }
        }

        return 0;
    }

    private static Integer calcCheckSum(final List<Integer> numbers) {
        return Collections.max(numbers) - Collections.min(numbers);
    }
}
