package adventofcode.day.day14;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import adventofcode.day.day10.Day10;

public class Day14 {

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final String theInput = "nbysizxe";
        final StringBuilder ans = new StringBuilder();

        for (int i = 0; i < 128; i++) {
            final List<Integer> input = Stream.of((theInput + "-" + i).split(""))
                .map(String::chars)
                .map(IntStream::sum)
                .collect(Collectors.toList());
            input.addAll(Arrays.asList(17, 31, 73, 47, 23));

            final List<Integer> numbers = IntStream.range(0, 256).boxed().collect(Collectors.toList());
            Day10.hashTheList(numbers, input, 64);

            final List<String> hexs = Day10.toHex(numbers);
            final String binary = hexToBin(hexs);
            ans.append(binary);
        }

        System.out.println(ans.length());
        System.out.println(ans.toString().replaceAll("0", "").length());

    }

    private static void starTwo() throws IOException {
        final String input = "nbysizxe";
        final List<String> bins = getBinaries(input);
        System.out.println(calcRegions(bins));
    }

    private static List<String> getBinaries(final String theInput) {
        final List<String> bins = new ArrayList<>();

        for (int i = 0; i < 128; i++) {
            final List<Integer> input = Stream.of((theInput + "-" + i).split(""))
                .map(String::chars)
                .map(IntStream::sum)
                .collect(Collectors.toList());
            input.addAll(Arrays.asList(17, 31, 73, 47, 23));

            final List<Integer> numbers = IntStream.range(0, 256).boxed().collect(Collectors.toList());
            Day10.hashTheList(numbers, input, 64);

            final List<String> hexs = Day10.toHex(numbers);
            final String binary = hexToBin(hexs);
            bins.add(binary);
        }

        return bins;
    }

    private static int calcRegions(final List<String> bins) {
        int groups = 0;
        final Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < bins.size(); i++) {
            final String line = bins.get(i);
            for (int j = 0; j < bins.size(); j++) {
                final char current = line.charAt(j);
                final Integer pos = i * 128 + j;
                if (current != '1' || visited.contains(pos)) {
                    continue;
                }

                checkNeighbours(bins, i, j, visited);
                visited.add(pos);
                groups++;
            }
        }

        System.out.println(visited);

        return groups;
    }

    private static void checkNeighbours(final List<String> list, final Integer line, final Integer pos, final Set<Integer> visited) {
        final char current = list.get(line).charAt(pos);
        final Integer absPos = line * 128 + pos;
        if (current == '0' || visited.contains(absPos)) {
            return;
        }

        visited.add(absPos);

        if (pos != 0) {
            final char left = list.get(line).charAt(pos - 1);
            if (left == '1') {
                checkNeighbours(list, line, pos - 1, visited);
            }
        }

        if (line != 127) {
            final char above = list.get(line + 1).charAt(pos);
            if (above == '1') {
                checkNeighbours(list, line + 1, pos, visited);
            }
        }

        if (line != 0) {
            final char under = list.get(line - 1).charAt(pos);
            if (under == '1') {
                checkNeighbours(list, line - 1, pos, visited);
            }
        }

        if (pos != 127) {
            final char right = list.get(line).charAt(pos + 1);
            if (right == '1') {
                checkNeighbours(list, line, pos + 1, visited);
            }
        }
    }

    private static String hexToBin(final List<String> hexs) {
        final StringBuilder ret = new StringBuilder();
        for (final String str : hexs) {
            final StringBuilder bin = new StringBuilder(new BigInteger(str, 16).toString(2));
            while (bin.length() < 8) {
                bin.insert(0, "0");
            }
            ret.append(bin);
        }
        return ret.toString();
    }
}
