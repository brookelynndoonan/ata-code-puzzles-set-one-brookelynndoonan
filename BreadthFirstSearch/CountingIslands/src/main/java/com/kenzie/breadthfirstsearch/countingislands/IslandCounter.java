package com.kenzie.breadthfirstsearch.countingislands;

import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Coordinate;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Direction;
import com.kenzie.breadthfirstsearch.countingislands.sharedmodel.Node;

import java.util.*;

/**
 * Counts the number of islands for a map.
 */
public class IslandCounter {
    private final int width;
    private final int height;
    private final int[][] map;

    public IslandCounter(int width, int height, int[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    /**
     * Main method for manual testing.
     *
     * @param args - unused
     */

    // Got help from Munir for the if statement in the bottom for loop.
    public static void main(String[] args) {
        IslandCounter islandCounter = new IslandCounter(5, 5, SamplesMaps.FIVE_ISLAND_MAP);
        int islandCount = islandCounter.countIslands();
        System.out.println(String.format("Found %s islands.", islandCount));
    }

    /**
     * Counts the number of islands for the map.
     *
     * @return the number of islands for the map.
     */
    public int countIslands() {
        if (map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int islandCounter = 0;

        boolean[][] visitedNodes = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1 && !visitedNodes[i][j]) {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * width + j);
                    visitedNodes[i][j] = true;


                    while (!queue.isEmpty()) {
                        int position = queue.remove();
                        int row = position / width;
                        int column = position % width;

                        Direction[] directions = {
                                Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
                                Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT };

                        for (Direction direction : directions) {
                            int newRow = row + direction.getRowDirection();
                            int newCol = column + direction.getColumnDirection();

                            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width
                                    && map[newRow][newCol] == 1 && !visitedNodes[newRow][newCol]) {
                                queue.add(newRow * width + newCol);
                                visitedNodes[newRow][newCol] = true;
                            }

                        }
                    }
                    islandCounter++;
                }
            }
        }

        return islandCounter;
    }
}
