package adventofcode.day.day15;

import java.io.IOException;

public class Day15 {
    private static final Integer A_FACTOR = 16807;
    private static final Integer B_FACTOR = 48271;
    private static final Integer DIVIDER = 2147483647;
    private static final Integer MILLION_40 = 40000000;
    private static final Integer MILLION_5 = 5000000;

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        Long a = 116L;
        Long b = 299L;
        Integer counter = 0;

        for (int i = 0; i < MILLION_40; i++) {
            a = (a * A_FACTOR) % DIVIDER;
            b = (b * B_FACTOR) % DIVIDER;

            if (bit16Equals(a, b)) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void starTwo() throws IOException {
        Long a = 116L;
        Long b = 299L;
        Integer counter = 0;

        for (int i = 0; i < MILLION_5; i++) {
            do {
                a = (a * A_FACTOR) % DIVIDER;
            } while (a % 4 != 0);

            do {
                b = (b * B_FACTOR) % DIVIDER;
            } while (b % 8 != 0);

            if (bit16Equals(a, b)) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static boolean bit16Equals(final Long a, final Long b) {
        return (a & 0xFFFF) == (b & 0xFFFF);
    }
}
