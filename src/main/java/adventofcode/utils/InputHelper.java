package adventofcode.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.stream.IntStream;

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

}
