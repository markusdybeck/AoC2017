package adventofcode.day.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import adventofcode.utils.InputHelper;

public class Day4 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day4/input.txt");

    public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> rows = getInput();
        final Integer answer = rows.stream().mapToInt(row -> {
            final InputHelper ih = new InputHelper(row);
            final List<String> passphrase = ih.toStringList(" ");
            final Set<String> theSet = new HashSet<>(passphrase);

            return passphrase.size() == theSet.size() ? 1 : 0;
        }).sum();

        System.out.println(answer);
    }

    private static void starTwo() throws IOException {
        final List<String> rows = getInput();
        final Integer answer = rows.stream().mapToInt(row -> {
            final List<String> passphrase = Stream.of(row.split(" "))
                .map(String::toCharArray)
                .peek(Arrays::sort)
                .map(String::new)
                .collect(Collectors.toList());
            final Set<String> theSet = new HashSet<>(passphrase);

            return passphrase.size() == theSet.size() ? 1 : 0;
        }).sum();

        System.out.println(answer);
    }

    private static List<String> getInput() throws IOException {
        return Files.readAllLines(INPUT_PATH);
    }
}
