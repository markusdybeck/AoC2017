package adventofcode.day.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day11/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = Stream.of((getInput().get(0).split(",")))
            .collect(Collectors.toList());

        final double y = Collections.frequency(input, "n") + Collections.frequency(input, "ne") - Collections.frequency(input, "s") - Collections.frequency(input, "sw");
        final double x = Collections.frequency(input, "s") + Collections.frequency(input, "se") - Collections.frequency(input, "n") - Collections.frequency(input, "nw");
        final double z = Collections.frequency(input, "ne") + Collections.frequency(input, "se") - Collections.frequency(input, "nw") - Collections.frequency(input, "sw");

        final double answer = (Math.abs(y) + Math.abs(x) + Math.abs(z)) / 2;
        System.out.println(answer);
    }

    private static void starTwo() throws IOException {
        final List<String> input = Stream.of((getInput().get(0).split(",")))
            .collect(Collectors.toList());

        int[] pos = new int[] { 0, 0, 0 };

        double max = (double) Integer.MIN_VALUE;

        for (final String s : input) {
            pos = walk(s, pos);
            final double calc = calc(pos);
            if (calc > max) {
                max = calc;
            }
        }

        System.out.println(calc(pos));
        System.out.println(max);
    }

    private static double calc(final int[] pos) {
        return (Math.abs(pos[0]) + Math.abs(pos[1]) + Math.abs(pos[2])) / 2;
    }

    private static int[] walk(final String go, final int[] pos) {
        switch (go) {
        case "n": {
            pos[0]--;
            pos[1]++;
            break;
        }
        case "ne": {
            pos[1]++;
            pos[2]++;
            break;

        }
        case "se": {
            pos[0]++;
            pos[2]++;
            break;

        }
        case "s": {
            pos[0]++;
            pos[1]--;
            break;

        }
        case "sw": {
            pos[1]--;
            pos[2]--;
            break;

        }
        case "nw": {
            pos[0]--;
            pos[2]--;
            break;
        }
        }
        return pos;
    }

    private static List<String> getInput() {
        try {
            return Files.readAllLines(INPUT_PATH);
        } catch (final Exception e) {
            System.out.println("Error while reading file...");
        }

        return Collections.emptyList();
    }
}
