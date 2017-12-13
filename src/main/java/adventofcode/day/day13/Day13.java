package adventofcode.day.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day13 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day13/input.txt");

    public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final Map<Integer, Integer> map = getInput().stream()
            .map(str -> str.split(": "))
            .collect(Collectors.toMap(str -> Integer.parseInt(str[0]), str -> Integer.parseInt(str[1])));
        final Integer max = Collections.max(map.keySet());

        Integer sum = 0;

        for (int i = 0; i <= max; i++) {
            final Integer depth = map.getOrDefault(i, 0);
            if (gotCaught(i, depth)) {
                sum += i * depth;
            }
        }

        System.out.println(sum);
    }

    private static void starTwo() throws IOException {
        final Map<Integer, Integer> map = getInput().stream()
            .map(str -> str.split(": "))
            .collect(Collectors.toMap(str -> Integer.parseInt(str[0]), str -> Integer.parseInt(str[1])));
        final Integer max = Collections.max(map.keySet());

        Integer delay = 0;
        while (true) {
            boolean caught = false;
            for (int i = 0; i <= max; i++) {
                final Integer depth = map.getOrDefault(i, -1);
                if (depth != -1 && gotCaught(i + delay, depth)) {
                    caught = true;
                    break;
                }
            }
            if (!caught) {
                System.out.println("Done, delay was: " + delay);
                break;
            }
            delay++;
        }
    }

    private static boolean gotCaught(final Integer i, final Integer depth) {
        return getScannerPos(i, depth) == 0;
    }

    private static Integer getScannerPos(final Integer i, final Integer depth) {
        return i % ((depth - 1) * 2);
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
