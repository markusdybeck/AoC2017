package adventofcode.day.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day19 {

    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day19/input.txt");

    public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = getInput();
        final List<String> letters = new ArrayList<>();
        Integer row = 0;
        Integer index = input.get(0).indexOf('|');
        Integer direction = 2;

        Integer max = Integer.MIN_VALUE;
        for (final String s : input) {
            if (s.length() > max) {
                max = s.length();
            }
        }

        for (int i = 0; i < input.size(); i++) {
            final StringBuilder sBuilder = new StringBuilder(input.get(i));
            while (sBuilder.length() < max) {
                sBuilder.append(" ");
            }
            input.set(i, sBuilder.toString());
        }

        System.out.println(input);

        Integer count = 1;

        while (true) {
            if (direction == 2) {
                row++;
            } else if (direction == 0) {
                row--;
            } else if (direction == 3) {
                index--;
            } else if (direction == 1) {
                index++;
            }

            final char c = input.get(row).charAt(index);

            if (c == ' ') {
                break;
            }

            if (c != '-' && c != '|' && c != '+') {
                letters.add(String.valueOf(c));
            }

            if (c == '+') {
                final Integer newDirection = sds(row, index, direction, input);

                if (Objects.equals(direction, newDirection)) {
                    break;
                }

                direction = newDirection;
            }
            count++;
        }

        System.out.println(count);
        System.out.println(letters);
    }

    private static void starTwo() throws IOException {

    }

    private static Integer sds(final Integer row, final Integer line, final Integer direction, final List<String> input) {
        if ((line + 1) < input.get(row).length() && input.get(row).charAt(line + 1) != ' ' && direction != 3) {
            return 1;
        } else if ((line - 1) >= 0 && input.get(row).charAt(line - 1) != ' ' && direction != 1) {
            return 3;
        } else if ((row - 1) >= 0 && input.get(row - 1).charAt(line) != ' ' && direction != 2) {
            return 0;
        } else if ((row + 1) < input.size() && input.get(row + 1).charAt(line) != ' ' && direction != 0) {
            return 2;
        }

        return direction;
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
