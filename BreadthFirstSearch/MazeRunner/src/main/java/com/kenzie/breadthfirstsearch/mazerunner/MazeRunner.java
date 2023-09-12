package com.kenzie.breadthfirstsearch.mazerunner;

import com.kenzie.breadthfirstsearch.mazerunner.model.MazePattern;
import com.kenzie.breadthfirstsearch.mazerunner.model.MazeSpace;
import com.kenzie.breadthfirstsearch.mazerunner.sharedmodel.Node;
import com.kenzie.breadthfirstsearch.mazerunner.utils.MazeGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static com.kenzie.breadthfirstsearch.mazerunner.SampleMazes.MAZE_ONE_EXIT;

/**
 * Class for running through mazes.
 */
public class MazeRunner {

    /**
     * Private constructor, as all methods are static.
     */
    private MazeRunner() {
    }

    /**
     * Utility main method, to run MazeRunner methods without adding tests.
     *
     * @param args - Method arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println(MazeRunner.findClosestExit(MAZE_ONE_EXIT));
    }

    /**
     * Finds the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the closest reachable exit to the maze, or empty if there are no reachable exits
     */

    //Got deque Idea from Munir
    public static Optional<MazeSpace> findClosestExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();
        Queue<Node<MazeSpace>> nodesToVisit = new ArrayDeque<>();

        if (entrance.isEmpty()) {
            return Optional.empty();
        }
        Node<MazeSpace> entranceNode = entrance.get();

        nodesToVisit.add(entranceNode);
        visitedNodes.add(entranceNode);

        while ((!nodesToVisit.isEmpty())) {
            Node<MazeSpace> currentNode = nodesToVisit.poll();
            MazeSpace mazeSpace = currentNode.getValue();

            if (mazeSpace.isExit()) {
                return Optional.of(mazeSpace);
            }
            for (Node<MazeSpace> neighborNode : currentNode.getNeighbors()) {
                if (!visitedNodes.contains(neighborNode)) {
                    nodesToVisit.add(neighborNode);
                    visitedNodes.add(neighborNode);
                }
            }
        }
        return Optional.empty();
    }


    /**
     * Finds the path to the exit out of the maze closest to its entrance.
     *
     * @param pattern - the pattern of maze being run through
     * @return the path closest reachable exit to the maze, or an empty list if there are no reachable exits
     */

    // Got some guidance from Elise for building this method.
    // Went over in depth discussion on Graph Theory.
    public static List<MazeSpace> findShortestPathToExit(MazePattern pattern) {
        Optional<Node<MazeSpace>> entrance = MazeGenerator.generateMaze(pattern);

        Set<Node<MazeSpace>> visitedNodes = new HashSet<>();

        Queue<List<Node<MazeSpace>>> currentLevel = new ArrayDeque<>();
        Queue<List<Node<MazeSpace>>> nextLevel = new ArrayDeque<>();

        if (entrance.isEmpty()) {
            return Collections.emptyList();
        }
        List<Node<MazeSpace>> currentPath = new ArrayList<>();
        currentPath.add(entrance.get());
        currentLevel.add(currentPath);

        while (!currentLevel.isEmpty()) {
            currentPath = currentLevel.poll();
            Node<MazeSpace> currentNode = currentPath.get(currentPath.size() - 1);
            visitedNodes.add(currentNode);

            if (currentNode.getValue().isExit()) {
                return currentPath.stream()
                        .map(Node::getValue)
                        .collect(Collectors.toList());
            } else {
                for (Node<MazeSpace> neighborSpaces : currentNode.getNeighbors()) {
                    if (!visitedNodes.contains(neighborSpaces)) {
                        List<Node<MazeSpace>> newPaths = new ArrayList<>();
                        newPaths.addAll(currentPath);
                        newPaths.add(neighborSpaces);
                        nextLevel.add(newPaths);
                    }
                }
            }
            if (currentLevel.isEmpty()) {
                currentLevel.addAll(nextLevel);
                nextLevel.clear();
            }
        }

        return Collections.emptyList();
    }
}
