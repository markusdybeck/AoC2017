package adventofcode.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InputHelper {
    private final String theInput;

    public InputHelper(final String input) {
        this.theInput = Objects.requireNonNull(input);
    }

    public String get() {
        return theInput;
    }

    public Integer lastIndex() {
        return theInput.length() - 1;
    }

    public Integer length() {
        return theInput.length();
    }

    public String stringAt(final Integer index) {
        return String.valueOf(theInput.charAt(index));
    }

    public Integer toInt(final Integer index) {
        return Integer.parseInt(this.stringAt(index));
    }

    public static int toInt(final char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    public Integer countMatches(final String pattern) {
        return StringUtils.countMatches(theInput, pattern);
    }

    public IntStream intStream() {
        return IntStream.range(0, theInput.length());
    }

    public List<List<Integer>> toListOfIntegerList(final String columnDelimiter, final String rowDelimiter) {
        return Stream.of(theInput.split(rowDelimiter))
                .map(row -> _toIntegerList(row, columnDelimiter))
                .collect(Collectors.toList());
    }

    public List<List<String>> toListOfStringList(final String columnDelimiter, final String rowDelimiter) {
        return Stream.of(theInput.split(rowDelimiter))
                .map(row -> _toStringList(row, columnDelimiter))
                .collect(Collectors.toList());
    }

    public List<String> toStringList(final String delimiter) {
        return _toStringList(theInput, delimiter);
    }

    public List<Integer> toIntegerList(final String delimiter) {
        return _toIntegerList(theInput, delimiter);
    }

    private List<Integer> _toIntegerList(final String input, final String delimiter) {
        return Stream.of(input.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> _toStringList(final String input, final String delimiter) {
        return Stream.of(input.split(delimiter)).collect(Collectors.toList());
    }
}
