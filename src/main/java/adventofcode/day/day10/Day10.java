package adventofcode.day.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day10/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<Integer> input = Stream.of((getInput().get(0).replaceAll(" ", "").split(",")))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        final List<Integer> numbers = IntStream.range(0, 256).mapToObj(Integer::new).collect(Collectors.toList());
        hashTheList(numbers, input, 1);
        System.out.println(numbers.get(0) * numbers.get(1));
    }


    private static void starTwo() throws IOException {
        final List<Integer> input = Stream.of((getInput().get(0).split("")))
                .map(String::chars)
                .map(IntStream::sum)
                .collect(Collectors.toList());
        input.addAll(Arrays.asList(17, 31, 73, 47, 23));

        final List<Integer> numbers = IntStream.range(0, 256).mapToObj(Integer::new).collect(Collectors.toList());
        hashTheList(numbers, input, 64);

        List<String> hexs = toHex(numbers);
        System.out.println(hexs);
        System.out.println(hexs.stream().collect(Collectors.joining()));
    }

    private static void hashTheList(final List<Integer> numbers, final List<Integer> lengths, final int iterations) {
        Integer currentPosition = 0;
        Integer skipSize = 0;
        for (int i = 0; i < iterations; i++) {
            for (Integer length : lengths) {
                if ((currentPosition + length) > numbers.size()) {
                    final Integer rotate = (currentPosition + length) - numbers.size();
                    Collections.rotate(numbers, -rotate);
                    Collections.reverse(numbers.subList(currentPosition - rotate, numbers.size()));
                    Collections.rotate(numbers, rotate);
                } else {
                    Collections.reverse(numbers.subList(currentPosition, currentPosition + length));
                }

                currentPosition = (currentPosition + skipSize + length) % numbers.size();
                skipSize++;
            }
        }
    }

    private static List<String> toHex(List<Integer> numbers) {
        final List<String> hexs = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            String theHex =
                    numbers.subList(i * 16, (i + 1) * 16).stream()
                            .reduce((a, b) -> a ^ b)
                            .map(Integer::toHexString)
                            .orElse(null);

            theHex = theHex.length() == 1 ? "0" + theHex : theHex;
            hexs.add(theHex);
        }

        return hexs;
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
