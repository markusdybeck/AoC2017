package adventofcode.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lister {
    public static List<Integer> toIntegerList(final String string, final String delimiter) {
        return Stream.of(string.split(delimiter)).map(Integer::parseInt).collect(Collectors.toList());
    }
}
