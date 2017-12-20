package adventofcode.day.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day16 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day16/input.txt");

    public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = Stream.of((getInput().get(0).split(",")))
                .collect(Collectors.toList());
        String dancers = "abcdefghijklmnop";
        System.out.println(dance(input, dancers));
    }

    private static void starTwo() throws IOException {
        final List<String> input = Stream.of((getInput().get(0).split(",")))
                .collect(Collectors.toList());
        String dancers = "abcdefghijklmnop";

        // The start string came back after 48 iterations (found by test): 1b % 48 = 16
        for (int i = 0; i < 16; i++) {
            dancers = dance(input, dancers);
        }

        System.out.println(dancers);
    }

    private static String dance(List<String> moves, String dancers) {
        for (String move : moves) {
            if(move.startsWith("x")) {
                move = move.replaceFirst("x", "");
                final String[] numbers = move.split("/");
                final Integer aIndex = Integer.parseInt(numbers[0]);
                final Integer bIndex = Integer.parseInt(numbers[1]);
                dancers = swap(dancers, aIndex, bIndex);
            } else if(move.startsWith("p")) {
                dancers = swap(dancers, dancers.indexOf(move.charAt(1)), dancers.indexOf(move.charAt(3)));
            } else if(move.startsWith("s")) {
                move = move.replaceFirst("s", "");
                dancers = spin(dancers, Integer.parseInt(move));
            }
        }

        return dancers;
    }

     private static String swap(final String dancers, final Integer aIndex, final Integer bIndex) {
        final StringBuilder sb = new StringBuilder(dancers);
        final char a = dancers.charAt(aIndex);
        final char b = dancers.charAt(bIndex);

        sb.setCharAt(aIndex, b);
        sb.setCharAt(bIndex, a);

        return sb.toString();
    }

    private static String spin(final String dancers, Integer length) {
        return dancers.substring(dancers.length()-length ,dancers.length())
                + dancers.substring(0, dancers.length()-length);
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
