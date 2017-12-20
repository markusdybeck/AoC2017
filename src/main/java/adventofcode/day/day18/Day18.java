package adventofcode.day.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 {

    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day18/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<String> input = getInput();
        final Map<String, Long> map = new HashMap<>();
        final Map<String, Integer> sounds = new HashMap<>();
        Long latestSound = 0L;

        System.out.println(input.size());

        for (int i = 0; i < input.size();) {
            final String[] commands = input.get(i).split(" ");
            final String operator = commands[0];
            final String letter = commands[1];
            final String strValue = commands.length < 3 ? "0" : commands[2];
            final Long value = isInteger(strValue) ? Long.parseLong(strValue) : map.getOrDefault(strValue, 0L);
            Integer offset = 1;
            Boolean done = false;

            switch (operator) {
            case "set":
                map.put(letter, value);
                break;
            case "add":
                map.put(letter, map.getOrDefault(letter, 0L) + value);
                break;
            case "mul":
                map.put(letter, map.getOrDefault(letter, 0L) * value);
                break;
            case "mod":
                if (value > 0) {
                    map.put(letter, map.getOrDefault(letter, 0L) % value);
                }
                break;
            case "snd":
                latestSound = map.getOrDefault(letter, 0L);
                break;
            case "rcv":
                if (map.getOrDefault(letter, 0L) != 0) {
                    done = true;
                }
                break;
            case "jgz":
                final Integer jump = isInteger(letter) ? Integer.parseInt(letter) : map.getOrDefault(letter, 0L).intValue();
                if (jump > 0) {
                    offset = value.intValue();
                }
                break;
            }

            System.out.println(map);
            i += offset;

            if (done) {
                System.out.println(i);
                break;
            }
        }

        System.out.println(latestSound);
        System.out.println(map);
    }

    private static Integer counter = 0;

    private static void starTwo() throws IOException {
        final List<String> input = getInput();
        final Map<String, Long> map1 = new HashMap<>();
        final Map<String, Long> map2 = new HashMap<>();
        final ArrayDeque<Long> queue1 = new ArrayDeque<>(200);
        final ArrayDeque<Long> queue2 = new ArrayDeque<>(200);

        // Set the default programs value
        map1.put("p", 0L);
        map2.put("p", 1L);

        Integer iOne = 0;
        Integer iTwo = 0;

        while (true) {
            final String[] commands = input.get(iOne).split(" ");
            final String operator = commands[0];
            final String letter = commands[1];
            final String strValue = commands.length < 3 ? "0" : commands[2];
            final Long value = isInteger(strValue) ? Long.parseLong(strValue) : map1.getOrDefault(strValue, 0L);

            final String[] commands2 = input.get(iTwo).split(" ");
            final String operator2 = commands2[0];
            final String letter2 = commands2[1];
            final String strValue2 = commands2.length < 3 ? "0" : commands2[2];
            final Long value2 = isInteger(strValue2) ? Long.parseLong(strValue2) : map2.getOrDefault(strValue2, 0L);

            final Integer offsetOne = operate(operator, queue1, queue2, letter, value, map1, false);
            final Integer offsetTwo = operate(operator2, queue2, queue1, letter2, value2, map2, true);

            if (offsetOne == 0 && offsetTwo == 0 && queue1.isEmpty() && queue2.isEmpty()) {
                break;
            }

            iOne += offsetOne;
            iTwo += offsetTwo;
        }

        System.out.println(counter);
    }

    private static Integer operate(final String operator, final ArrayDeque<Long> myQueue, final ArrayDeque<Long> theOtherQueue, final String letter, final Long value,
        final Map<String, Long> map, final boolean count) {

        Integer offset = 1;

        switch (operator) {
        case "set":
            map.put(letter, value);
            break;
        case "add":
            map.put(letter, map.getOrDefault(letter, 0L) + value);
            break;
        case "mul":
            map.put(letter, map.getOrDefault(letter, 0L) * value);
            break;
        case "mod":
            if (value > 0) {
                map.put(letter, map.getOrDefault(letter, 0L) % value);
            }
            break;
        case "snd":
            final Long send = isInteger(letter) ? Long.parseLong(letter) : map.getOrDefault(letter, 0L);
            theOtherQueue.addLast(send);
            if (count) {
                counter++;
            }
            break;
        case "rcv":
            if (!myQueue.isEmpty()) {
                map.put(letter, myQueue.pollFirst());
            } else {
                offset = 0;
            }
            break;
        case "jgz":
            final Integer jump = isInteger(letter) ? Integer.parseInt(letter) : map.getOrDefault(letter, 0L).intValue();
            if (jump > 0) {
                offset = value.intValue();
            }
            break;
        }

        return offset;
    }

    private static boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (final NumberFormatException ignored) {
        }

        return false;
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
