package adventofcode.day.day12;

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

public class Day12 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day12/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = getInput();
        final Map<Integer, Set<Integer>> theMap = createMap(input);
        final Set<Integer> touched = new HashSet<>();
        theMap.get(0).add(0);
        touched.add(0);
        walk(0, theMap, 0, touched);
        System.out.println(theMap.get(0).size());
    }

    private static void starTwo() throws IOException {
        final List<String> input = getInput();
        final Map<Integer, Set<Integer>> theMap = createMap(input);
        final Set<Set<Integer>> setSet = new HashSet<>();
        final Set<Integer> touched = new HashSet<>();

        theMap.forEach((key, value) -> {
            if (!touched.contains(key)) {
                theMap.get(key).add(key);
                touched.add(key);
                walk(key, theMap, key, touched);
                setSet.add(value);
            }
        });

        System.out.println(theMap.get(0).size());
        System.out.println(setSet.size());
    }

    private static Map<Integer, Set<Integer>> createMap(final List<String> list) {
        final Map<Integer, Set<Integer>> theMap = new HashMap<>();
        for (String str : list) {
            str = str.replaceAll(" ", "").replaceAll("<->", ",");
            final List<Integer> newList = Stream.of(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            final Integer index = newList.get(0);
            newList.remove(0);

            theMap.put(index, new HashSet<>(newList));
        }

        return theMap;
    }

    private static void walk(final Integer index, final Map<Integer, Set<Integer>> theMap, final Integer keyIndex, final Set<Integer> touched) {
        final Set<Integer> keySet = theMap.get(keyIndex);
        final Set<Integer> theSet = new HashSet<>(theMap.get(index));
        if (!Objects.equals(index, keyIndex)) {
            theSet.removeAll(keySet);
        }

        for (final Integer i : theSet) {
            if (!touched.contains(i)) {
                keySet.add(i);
                touched.add(i);
                walk(i, theMap, keyIndex, touched);
            }
        }

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
