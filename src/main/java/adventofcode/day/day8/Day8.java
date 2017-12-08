package adventofcode.day.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day8/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = getInput();
        final Map<String, Integer> keys = new HashMap<>();

        for (final String str : input) {
            final List<String> types = Stream.of(str.split(" ")).collect(Collectors.toList());
            final String key = types.get(0);
            final String command = types.get(1);
            final Integer value = Integer.parseInt(types.get(2));
            final String ifKey = types.get(4);
            final String operator = types.get(5);
            final Integer ifValue = Integer.parseInt(types.get(6));

            keys.putIfAbsent(types.get(0), 0);

            if (compare(keys.getOrDefault(ifKey, 0), ifValue, operator)) {
                final Integer newValue = doCommand(keys.get(key), value, command);
                keys.put(key, newValue);
            }
        }

        System.out.println(keys);
        System.out.println(Collections.max(new ArrayList<>(keys.values())));
    }

    private static void starTwo() throws IOException {
        final List<String> input = getInput();
        final Map<String, Integer> keys = new HashMap<>();
        Integer highest = Integer.MIN_VALUE;

        for (final String str : input) {
            final List<String> types = Stream.of(str.split(" ")).collect(Collectors.toList());
            final String key = types.get(0);
            final String command = types.get(1);
            final Integer value = Integer.parseInt(types.get(2));
            final String ifKey = types.get(4);
            final String operator = types.get(5);
            final Integer ifValue = Integer.parseInt(types.get(6));

            keys.putIfAbsent(types.get(0), 0);

            if (compare(keys.getOrDefault(ifKey, 0), ifValue, operator)) {
                final Integer newValue = doCommand(keys.get(key), value, command);
                keys.put(key, newValue);
                highest = newValue > highest ? newValue : highest;
            }
        }

        System.out.println(keys);
        System.out.println(Collections.max(new ArrayList<>(keys.values())));
        System.out.println(highest);
    }

    private static boolean compare(final Integer left, final Integer right, final String operator) {
        switch (operator) {
        case ">":
            return left > right;
        case "<":
            return left < right;
        case ">=":
            return left >= right;
        case "<=":
            return left <= right;
        case "==":
            return Objects.equals(left, right);
        case "!=":
            return !Objects.equals(left, right);
        }

        return false;
    }

    private static Integer doCommand(final Integer currentValue, final Integer value, final String command) {
        switch (command) {
        case "inc":
            return currentValue + value;
        case "dec":
            return currentValue - value;
        }

        return currentValue;
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
