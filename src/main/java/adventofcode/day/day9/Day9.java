package adventofcode.day.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day9 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day9/input.txt");

    public static void main(final String[] args) throws IOException {
        //starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        String input = getInput().stream().collect(Collectors.joining()).replaceAll(" ", "");

        // Remove all canceled characters
        input = input.replaceAll("\\!.{1}", "");

        // Remove all garbage
        input = input.replaceAll("<(.*?)>", "");
        input = input.replaceAll(",", "");

        Integer score = 0;
        Integer level = 1;
        for (char theChar : input.toCharArray()) {
            if (theChar == '{') {
                score += level;
                level++;
            } else {
                level--;
            }
        }

        System.out.println(score);
    }


    private static void starTwo() throws IOException {
        String input = getInput().stream().collect(Collectors.joining()).replaceAll(" ", "");

        // Remove all canceled characters
        input = input.replaceAll("\\!.{1}", "");

        // Count garbage
        Integer garbage = 0;
        final Pattern pattern = Pattern.compile("<(.*?)>");
        final Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            garbage += matcher.group().length() - 2;
        }

        System.out.println(garbage);
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
