package adventofcode.day.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.utils.Lister;

public class Day6 {
    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day6/input.txt");

    public static void main(final String[] args) throws IOException {
        // starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<Integer> blocksInBanks = Lister.toIntegerList(getInput().get(0), "\t");
        reallocate(blocksInBanks);
    }

    private static void starTwo() throws IOException {
        final List<Integer> blocksInBanks = Lister.toIntegerList(getInput().get(0), "\t");
        reallocate(blocksInBanks);
        reallocate(blocksInBanks);
    }

    private static void reallocate(final List<Integer> blocksInBanks) {
        final Set<String> seenStates = new HashSet<>();
        Integer counter = 1;

        while (true) {
            redistributeList(blocksInBanks);

            if (seenStates.contains(blocksInBanks.toString())) {
                break;
            }

            counter++;
            seenStates.add(blocksInBanks.toString());
        }

        System.out.println(counter);
    }

    private static void redistributeList(final List<Integer> blocksInBanks) {
        final Integer blocks = Collections.max(blocksInBanks);
        final Integer bankIndex = blocksInBanks.indexOf(blocks);
        blocksInBanks.set(bankIndex, 0);

        for (int i = 1; i <= blocks; i++) {
            final Integer getIndex = (bankIndex + i) % blocksInBanks.size();
            blocksInBanks.set(getIndex, blocksInBanks.get(getIndex) + 1);
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
