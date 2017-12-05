package adventofcode.day.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Day5 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day5/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> instructions = getInput();
        Integer steps = 0;
        for (int i = 0;;) {
            final Integer step = Integer.parseInt(instructions.get(i));
            instructions.set(i, String.valueOf(step + 1));
            i += step;
            steps++;
            if (!withinRange(instructions, i)) {
                break;
            }
        }

        System.out.println(steps);
    }

    private static void starTwo() throws IOException {
        final List<String> instructions = getInput();
        Integer steps = 0;
        for (int i = 0;;) {
            final Integer step = Integer.parseInt(instructions.get(i));
            if (step >= 3) {
                instructions.set(i, String.valueOf(step - 1));
            } else {
                instructions.set(i, String.valueOf(step + 1));
            }

            i += step;
            steps++;

            if (!withinRange(instructions, i)) {
                break;
            }
        }

        System.out.println(steps);
    }

    private static boolean withinRange(final List<String> list, final Integer index) {
        return 0 <= index && index < list.size();
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
