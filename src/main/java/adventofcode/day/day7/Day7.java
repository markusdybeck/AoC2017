package adventofcode.day.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day7/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> lines = getInput(); // System.out.println(lines); final Map<String,
        final Map<String, Set<String>> structure = new HashMap<>();
        final Set<String> candidates = new HashSet<>();

        for (final String line : lines) {
            final String name = line.split("\\)")[0] + ")";
            final String csv = line.substring(name.length(),
                line.length()).replaceAll(" -> ", "").replaceAll(" ", ""); // System.out.println("CSV: " + csv.length());

            if (csv.length() > 1) {
                final Set<String> aboveNames = Stream.of(csv.split(",")).filter(s -> !Objects.equals(s, " ")).collect(Collectors.toSet());

                structure.put(name, aboveNames);
                candidates.add(name);
            }
        }

        for (final String cand : candidates) {
            boolean gotAbove = false;

            for (final String sdsd : candidates) {
                final Set<String> theList = structure.get(sdsd);
                final Integer index = cand.indexOf(" ");
                System.out.println("Candidate: " + cand.substring(0, index));
                System.out.println(theList);
                if (theList.contains(cand.substring(0,
                    index))) {
                    gotAbove = true;
                }
            }

            if (!gotAbove) {
                System.out.println(cand);
                break;
            }
        }
    }

    private static void starTwo() throws IOException {
        final List<String> lines = getInput();
        final Map<String, Set<String>> structure = new HashMap<>();
        final Map<String, Integer> ints = new HashMap<>();

        for (final String line : lines) {
            final String name = line.split("-> ")[0].trim();
            final String csv = line.substring(name.length(), line.length()).replaceAll(" -> ", "").replaceAll(" ", "");
            if (csv.length() > 1) {
                final Set<String> aboveNames = Stream.of(csv.split(","))
                    .filter(s -> !Objects.equals(s, " "))
                    .collect(Collectors.toSet());

                structure.put(name, aboveNames);
            }

            final Integer first = name.indexOf("(");
            final Integer last = name.indexOf(")");
            final String theInt = name.substring(first + 1, last);
            ints.put(name.substring(0, first - 1), Integer.parseInt(theInt));
        }

        System.out.println(ints);

        final String start = "orflty (82)";

        for (final String asdasd : structure.get(start)) {
            final Integer intValue = ints.get(asdasd);
            final String getName = asdasd + " (" + intValue + ")";
            System.out.println("GetName: " + getName);
            System.out.println((intValue + getWeight(structure.get(getName), ints, structure)));
        }

    }

    private static Integer getWeight(final Set<String> theList, final Map<String, Integer> ints, final Map<String, Set<String>> structrue) {
        Integer w = 0;
        for (final String name : theList) {
            final Integer weight = ints.get(name);
            w += weight;
            final String sName = name + " (" + weight + ")";

            if (structrue.containsKey(sName)) {
                w += getWeight(structrue.get(sName), ints, structrue);
            }
        }
        return w;
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
