package adventofcode.day.day3;

import adventofcode.utils.Direction;
import adventofcode.utils.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Day3 {

    private static final InputHelper INPUT = new InputHelper(Input.THE_INPUT);

    public static void main(final String[] args) {
        starOne();
        //starTwo();
    }

    private static void starOne() {
        // First solution, not so nice - slow as fu*k
        //createGrid();

        // Second solution after a little bit more thinking...
        starOneSolutionTwo();
    }

    private static void starTwo() {

        Direction direction = Direction.SOUTH;
        Integer turn = -1;
        Integer x = 0;
        Integer y = 0;

        final List<GridMap> list = new ArrayList<>();

        GridMap initial = new GridMap();
        initial.setKey(1);
        initial.setX(0);
        initial.setY(0);
        list.add(initial);


        final Integer range = Integer.parseInt(INPUT.get());
        for (int i = 0; i < range; i++) {
            //System.out.println("Iteration: " + i);
            Direction heading = Direction.values()[(direction.getValue() + turn + 4) % 4];
            GridMap gm = new GridMap();

            final Boolean emptySpot = emptySpot(x, y, heading, list);

            if (!emptySpot) {
                heading = direction;
            }

            switch (heading) {
                case NORTH:
                    y += 1;
                    break;
                case EAST:
                    x += 1;
                    break;
                case SOUTH:
                    y -= 1;
                    break;
                case WEST:
                    x -= 1;
                    break;
            }

            Integer key = getNeighborSum(x, y, list);
            System.out.println("Key: " + key);
            gm.setKey(key);
            gm.setX(x);
            gm.setY(y);
            list.add(gm);

            direction = heading;

            if (key > range) {
                break;
            }
        }

        GridMap theGrid = list.get(list.size() - 1);
        System.out.println(theGrid);

        System.out.println("Steps: " + (Math.abs(theGrid.getX()) + Math.abs(theGrid.getY())));
    }

    private static void starOneSolutionTwo() {
        final Integer intInput = Integer.parseInt(INPUT.get());
        Integer southEastCornerSqrt = (int) Math.sqrt(intInput);

        if (southEastCornerSqrt % 2 == 0) {
            // Below y = -x part of the matrix
            southEastCornerSqrt += 1;
        } else if (intInput != southEastCornerSqrt * southEastCornerSqrt) {
            // Above y = -x part of the matrix
            southEastCornerSqrt += 2;
        }

        // Steps
        final Integer maxSteps = southEastCornerSqrt - 1;
        final Integer minSteps = maxSteps / 2;

        // Calculate
        final Integer southEastCorner = southEastCornerSqrt * southEastCornerSqrt;
        Integer calc = (southEastCorner - intInput) % maxSteps;

        if (calc < minSteps) {
            calc = maxSteps - calc;
        }

        System.out.println("Answer: " + calc);
    }

    private static Integer getNeighborSum(final Integer x, final Integer y, final List<GridMap> theList) {
        Integer first = Stream.of(Direction.values())
                .mapToInt(heading -> {
                    Integer tempY = y;
                    Integer tempX = x;
                    switch (heading) {
                        case NORTH:
                            tempY += 1;
                            break;
                        case EAST:
                            tempX += 1;
                            break;
                        case SOUTH:
                            tempY -= 1;
                            break;
                        case WEST:
                            tempX -= 1;
                            break;
                    }

                    for (GridMap map : theList) {
                        if (Objects.equals(map.getX(), tempX) && Objects.equals(map.getY(), tempY)) {
                            return map.getKey();
                        }
                    }

                    return 0;
                }).sum();

        Integer second = Stream.of(Direction.values())
                .mapToInt(heading -> {
                    Integer tempY = y;
                    Integer tempX = x;
                    switch (heading) {
                        case NORTH:
                            tempY += 1;
                            tempX += 1;
                            break;
                        case EAST:
                            tempX += 1;
                            tempY -= 1;
                            break;
                        case SOUTH:
                            tempY -= 1;
                            tempX -= 1;
                            break;
                        case WEST:
                            tempX -= 1;
                            tempY += 1;
                            break;
                    }

                    for (GridMap map : theList) {
                        if (Objects.equals(map.getX(), tempX) && Objects.equals(map.getY(), tempY)) {
                            return map.getKey();
                        }
                    }

                    return 0;
                }).sum();

        return first + second;
    }

    private static void createGrid() {
        Direction direction = Direction.SOUTH;
        Integer turn = -1;
        Integer x = 0;
        Integer y = 0;

        final List<GridMap> list = new ArrayList<>();

        GridMap initial = new GridMap();
        initial.setKey(1);
        initial.setX(0);
        initial.setY(0);
        list.add(initial);


        final Integer range = Integer.parseInt(INPUT.get());
        for (int i = 0; i < range; i++) {
            System.out.println("Iteration: " + i);
            Direction heading = Direction.values()[(direction.getValue() + turn + 4) % 4];
            GridMap gm = new GridMap();
            gm.setKey(i + 1);

            final Boolean emptySpot = emptySpot(x, y, heading, list);

            if (!emptySpot) {
                heading = direction;
            }

            switch (heading) {
                case NORTH:
                    y += 1;
                    break;
                case EAST:
                    x += 1;
                    break;
                case SOUTH:
                    y -= 1;
                    break;
                case WEST:
                    x -= 1;
                    break;
            }

            gm.setX(x);
            gm.setY(y);
            list.add(gm);

            direction = heading;
        }

        GridMap theGrid = list.get(range - 1);
        System.out.println(theGrid);

        System.out.println("Steps: " + (Math.abs(theGrid.getX()) + Math.abs(theGrid.getY())));

    }

    private static boolean emptySpot(final Integer x, final Integer y, final Direction heading, final List<GridMap> theList) {
        Integer tempY = y;
        Integer tempX = x;

        switch (heading) {
            case NORTH:
                tempY += 1;
                break;
            case EAST:
                tempX += 1;
                break;
            case SOUTH:
                tempY -= 1;
                break;
            case WEST:
                tempX -= 1;
                break;
        }

        for (GridMap map : theList) {
            if (Objects.equals(map.getX(), tempX) && Objects.equals(map.getY(), tempY)) {
                return false;
            }
        }

        return true;

    }

    private static class GridMap {

        private Integer key;
        private Integer x;
        private Integer y;

        public GridMap() {
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "GridMap{" +
                    "key=" + key +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
