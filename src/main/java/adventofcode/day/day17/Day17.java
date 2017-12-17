package adventofcode.day.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 {

    private static Integer STEPS = 366;

     public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
      final List<Integer> numbers = new ArrayList<>();
        numbers.add(0,0);
        int currentPosition = 0;
        for (int i = 1; i < 2018; i++) {
            Integer ns = ((currentPosition + STEPS) % numbers.size()) + 1;
            numbers.add(ns, i);
            currentPosition = ns;
        }

        System.out.println(numbers.get(currentPosition+1));
    }

    // Number 0 will always have position 0, so no array needed.
    private static void starTwo() throws IOException {
        int arraySize = 1;
        int currentPosition = 0;
        int positionOne = -1;
        for (int i = 1; i < 50000000; i++) {
            currentPosition = ((currentPosition + STEPS) % arraySize) + 1;
            if(currentPosition == 1) {
                positionOne = i;
            }
            arraySize++;
        }

        System.out.println(positionOne);
    }

}
